package com.raffle.ticketing.system.ticket.service.domain.ports.input.message.listener.payment;

import com.raffle.ticketing.system.ticket.service.domain.dto.message.PaymentResponse;

/**
    EventListener'lar bir tür özel ApplicationService türüdür.
 */
public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCanceled(PaymentResponse paymentResponse);
}
