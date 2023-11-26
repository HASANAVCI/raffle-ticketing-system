package com.raffle.ticketing.system.ticket.service.dataaccess.ticket.mapper;

import com.raffle.ticketing.system.domain.valueobject.*;
import com.raffle.ticketing.system.ticket.service.dataaccess.ticket.entity.TicketEntity;
import com.raffle.ticketing.system.ticket.service.dataaccess.ticket.entity.TicketItemEntity;
import com.raffle.ticketing.system.ticket.service.domain.entity.Raffle;
import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;
import com.raffle.ticketing.system.ticket.service.domain.entity.TicketItem;
import com.raffle.ticketing.system.ticket.service.domain.valueobject.TicketItemId;
import com.raffle.ticketing.system.ticket.service.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.raffle.ticketing.system.ticket.service.domain.entity.Ticket.FAILURE_MESSAGE_DELIMITER;

@Component
public class TicketDataAccessMapper {

    public TicketEntity ticketToTicketEntity(Ticket ticket) {
        TicketEntity ticketEntity = TicketEntity.builder()
                .id(ticket.getId().getValue())
                .customerId(ticket.getCustomerId().getValue())
                .officeId(ticket.getOfficeId().getValue())
                .trackingId(ticket.getTrackingId().getValue())
                .price(ticket.getPrice().getAmount())
                .items(ticketItemsToTicketItemEntities(ticket.getItems()))
                .ticketStatus(ticket.getTicketStatus())
                .failureMessages(ticket.getFailureMessages() !=null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, ticket.getFailureMessages()) : "")
                .build();
        ticketEntity.getItems().forEach(ticketItemEntity -> ticketItemEntity.setTicket(ticketEntity));
        return ticketEntity;
    }

    public Ticket ticketEntityToTicket(TicketEntity ticketEntity) {
        return Ticket.Builder.builder()
                .ticketId(new TicketId(ticketEntity.getId()))
                .customerId(new CustomerId(ticketEntity.getCustomerId()))
                .officeId(new OfficeId(ticketEntity.getOfficeId()))
                .price(new Money(ticketEntity.getPrice()))
                .items(ticketItemEntitiesToTicketItem(ticketEntity.getItems()))
                .trackingId(new TrackingId(ticketEntity.getTrackingId()))
                .ticketStatus(ticketEntity.getTicketStatus())
                .failureMessages(ticketEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(ticketEntity.getFailureMessages()
                                .split(FAILURE_MESSAGE_DELIMITER))))
                .build();
    }

    private List<TicketItem> ticketItemEntitiesToTicketItem(List<TicketItemEntity> items) {
        return items.stream()
                .map(ticketItemEntity -> TicketItem.Builder.builder()
                        .ticketItemId(new TicketItemId(ticketItemEntity.getId()))
                        .raffle(new Raffle(new RaffleId(ticketItemEntity.getRaffleId())))
                        .price(new Money(ticketItemEntity.getPrice()))
                        .quantity(ticketItemEntity.getQuantity())
                        .subTotal(new Money(ticketItemEntity.getSubTotal()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<TicketItemEntity> ticketItemsToTicketItemEntities(List<TicketItem> items) {
        return items.stream()
                .map(ticketItem -> TicketItemEntity.builder()
                        .id(ticketItem.getId().getValue())
                        .raffleId(ticketItem.getRaffle().getId().getValue())
                        .price(ticketItem.getPrice().getAmount())
                        .quantity(ticketItem.getQuantity())
                        .subTotal(ticketItem.getSubTotal().getAmount())
                        .build())
                .collect(Collectors.toList());
    }
}
