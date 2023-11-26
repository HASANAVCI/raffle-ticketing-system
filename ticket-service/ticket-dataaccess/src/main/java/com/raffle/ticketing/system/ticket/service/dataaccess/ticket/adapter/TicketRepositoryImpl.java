package com.raffle.ticketing.system.ticket.service.dataaccess.ticket.adapter;

import com.raffle.ticketing.system.ticket.service.dataaccess.ticket.mapper.TicketDataAccessMapper;
import com.raffle.ticketing.system.ticket.service.dataaccess.ticket.repository.TicketJpaRepository;
import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;
import com.raffle.ticketing.system.ticket.service.domain.ports.output.repository.TicketRepository;
import com.raffle.ticketing.system.ticket.service.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TicketRepositoryImpl implements TicketRepository {

    private final TicketJpaRepository ticketJpaRepository;
    private final TicketDataAccessMapper ticketDataAccessMapper;

    public TicketRepositoryImpl(TicketJpaRepository ticketJpaRepository,
                                TicketDataAccessMapper ticketDataAccessMapper) {
        this.ticketJpaRepository = ticketJpaRepository;
        this.ticketDataAccessMapper = ticketDataAccessMapper;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketDataAccessMapper.ticketEntityToTicket(ticketJpaRepository
                .save(ticketDataAccessMapper.ticketToTicketEntity(ticket)));
    }

    @Override
    public Optional<Ticket> findByTrackingId(TrackingId trackingId) {
        return ticketJpaRepository.findByTrackingId(trackingId.getValue())
                .map(ticketDataAccessMapper::ticketEntityToTicket);
    }
}
