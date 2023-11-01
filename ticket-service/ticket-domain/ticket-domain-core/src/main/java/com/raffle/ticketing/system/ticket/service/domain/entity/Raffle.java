package com.raffle.ticketing.system.ticket.service.domain.entity;

import com.raffle.ticketing.system.domain.entity.BaseEntity;
import com.raffle.ticketing.system.domain.valueobject.Money;
import com.raffle.ticketing.system.domain.valueobject.RaffleId;
import com.raffle.ticketing.system.domain.valueobject.TicketId;

/*
    For Product
 */
public class Raffle extends BaseEntity<RaffleId> {
    private String name;
    private Money price;

    public Raffle(RaffleId raffleId, String name, Money price) {
        super.setId(raffleId);
        this.name = name;
        this.price = price;
    }

    public Raffle(RaffleId raffleId) {
        super.setId(raffleId);
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
