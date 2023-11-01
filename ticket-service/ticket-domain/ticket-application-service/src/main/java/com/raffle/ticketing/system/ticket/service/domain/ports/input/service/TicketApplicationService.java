package com.raffle.ticketing.system.ticket.service.domain.ports.input.service;

import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketCommand;
import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketQuery;
import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketResponse;

import javax.validation.Valid;

/*
    3 tane input port var listenerlarla beraber. Ve input portları ApplicationService implement ediyoruz.
 */
public interface TicketApplicationService {
    CreateTicketResponse createTicket(@Valid CreateTicketCommand createTicketCommand);

    TrackTicketResponse trackTicket(@Valid TrackTicketQuery trackTicketQuery);
}
