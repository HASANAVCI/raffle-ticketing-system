package com.raffle.ticketing.system.ticket.service.domain.event;

import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;

import java.time.ZonedDateTime;

public class TicketPaidEvent extends TicketEvent {

    public TicketPaidEvent(Ticket ticket, ZonedDateTime createdAt) {
        super(ticket, createdAt);
    }
}
