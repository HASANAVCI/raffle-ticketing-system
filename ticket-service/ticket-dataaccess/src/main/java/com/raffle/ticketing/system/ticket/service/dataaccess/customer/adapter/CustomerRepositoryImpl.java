package com.raffle.ticketing.system.ticket.service.dataaccess.customer.adapter;

import com.raffle.ticketing.system.ticket.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import com.raffle.ticketing.system.ticket.service.dataaccess.customer.repository.CustomerJpaRespository;
import com.raffle.ticketing.system.ticket.service.domain.entity.Customer;
import com.raffle.ticketing.system.ticket.service.domain.ports.output.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRespository customerJpaRespository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRespository customerJpaRespository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRespository = customerJpaRespository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }


    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRespository.findById(customerId).map(customerDataAccessMapper::customerEntityToCustomer);
    }
}
