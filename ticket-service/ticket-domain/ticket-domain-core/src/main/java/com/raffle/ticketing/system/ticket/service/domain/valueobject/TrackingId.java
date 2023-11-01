package com.raffle.ticketing.system.ticket.service.domain.valueobject;

import com.raffle.ticketing.system.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
