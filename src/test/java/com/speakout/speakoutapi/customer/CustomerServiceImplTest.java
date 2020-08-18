package com.speakout.speakoutapi.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Spy
    CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @InjectMocks
    CustomerServiceImpl customerService;


    @Test
    void getAllObserversForCustomer() {
        //given
        Set<Customer> observers = new HashSet<>();
        observers.add(Customer.builder().username("test1").build());
        observers.add(Customer.builder().username("test2").build());
        Customer customer = Customer.builder()
                .username("username")
                .observers(observers)
                .build();
        given(customerRepository.findByUsername("username")).willReturn(Optional.of(customer));

        //when
        List<CustomerDto> customerDtoList = customerService.getAllObserversForCustomer("username");

        //then
        then(customerRepository).should().findByUsername("username");

        assertThat(observers
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList()))
                .isEqualTo(customerDtoList);
    }

    @Test
    void getAllObservedForCustomer() {
    }
}