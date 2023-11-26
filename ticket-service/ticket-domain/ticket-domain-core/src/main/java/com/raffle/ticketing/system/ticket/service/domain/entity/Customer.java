package com.raffle.ticketing.system.ticket.service.domain.entity;

import com.raffle.ticketing.system.domain.entity.AggregateRoot;
import com.raffle.ticketing.system.domain.valueobject.CustomerId;

/**
    For check the existing Customer
 */
public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
