package com.speakout.speakoutapi.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public List<CustomerDto> getAllObserversForCustomer(String username) {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        Customer customerEntity = customer.orElseThrow(CustomerNotFoundException::new);
        Set<Customer> observers = customerEntity.getObservers();
        return observers
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getAllObservedForCustomer(String username) {
        return null;
    }
}
