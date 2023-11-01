package com.raffle.ticketing.system.domain.valueobject;

import java.util.UUID;

public class RaffleId extends BaseId<UUID>{
    public RaffleId(final UUID value) {
        super(value);
    }

    public static RaffleId of(final UUID value) {
        return new RaffleId(value);
    }
}
