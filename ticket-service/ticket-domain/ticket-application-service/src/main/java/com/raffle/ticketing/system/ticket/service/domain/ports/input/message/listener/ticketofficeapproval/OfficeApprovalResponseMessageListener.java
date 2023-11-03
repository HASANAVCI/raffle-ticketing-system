package com.raffle.ticketing.system.ticket.service.domain.ports.input.message.listener.ticketofficeapproval;

import com.raffle.ticketing.system.ticket.service.domain.dto.message.TicketOfficeApprovalResponse;

/**
    EventListener'lar bir tür özel ApplicationService türüdür.
 */
public interface OfficeApprovalResponseMessageListener {

    void ticketApproved(TicketOfficeApprovalResponse officeApprovalResponse);

    void ticketRejected(TicketOfficeApprovalResponse officeApprovalResponse);
}
