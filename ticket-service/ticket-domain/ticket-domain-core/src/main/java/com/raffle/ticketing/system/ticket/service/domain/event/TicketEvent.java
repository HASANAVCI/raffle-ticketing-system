package com.raffle.ticketing.system.ticket.service.domain.event;

import com.raffle.ticketing.system.domain.event.DomainEvent;
import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;

import java.time.ZonedDateTime;

/***
    Created for code duplicate subclasses.Refactoring..
 */
public abstract class TicketEvent implements DomainEvent<Ticket> {
    private final Ticket ticket;
    private final ZonedDateTime createdAt;

    public TicketEvent(Ticket ticket, ZonedDateTime createdAt) {
        this.ticket = ticket;
        this.createdAt = createdAt;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
