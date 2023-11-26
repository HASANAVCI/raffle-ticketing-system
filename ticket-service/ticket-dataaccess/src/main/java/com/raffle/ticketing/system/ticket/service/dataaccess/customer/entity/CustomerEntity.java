package com.raffle.ticketing.system.ticket.service.dataaccess.customer.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "ticket_customer_m_view", schema = "customer") //
@Entity
//Bu entity sadece müşterinin var olup olmadığını kontrol etmek amaçlı..
public class CustomerEntity {
    @Id
    private UUID id;
}
