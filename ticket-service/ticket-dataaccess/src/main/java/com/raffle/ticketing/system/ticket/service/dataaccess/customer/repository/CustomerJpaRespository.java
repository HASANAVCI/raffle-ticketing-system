package com.raffle.ticketing.system.ticket.service.dataaccess.customer.repository;

import com.raffle.ticketing.system.ticket.service.dataaccess.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerJpaRespository extends JpaRepository<CustomerEntity, UUID> {
}
