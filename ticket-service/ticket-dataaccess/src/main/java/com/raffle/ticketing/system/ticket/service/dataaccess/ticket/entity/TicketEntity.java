package com.raffle.ticketing.system.ticket.service.dataaccess.ticket.entity;

import com.raffle.ticketing.system.domain.valueobject.TicketStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor // Spring bu classtan proxy object create edebilmesi için must !!
@AllArgsConstructor // Builder patterni kullanmak için tanımlanması must !!
@Table(name= "tickets")
@Entity
public class TicketEntity {

    @Id
    private UUID id;
    private UUID customerId;
    private UUID officeId;
    private UUID trackingId;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    private String failureMessages;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL) // TicketItemEntity'de gosterdiği alanın adı 'ticket' olmalı! CascadeType.ALL' ile bir Id ile ilişkili data silindiğinde bağlı oldugu id'ye baglı kayıt öbür tablodan da siliniyor!
    private List<TicketItemEntity> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
