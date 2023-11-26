package com.raffle.ticketing.system.ticket.service.dataaccess.office.adapter;

import com.raffle.ticketing.system.ticket.service.dataaccess.office.entity.OfficeEntity;
import com.raffle.ticketing.system.ticket.service.dataaccess.office.mapper.OfficeDataAccessMapper;
import com.raffle.ticketing.system.ticket.service.dataaccess.office.repository.OfficeJpaRepository;
import com.raffle.ticketing.system.ticket.service.domain.entity.TicketOffice;
import com.raffle.ticketing.system.ticket.service.domain.ports.output.repository.OfficeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OfficeRepositoryImpl implements OfficeRepository {

    private final OfficeJpaRepository officeJpaRepository;
    private  final OfficeDataAccessMapper officeDataAccessMapper;

    public OfficeRepositoryImpl(OfficeJpaRepository officeJpaRepository,
                                OfficeDataAccessMapper officeDataAccessMapper) {
        this.officeJpaRepository = officeJpaRepository;
        this.officeDataAccessMapper = officeDataAccessMapper;
    }

    @Override
    public Optional<TicketOffice> findOfficeInformation(TicketOffice office) {

        List<UUID> officeRaffles =
                officeDataAccessMapper.officeToOfficeRaffles(office);

        Optional<List<OfficeEntity>> officeEntities = officeJpaRepository
                .findByOfficeIdAndRaffleIdIn(office.getId().getValue(),
                officeRaffles);

        return officeEntities.map(officeDataAccessMapper::officeEntityToOffice);
    }
}
