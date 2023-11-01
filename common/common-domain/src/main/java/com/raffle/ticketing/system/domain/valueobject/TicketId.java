package com.raffle.ticketing.system.domain.valueobject;

import java.util.UUID;

public class TicketId extends BaseId<UUID> {
    public TicketId(UUID value) {
        super(value);
    }
}
