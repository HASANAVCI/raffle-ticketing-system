package com.raffle.ticketing.system.ticket.service.domain.entity;

import com.raffle.ticketing.system.domain.entity.BaseEntity;
import com.raffle.ticketing.system.domain.valueobject.Money;
import com.raffle.ticketing.system.domain.valueobject.TicketId;
import com.raffle.ticketing.system.ticket.service.domain.valueobject.TicketItemId;

public class TicketItem extends BaseEntity<TicketItemId> {
    private TicketId ticketId;
    private Raffle raffle;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    private TicketItem(Builder builder) {
        super.setId(builder.ticketItemId);
        raffle = builder.raffle;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }


    public TicketId getTicketId() {
        return ticketId;
    }

    public Raffle getRaffle() {
        return raffle;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(raffle.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }

    void initializeTicketItem(TicketId ticketId, TicketItemId ticketItemId) {
        this.ticketId = ticketId;
        super.setId(ticketItemId);
    }

    public static final class Builder {
        private TicketItemId ticketItemId;
        private Raffle raffle;
        private int quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder ticketItemId(TicketItemId val) {
            ticketItemId = val;
            return this;
        }

        public Builder raffle(Raffle val) {
            raffle = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public TicketItem build() {
            return new TicketItem(this);
        }
    }
}
