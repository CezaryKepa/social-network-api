package com.speakout.speakoutapi.customer;

import com.speakout.speakoutapi.user.ApplicationUser;
import com.speakout.speakoutapi.user.ApplicationUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @Mock
    ApplicationUserRepository userRepository;

    @Mock
    SecurityContext securityContext;

    @Mock
    Authentication authentication;

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
                .observed(observers)
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
        //given
        Set<Customer> observers = new HashSet<>();
        observers.add(Customer.builder().username("test1").build());
        observers.add(Customer.builder().username("test2").build());
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("username");
        given(customerRepository.findByUsername("username")).willReturn(Optional.of(customer));
        given(customerRepository.findAllObservedForId(1L)).willReturn(observers);



        //when
        List<CustomerDto> customerDtoList = customerService.getAllObservedForCustomer("username");

        //then
        then(customerRepository).should().findByUsername("username");
        then(customerRepository).should().findAllObservedForId(1L);
        assertThat(observers
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList()))
                .isEqualTo(customerDtoList);
    }

    @Test
    void startObservingCustomer() {
        //given
        Set<Customer> observed = new HashSet<>();
        observed.add(Customer.builder().username("test1").build());
        observed.add(Customer.builder().username("test2").build());
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("username");
        Customer authCustomer = Customer.builder().username("AuthenticatedCustomer").observed(observed).build();
        ApplicationUser user = new ApplicationUser();
        user.setEmail("email");
        user.setCustomer(authCustomer);
        given(securityContext. getAuthentication()).willReturn(authentication);
        given(authentication.getPrincipal()).willReturn("email");
        SecurityContextHolder.setContext(securityContext);
        given(customerRepository.findByUsername("username")).willReturn(Optional.of(customer));
        given(userRepository.findByEmail("email")).willReturn(Optional.of(user));

        //when
        List<CustomerDto> customerDtoList = customerService.startObservingCustomer("username");

        //then
        then(customerRepository).should().findByUsername("username");
        then(userRepository).should().findByEmail("email");
        then(customerRepository).should().save(authCustomer);
        observed.add(customer);

        assertThat(observed
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList()))
                .isEqualTo(customerDtoList);

    }

    @Test
    void stopObservingCustomer() {
        //given
        Set<Customer> observed = new HashSet<>();
        observed.add(Customer.builder().username("test1").build());
        observed.add(Customer.builder().username("test2").build());
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("username");
        Customer authCustomer = Customer.builder().username("AuthenticatedCustomer").observed(observed).build();
        ApplicationUser user = new ApplicationUser();
        user.setEmail("email");
        user.setCustomer(authCustomer);
        given(securityContext. getAuthentication()).willReturn(authentication);
        given(authentication.getPrincipal()).willReturn("email");
        SecurityContextHolder.setContext(securityContext);
        given(customerRepository.findByUsername("username")).willReturn(Optional.of(customer));
        given(userRepository.findByEmail("email")).willReturn(Optional.of(user));

        //when
        List<CustomerDto> customerDtoList = customerService.stopObservingCustomer("username");

        //then
        then(customerRepository).should().findByUsername("username");
        then(userRepository).should().findByEmail("email");
        then(customerRepository).should().save(authCustomer);
        observed.remove(customer);

        assertThat(observed
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList()))
                .isEqualTo(customerDtoList);
    }
}