package com.raffle.ticketing.system.ticket.service.domain.ports.output.message.publisher.payment;

import com.raffle.ticketing.system.domain.event.publisher.DomainEventPublisher;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketCreatedEvent;

public interface TicketCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<TicketCreatedEvent> {

}
