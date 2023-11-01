package com.raffle.ticketing.system.ticket.service.domain.exception;

import com.raffle.ticketing.system.domain.exception.DomainException;

public class TicketDomainException extends DomainException {

    public TicketDomainException(String message) {
        super(message);
    }

    public TicketDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
