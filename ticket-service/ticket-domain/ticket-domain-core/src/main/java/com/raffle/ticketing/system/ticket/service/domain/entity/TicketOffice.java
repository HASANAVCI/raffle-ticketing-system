package com.raffle.ticketing.system.ticket.service.domain.entity;

import com.raffle.ticketing.system.domain.entity.AggregateRoot;
import com.raffle.ticketing.system.domain.valueobject.OfficeId;

import java.util.List;

/*
    Bilet Gişe for ticket sales and operations
 */
public class TicketOffice extends AggregateRoot<OfficeId> {
    private final List<Raffle> raffles;
    private final boolean active;

    private TicketOffice(Builder builder) {
        super.setId(builder.officeId);
        raffles = builder.raffles;
        active = builder.active;
    }


    public List<Raffle> getRaffles() {
        return raffles;
    }

    public boolean isActive() {
        return active;
    }

    public static final class Builder {
        private OfficeId officeId;
        private List<Raffle> raffles;
        private boolean active;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder officeId(OfficeId val) {
            officeId = val;
            return this;
        }

        public Builder raffles(List<Raffle> val) {
            raffles = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public TicketOffice build() {
            return new TicketOffice(this);
        }
    }
}
