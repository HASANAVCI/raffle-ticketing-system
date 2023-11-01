package com.raffle.ticketing.system.ticket.service.domain.ports.output.message.publisher.officeapproval;

import com.raffle.ticketing.system.domain.event.publisher.DomainEventPublisher;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketPaidEvent;

public interface TicketPaidOfficeRequestMessagePublisher extends DomainEventPublisher<TicketPaidEvent> {
}
