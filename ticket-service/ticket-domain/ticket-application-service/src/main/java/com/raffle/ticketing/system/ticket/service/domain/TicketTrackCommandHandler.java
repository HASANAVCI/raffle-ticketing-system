package com.raffle.ticketing.system.ticket.service.domain;

import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketQuery;
import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;
import com.raffle.ticketing.system.ticket.service.domain.exception.TicketNotFoundException;
import com.raffle.ticketing.system.ticket.service.domain.mapper.TicketDataMapper;
import com.raffle.ticketing.system.ticket.service.domain.ports.output.repository.TicketRepository;
import com.raffle.ticketing.system.ticket.service.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class TicketTrackCommandHandler {

    private final TicketDataMapper ticketDataMapper;

    private final TicketRepository ticketRepository;

    public TicketTrackCommandHandler(TicketDataMapper ticketDataMapper, TicketRepository ticketRepository) {
        this.ticketDataMapper = ticketDataMapper;
        this.ticketRepository = ticketRepository;
    }

    TrackTicketResponse trackTicket(TrackTicketQuery ticketQuery) {

        Optional<Ticket> ticketResult = ticketRepository.findByTrackingId(new TrackingId(ticketQuery.getTicketTrackingId()));
        if (ticketResult.isEmpty()) {
            log.warn("Could not find tciket with tracking id: {}", ticketQuery.getTicketTrackingId());
            throw new TicketNotFoundException("Could not find tciket with tracking id:" +
                    ticketQuery.getTicketTrackingId());
        }
        return ticketDataMapper.ticketToTrackTicketResponse(ticketResult.get());
    }
}
