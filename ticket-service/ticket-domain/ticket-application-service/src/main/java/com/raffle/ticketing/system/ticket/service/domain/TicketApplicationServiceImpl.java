package com.raffle.ticketing.system.ticket.service.domain;

import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketCommand;
import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketQuery;
import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.ports.input.service.TicketApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class TicketApplicationServiceImpl implements TicketApplicationService {

    private final TicketCreateCommandHandler ticketCreateCommandHandler;

    private final TicketTrackCommandHandler ticketTrackCommandHandler;

    public TicketApplicationServiceImpl(TicketCreateCommandHandler ticketCreateCommandHandler, TicketTrackCommandHandler ticketTrackCommandHandler) {
        this.ticketCreateCommandHandler = ticketCreateCommandHandler;
        this.ticketTrackCommandHandler = ticketTrackCommandHandler;
    }

    @Override
    public CreateTicketResponse createTicket(CreateTicketCommand createTicketCommand) {
        return ticketCreateCommandHandler.createTicket(createTicketCommand);
    }

    @Override
    public TrackTicketResponse trackTicket(TrackTicketQuery trackTicketQuery) {
        return ticketTrackCommandHandler.trackTicket(trackTicketQuery);
    }
}
