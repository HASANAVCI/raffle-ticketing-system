package com.raffle.ticketing.system.ticket.service.domain.event;

import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;

import java.time.ZonedDateTime;

public class TicketCancelledEvent extends TicketEvent {

    public TicketCancelledEvent(Ticket ticket, ZonedDateTime createdAt) {
        super(ticket, createdAt);
    }
}
