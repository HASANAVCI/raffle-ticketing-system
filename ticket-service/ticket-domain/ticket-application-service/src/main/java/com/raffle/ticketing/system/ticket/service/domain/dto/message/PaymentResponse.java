package com.raffle.ticketing.system.ticket.service.domain.dto.message;

import com.raffle.ticketing.system.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/*
    PaymentService'den TicketService'e dönen responsu tutacaktır.
 */
@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {
    private String id;

    /*
        Serviceler arasında mesajda kullanacagız.
     */
    private String sagaId;
    private String ticketId;
    private String paymentId;
    private String customerId;
    private BigDecimal price;
    private Instant createdAt;
    private PaymentStatus paymentStatus;
    private List<String> failureMessages;
}
