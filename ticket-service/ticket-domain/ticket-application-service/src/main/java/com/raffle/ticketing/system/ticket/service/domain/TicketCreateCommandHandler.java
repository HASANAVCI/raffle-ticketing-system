package com.raffle.ticketing.system.ticket.service.domain;

import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketCommand;
import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketCreatedEvent;
import com.raffle.ticketing.system.ticket.service.domain.mapper.TicketDataMapper;
import com.raffle.ticketing.system.ticket.service.domain.ports.output.message.publisher.payment.TicketCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
    TicketApplicationService' deki createTicket işlevini bu CommandHandler class'a devredip ApplicationService'i çok karmaşıklaştırmıyoruz.
 */
@Slf4j
@Component
public class TicketCreateCommandHandler {

    private final TicketCreateHelper ticketCreateHelper;
    private final TicketDataMapper ticketDataMapper;

    private final TicketCreatedPaymentRequestMessagePublisher messagePublisher;

    public TicketCreateCommandHandler(TicketCreateHelper ticketCreateHelper,
                                      TicketDataMapper ticketDataMapper,
                                      TicketCreatedPaymentRequestMessagePublisher messagePublisher) {
        this.ticketCreateHelper = ticketCreateHelper;
        this.ticketDataMapper = ticketDataMapper;
        this.messagePublisher = messagePublisher;
    }

    public CreateTicketResponse createTicket(CreateTicketCommand createTicketCommand) {
        TicketCreatedEvent ticketCreatedEvent = ticketCreateHelper.persistTicket(createTicketCommand);
        log.info("Ticket is created wit id: {}", ticketCreatedEvent.getTicket().getId().getValue());
        messagePublisher.publish(ticketCreatedEvent);
        return ticketDataMapper.ticketToCreateTicketResponse(ticketCreatedEvent.getTicket());
    }
}
