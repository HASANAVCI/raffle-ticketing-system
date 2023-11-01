package com.raffle.ticketing.system.domain.event.publisher;

import com.raffle.ticketing.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
