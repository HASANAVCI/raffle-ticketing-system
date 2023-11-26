package com.raffle.ticketing.system.ticket.service.application.exception.handler;

import com.raffle.ticketing.system.application.handler.ErrorDTO;
import com.raffle.ticketing.system.application.handler.GlobalExceptionHandler;
import com.raffle.ticketing.system.ticket.service.domain.exception.TicketDomainException;
import com.raffle.ticketing.system.ticket.service.domain.exception.TicketNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class TicketGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody // Methodun dönüş türü Bir HTTP response olarak sarılacaktır..
    @ExceptionHandler(value = {TicketDomainException.class}) //Bu exception atıldığında bu metot çağrılacak..
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(TicketDomainException ticketDomainException) {
        log.error(ticketDomainException.getMessage(), ticketDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ticketDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {TicketNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(TicketNotFoundException ticketNotFoundException) {
        log.error(ticketNotFoundException.getMessage(), ticketNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ticketNotFoundException.getMessage())
                .build();
    }
}
