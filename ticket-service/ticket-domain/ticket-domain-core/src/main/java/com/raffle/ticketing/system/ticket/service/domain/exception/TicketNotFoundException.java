package com.raffle.ticketing.system.ticket.service.domain.exception;

import com.raffle.ticketing.system.domain.exception.DomainException;

public class TicketNotFoundException extends DomainException {
    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
