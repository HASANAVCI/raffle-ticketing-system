package com.raffle.ticketing.system.ticket.service.domain.ports.output.message.publisher.payment;

import com.raffle.ticketing.system.domain.event.publisher.DomainEventPublisher;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketCancelledEvent;

public interface TicketCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<TicketCancelledEvent> {
}
