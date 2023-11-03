package com.raffle.ticketing.system.ticket.service.domain;

import com.raffle.ticketing.system.ticket.service.domain.dto.create.CreateTicketCommand;
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

@Slf4j
@Component
public class TicketCreateHelper {

    private final TicketDomainService ticketDomainService;

    private final TicketRepository ticketRepository;

    private final CustomerRepository customerRepository;

    private final OfficeRepository officeRepository;

    private final TicketDataMapper ticketDataMapper;

    public TicketCreateHelper(TicketDomainService ticketDomainService,
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

    /**
     * https://stackoverflow.com/questions/1606559/spring-aop-vs-aspectj
     * SpringProxyAOP kullanıyoruz. Alternatif olarak AspectJ kullanalabilirdi fakat ihtiyacımızı görüyor.
     * The methods with transactional annotation must be public, otherwise th transactional annotation will not function again!
     */
    @Transactional
    public TicketCreatedEvent persistTicket(CreateTicketCommand createTicketCommand) {
        checkCustomer(createTicketCommand.getCustomerId());
        TicketOffice ticketOffice = checkTicketOffice(createTicketCommand);
        Ticket ticket = ticketDataMapper.createTicketCommandToTicket(createTicketCommand);
        TicketCreatedEvent ticketCreatedEvent = ticketDomainService.validateAndInitializeTicket(ticket, ticketOffice);
        saveTicket(ticket);
        log.info("Ticket is created with id : {}", ticketCreatedEvent.getTicket().getId().getValue());
        return ticketCreatedEvent;
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

    /**
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
