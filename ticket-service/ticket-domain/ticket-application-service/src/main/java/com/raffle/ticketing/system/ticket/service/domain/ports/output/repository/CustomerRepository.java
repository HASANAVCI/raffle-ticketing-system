package com.raffle.ticketing.system.ticket.service.domain.ports.output.repository;

import com.raffle.ticketing.system.ticket.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
