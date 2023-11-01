package com.raffle.ticketing.system.ticket.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TicketItem {
    @NotNull
    private final UUID raffleId;
    @NotNull
    private final Integer quantity;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final BigDecimal subTotal;
}
