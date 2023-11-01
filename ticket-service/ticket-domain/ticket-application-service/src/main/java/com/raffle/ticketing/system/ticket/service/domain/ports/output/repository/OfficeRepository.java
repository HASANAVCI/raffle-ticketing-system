package com.raffle.ticketing.system.ticket.service.domain.ports.output.repository;

import com.raffle.ticketing.system.ticket.service.domain.entity.TicketOffice;

import java.util.Optional;

public interface OfficeRepository {

   Optional<TicketOffice> findOfficeInformation(TicketOffice office);
}
