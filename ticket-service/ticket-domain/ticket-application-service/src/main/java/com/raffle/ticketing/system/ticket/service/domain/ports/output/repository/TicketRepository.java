package com.raffle.ticketing.system.ticket.service.domain.ports.output.repository;

import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;
import com.raffle.ticketing.system.ticket.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    /*
        Optional kullanıyoruz çünkü ilgili trackingId'ye sahip bir Ticket bulabilir veya olmayabilir!
     */
    Optional<Ticket> findByTrackingId(TrackingId trackingId);
}
