package com.raffle.ticketing.system.ticket.service.dataaccess.office.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficeEntityId implements Serializable {

    private UUID officeId;
    private UUID raffleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeEntityId that = (OfficeEntityId) o;
        return Objects.equals(officeId, that.officeId) && Objects.equals(raffleId, that.raffleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId, raffleId);
    }
}
