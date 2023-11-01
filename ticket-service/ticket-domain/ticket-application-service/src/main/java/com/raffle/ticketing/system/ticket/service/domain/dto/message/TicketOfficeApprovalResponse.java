package com.raffle.ticketing.system.ticket.service.domain.dto.message;

import com.raffle.ticketing.system.domain.valueobject.TicketApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TicketOfficeApprovalResponse {
    private String id;
    private String sagaId;
    private String ticketId;
    private String officeId;
    private Instant createdAt;
    private TicketApprovalStatus ticketApprovalStatus;
    private List<String> failureMessages;
}
