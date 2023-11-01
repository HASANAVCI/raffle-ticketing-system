package com.raffle.ticketing.system.ticket.service.domain.dto.track;

import com.raffle.ticketing.system.domain.valueobject.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackTicketResponse {
    @NotNull
    private final UUID ticketTrackingId;
    @NotNull
    private final TicketStatus ticketStatus;
    private final List<String> failureMessages;
}
