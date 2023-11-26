package com.raffle.ticketing.system.ticket.service.dataaccess.ticket.repository;

import com.raffle.ticketing.system.ticket.service.dataaccess.ticket.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketJpaRepository extends JpaRepository<TicketEntity, UUID> {

    Optional<TicketEntity> findByTrackingId(UUID trackingId);
}
