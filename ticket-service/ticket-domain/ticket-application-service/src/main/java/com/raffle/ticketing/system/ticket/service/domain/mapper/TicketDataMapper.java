package com.raffle.ticketing.system.ticket.service.domain.mapper;

import com.raffle.ticketing.system.domain.valueobject.*;
import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketCommand;
import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.entity.Raffle;
import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;
import com.raffle.ticketing.system.ticket.service.domain.entity.TicketItem;
import com.raffle.ticketing.system.ticket.service.domain.entity.TicketOffice;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
    TicketDataMapperClass - Input Dto'lardan Domain objeleri yaratmak için. Bi nevi Factory Pattern çünkü burada object creation ve conversition işlemleri yapılıyor.
 */
@Component
public class TicketDataMapper {

    public TicketOffice createTicketCommandToTicketOffice(CreateTicketCommand createTicketCommand) {
        return TicketOffice.Builder.builder()
                .officeId(new OfficeId(createTicketCommand.getOfficeId()))
                .raffles(createTicketCommand.getItems().stream().map(ticketItem ->
                        new Raffle(new RaffleId(ticketItem.getRaffleId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public Ticket createTicketCommandToTicket(CreateTicketCommand createTicketCommand) {
        return Ticket.Builder.builder()
                .customerId(new CustomerId(createTicketCommand.getCustomerId()))
                .officeId(new OfficeId(createTicketCommand.getOfficeId()))
                .price(new Money(createTicketCommand.getPrice()))
                .items(ticketItemsToTicketEntities(createTicketCommand.getItems()))
                .build();
    }

    private List<TicketItem> ticketItemsToTicketEntities(List<com.raffle.ticketing.system.ticket.service.domain.dto.create.TicketItem> items) {
        return items.stream()
                .map(ticketItem ->
                        TicketItem.Builder.builder()
                                .raffle(new Raffle(new RaffleId(ticketItem.getRaffleId())))
                                .price(new Money(ticketItem.getPrice()))
                                .quantity(ticketItem.getQuantity())
                                .subTotal(new Money(ticketItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

    public CreateTicketResponse ticketToCreateTicketResponse(Ticket ticket) {
        return CreateTicketResponse.builder()
                .trackingId(ticket.getTrackingId().getValue())
                .ticketStatus(ticket.getTicketStatus())
                .build();
    }

    public TrackTicketResponse ticketToTrackTicketResponse(Ticket ticket) {
        return TrackTicketResponse.builder()
                .ticketTrackingId(ticket.getTrackingId().getValue())
                .ticketStatus(ticket.getTicketStatus())
                .failureMessages(ticket.getFailureMessages())
                .build();
    }
}
