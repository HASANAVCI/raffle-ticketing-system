package com.raffle.ticketing.system.ticket.service.domain;

import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketCommand;
import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketResponse;
import com.raffle.ticketing.system.ticket.service.domain.entity.Customer;
import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;
import com.raffle.ticketing.system.ticket.service.domain.entity.TicketOffice;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketCreatedEvent;
import com.raffle.ticketing.system.ticket.service.domain.exception.TicketDomainException;
import com.raffle.ticketing.system.ticket.service.domain.mapper.TicketDataMapper;
import com.raffle.ticketing.system.ticket.service.domain.ports.output.repository.CustomerRepository;
import com.raffle.ticketing.system.ticket.service.domain.ports.output.repository.OfficeRepository;
import com.raffle.ticketing.system.ticket.service.domain.ports.output.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/*
    TicketApplicationService' deki createTicket işlevini bu CommandHandler class'a devredip ApplicationService'i çok karmaşıklaştırmıyoruz.
 */
@Slf4j
@Component
public class TicketCreateCommandHandler {

    private final TicketDomainService ticketDomainService;

    private final TicketRepository ticketRepository;

    private final CustomerRepository customerRepository;

    private final OfficeRepository officeRepository;

    private final TicketDataMapper ticketDataMapper;

    public TicketCreateCommandHandler(TicketDomainService ticketDomainService,
                                      TicketRepository ticketRepository,
                                      CustomerRepository customerRepository,
                                      OfficeRepository officeRepository,
                                      TicketDataMapper ticketDataMapper) {
        this.ticketDomainService = ticketDomainService;
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.officeRepository = officeRepository;
        this.ticketDataMapper = ticketDataMapper;
    }

    @Transactional
    public CreateTicketResponse createTicket(CreateTicketCommand createTicketCommand) {
        checkCustomer(createTicketCommand.getCustomerId());
        TicketOffice ticketOffice = checkTicketOffice(createTicketCommand);
        Ticket ticket = ticketDataMapper.createTicketCommandToTicket(createTicketCommand);
        TicketCreatedEvent ticketCreatedEvent = ticketDomainService.validateAndInitializeTicket(ticket, ticketOffice);
        Ticket ticketResult = saveTicket(ticket);
        log.info("Ticket is created with id: {}" , ticketResult.getId().getValue());
        return ticketDataMapper.ticketToCreateTicketResponse(ticketResult);
    }

    private TicketOffice checkTicketOffice(CreateTicketCommand createTicketCommand) {
        TicketOffice ticketOffice = ticketDataMapper.createTicketCommandToTicketOffice(createTicketCommand);
        Optional<TicketOffice> optionalTicketOffice = officeRepository.findOfficeInformation(ticketOffice);
        if (optionalTicketOffice.isEmpty()) {
            log.warn("Could not find ticketOffice with office id: {}" , createTicketCommand.getOfficeId());
            throw new TicketDomainException("Could not find ticketOffice with office id: {}" +
                    createTicketCommand.getOfficeId());
        }
        return optionalTicketOffice.get();
    }

    /*
        Sadece customer'ın uygunluğunu(availability) kontrolünü yaptığımız için ve çok fazla business logic check gerekli olmadığı için
         bunu DomainService iletmeden burada hallediyoruz.
     */
    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);

        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {} ", customerId);
            throw new TicketDomainException("Could not find customer with customer id: {} " + customer);
        }
    }

    private Ticket saveTicket(Ticket ticket) {
        Ticket ticketResult = ticketRepository.save(ticket);
        if (ticketResult == null) {
            log.error("Could not save ticket!");
            throw new TicketDomainException("Could not save ticket!");
        }
        log.info("Ticket is saved with id: {}", ticketResult.getId().getValue());
        return ticketResult;
    }
}
