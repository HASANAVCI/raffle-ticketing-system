package com.raffle.ticketing.system.ticket.service.domain;

import com.raffle.ticketing.system.ticket.service.domain.entity.Ticket;
import com.raffle.ticketing.system.ticket.service.domain.entity.TicketOffice;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketCancelledEvent;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketCreatedEvent;
import com.raffle.ticketing.system.ticket.service.domain.event.TicketPaidEvent;

import java.util.List;

/*
    DomainEventsHandlingProcess için bu yöntem kullanıldı. Eventler, entityde veya domain servicede yani domain core'da oluşturulmaya devam edecektir.
    Fakat event firing(olay tetiklemesi) ApplicationService de olacaktır.

    Burada farklı bir yöntem uygulayıp ApplicationService'in doğrudan entityleri ile konuşmaması için Domainimizin önüne DomainService'i oluşturuyoruz.

    Domain core module'de yalnızca business logic'e odaklanması gereken gereksiz işler yapılmasını önlemek için REPOSITORY çağrılarını ApplicationSerice'e devretmeyi tercih ediyorum.

    Doamin'in event publishing veya event tracking hakkında hiçbir bilgisi yoktur. Yalnızca business logic'i çalıştırdıktan sonra eventleri oluşturur ve döndürür.

    ApplicationService, eventlerin ne zaman ve nasıl yükseltileceğine(raise) karar verecektir. Domain Events Handling Process süreci söz konusu olduğunda, eventlerin DomainEntity veya DomainService'de başlatılması
    gibi farklı bir yaklaşım görmüş olabilirsiniz. Ancak bahsettiğim nedenlerden dolayı domain core module'den domain event oluşturup geri döndürmeyi ve event-firing process'yi ApplicationSerivce'ye devretmeyi tercih ediyorum.

    Burada bahsetmem gereken ikinci bir şey var. Domain Eventler'i Entity veya DomainService'de nerede oluşturmalıyım? Doğal olarak Domain Entityler, doğrudan ApplicationSerivce'den çağrılabileceği için ilgili eventleri oluşturmaktan sorumludur
    çünkü DDD'de DomainSerice kullanmak zorunlu değildir. Belirtildiği gibi, business logic'de birden fazla Aggragates'ye erişiminiz varsa veya herhangi bir Entity class'a uymayan bir mantığınız varsa, Domain Service gereklidir.
 */
public interface TicketDomainService {
    TicketCreatedEvent validateAndInitializeTicket(Ticket ticket, TicketOffice ticketOffice);

    TicketPaidEvent payTicket(Ticket ticket);

    void approveTicket(Ticket ticket);

    TicketCancelledEvent cancelTicketPayment(Ticket ticket, List<String> failureMessages);

    void cancelTicket(Ticket ticket, List<String> failureMessages);

}
