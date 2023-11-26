package com.raffle.ticketing.system.ticket.service.dataaccess.ticket.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketItemEntityId implements Serializable {
    private Long id;
    private TicketEntity ticket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketItemEntityId that = (TicketItemEntityId) o;
        return Objects.equals(id, that.id) && Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket);
    }
}
