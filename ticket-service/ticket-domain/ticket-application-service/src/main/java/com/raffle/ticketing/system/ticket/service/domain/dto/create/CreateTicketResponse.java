package com.raffle.ticketing.system.ticket.service.domain.dto.create;

import com.raffle.ticketing.system.domain.valueobject.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateTicketResponse {
    @NotNull
    private final UUID trackingId;
    @NotNull
    private final TicketStatus ticketStatus;
    @NotNull
    private final String message;
}
