package com.raffle.ticketing.system.ticket.service.dataaccess.customer.mapper;

import com.raffle.ticketing.system.domain.valueobject.CustomerId;
import com.raffle.ticketing.system.ticket.service.dataaccess.customer.entity.CustomerEntity;
import com.raffle.ticketing.system.ticket.service.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
