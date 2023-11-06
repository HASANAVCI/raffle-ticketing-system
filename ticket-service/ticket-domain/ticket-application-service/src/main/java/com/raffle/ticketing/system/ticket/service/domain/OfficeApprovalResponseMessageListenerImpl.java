package com.raffle.ticketing.system.ticket.service.domain;

import com.raffle.ticketing.system.ticket.service.domain.dto.message.TicketOfficeApprovalResponse;
import com.raffle.ticketing.system.ticket.service.domain.ports.input.message.listener.ticketofficeapproval.OfficeApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class OfficeApprovalResponseMessageListenerImpl implements OfficeApprovalResponseMessageListener {
    @Override
    public void ticketApproved(TicketOfficeApprovalResponse officeApprovalResponse) {

    }

    @Override
    public void ticketRejected(TicketOfficeApprovalResponse officeApprovalResponse) {

    }
}
