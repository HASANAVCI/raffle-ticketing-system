package com.raffle.ticketing.system.ticket.service.application.rest;

import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketCommand;
import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketQuery;
import com.raffle.ticketing.system.ticket.service.domain.dto.track.TrackTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.ports.input.service.TicketApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/tickets", produces = "applicaiton/vnd.api.v1+json")
public class TicketController {

    private final TicketApplicationService ticketApplicationService;


    public TicketController(TicketApplicationService ticketApplicationService) {
        this.ticketApplicationService = ticketApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateTicketResponse> createTicket(@RequestBody CreateTicketCommand createTicketCommand) {
        log.info("Creating ticket for customer: {} at ticketOffice: {}", createTicketCommand.getCustomerId(),
                createTicketCommand.getOfficeId());
        CreateTicketResponse createTicketResponse = ticketApplicationService.createTicket(createTicketCommand);
        log.info("Ticket created with tracking id: {}", createTicketResponse.getTrackingId());
        return ResponseEntity.ok(createTicketResponse);
    }

    @GetMapping
    public ResponseEntity<TrackTicketResponse> getTicketByTrackingId(@PathVariable UUID trackingId) {
        TrackTicketResponse trackTicketResponse =
                ticketApplicationService.trackTicket(TrackTicketQuery.builder().ticketTrackingId(trackingId).build());
        log.info("Retırning ticket status with tracking id: {}", trackTicketResponse.getTicketTrackingId());
        return ResponseEntity.ok(trackTicketResponse);
    }
}
