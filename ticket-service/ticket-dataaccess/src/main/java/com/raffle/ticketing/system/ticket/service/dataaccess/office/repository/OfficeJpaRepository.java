package com.raffle.ticketing.system.ticket.service.dataaccess.office.repository;

import com.raffle.ticketing.system.ticket.service.dataaccess.office.entity.OfficeEntity;
import com.raffle.ticketing.system.ticket.service.dataaccess.office.entity.OfficeEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfficeJpaRepository extends JpaRepository<OfficeEntity, OfficeEntityId> {

    Optional<List<OfficeEntity>> findByOfficeIdAndRaffleIdIn(UUID officeId, List<UUID> raffleIds);

}
