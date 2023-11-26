package com.raffle.ticketing.system.ticket.service.dataaccess.office.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OfficeEntityId.class)
@Table(name= "ticket_office_m_view", schema = "office")
@Entity
public class OfficeEntity {

    @Id
    private UUID officeId;
    @Id
    private UUID raffleId;
    private String officeName;
    private Boolean officeActive;
    private String raffleName;
    private BigDecimal rafflePrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeEntity that = (OfficeEntity) o;
        return Objects.equals(officeId, that.officeId) && Objects.equals(raffleId, that.raffleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId, raffleId);
    }
}
