package com.raffle.ticketing.system.ticket.service.domain.dto.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackTicketQuery {
    @NotNull
    private final UUID ticketTrackingId;
}
