package com.raffle.ticketing.system.ticket.service.dataaccess.office.mapper;

import com.raffle.ticketing.system.domain.valueobject.Money;
import com.raffle.ticketing.system.domain.valueobject.OfficeId;
import com.raffle.ticketing.system.domain.valueobject.RaffleId;
import com.raffle.ticketing.system.ticket.service.dataaccess.office.entity.OfficeEntity;
import com.raffle.ticketing.system.ticket.service.dataaccess.office.exception.OfficeDataAccessException;
import com.raffle.ticketing.system.ticket.service.domain.entity.Raffle;
import com.raffle.ticketing.system.ticket.service.domain.entity.TicketOffice;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OfficeDataAccessMapper {

    public List<UUID> officeToOfficeRaffles(TicketOffice office) {
        return office.getRaffles().stream()
                .map(raffle -> raffle.getId().getValue())
                .collect(Collectors.toList());
    }

    public TicketOffice officeEntityToOffice(List<OfficeEntity> officeEntities) {
        OfficeEntity officeEntity =
                officeEntities.stream().findFirst().orElseThrow(() ->
                        new OfficeDataAccessException("TicketOffice could not be found!"));

        List<Raffle> officeRaffles = officeEntities.stream().map(entity ->
                new Raffle(new RaffleId(entity.getRaffleId()), entity.getRaffleName(),
                        new Money(entity.getRafflePrice()))).toList();

        return TicketOffice.Builder.builder()
                .officeId(new OfficeId(officeEntity.getOfficeId()))
                .raffles(officeRaffles)
                .active(officeEntity.getOfficeActive())
                .build();
    }

}
