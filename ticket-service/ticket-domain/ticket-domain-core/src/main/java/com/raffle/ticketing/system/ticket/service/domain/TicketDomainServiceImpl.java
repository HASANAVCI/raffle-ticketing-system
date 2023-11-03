package com.raffle.ticketing.system.ticket.service.domain;

import com.raffle.ticketing.system.ticket.service.domain.entity.Raffle;
import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;
import com.raffle.ticketing.system.ticket.service.domain.entity.TicketOffice;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketCancelledEvent;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketCreatedEvent;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketPaidEvent;
import com.raffle.ticketing.system.ticket.service.domain.exception.TicketDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class TicketDomainServiceImpl implements TicketDomainService {

    private static final String UTC = "UTC";

    /**
        DomainService'in bazı business requirementları kontrol etmek için validateAndInitializeTicket() methodunda görüldüğü gibi birden fazla
        Aggregate kullanıyor!
     */
    @Override
    public TicketCreatedEvent validateAndInitializeTicket(Ticket ticket, TicketOffice ticketOffice) {
        validateTicketOffice(ticketOffice);
        setTicketRaffleInformation(ticket, ticketOffice);
        ticket.validateTicket();
        ticket.initializeTicketing();
        log.info("Ticket with id: {} is initiated", ticket.getId().getValue());
        return new TicketCreatedEvent(ticket, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    private void setTicketRaffleInformation(Ticket ticket, TicketOffice ticketOffice) {
        ticket.getItems().forEach(ticketItem -> ticketOffice.getRaffles().forEach(ticketOfficeRaffle -> {
            Raffle currentRaffle = ticketItem.getRaffle();
            if (currentRaffle.equals(ticketOfficeRaffle)) {
                currentRaffle.updateWithConfirmedNameAndPrice(ticketOfficeRaffle.getName(), ticketOfficeRaffle.getPrice());
            }
        }));
    }

    private void validateTicketOffice(TicketOffice ticketOffice) {
        if (!ticketOffice.isActive()) {
            throw new TicketDomainException("TicketOffice with id " + ticketOffice.getId().getValue() +
                " is currently not active!");
        }
    }

    @Override
    public TicketPaidEvent payTicket(Ticket ticket) {
        ticket.pay();
        log.info("Ticket with id: {} is paid", ticket.getId().getValue());
        return new TicketPaidEvent(ticket, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    /**
        Ticket'lama işleminin son adımıdır ve bir event döndürmez.Bilet onaylandıktan sonra başka bir event tetiklemeye gerek yok!
        Bunun yerine client, TrackingId ile bir get() endpointini kullanrak dataları getirir.
        Client mesela approve() işleminden sonra örneğin delivery(sipariş) akışı için bir event consumer'ımız olsaydı client için event dönebilirdik :)
     */
    @Override
    public void approveTicket(Ticket ticket) {
        ticket.approve();
        log.info("Ticket with id: {} is approved",  ticket.getId().getValue());
    }

    @Override
    public TicketCancelledEvent cancelTicketPayment(Ticket ticket, List<String> failureMessages) {
        ticket.initCancel(failureMessages);
        log.info("Ticket payment is cancelling for ticket id: {}", ticket.getId().getValue());
        return new TicketCancelledEvent(ticket, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelTicket(Ticket ticket, List<String> failureMessages) {
        ticket.cancel(failureMessages);
        log.info("Ticket with id: {} is cancelled", ticket.getId().getValue());
    }
}
