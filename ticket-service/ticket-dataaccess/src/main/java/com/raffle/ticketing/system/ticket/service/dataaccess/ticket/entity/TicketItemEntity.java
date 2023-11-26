package com.raffle.ticketing.system.ticket.service.dataaccess.ticket.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@IdClass(TicketItemEntityId.class) // For multi column primary key..
@AllArgsConstructor
@Table(name= "ticket_items")
@Entity
public class TicketItemEntity {
    @Id
    private Long id;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TICKET_ID") //Foreign Key
    private TicketEntity ticket;

    private UUID raffleId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketItemEntity that = (TicketItemEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket);
    }
}
