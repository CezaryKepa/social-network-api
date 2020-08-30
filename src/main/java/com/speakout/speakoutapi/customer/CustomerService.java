package com.speakout.speakoutapi.customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllObserversForCustomer(String username);
    List<CustomerDto> getAllObservedForCustomer(String username);
    List<CustomerDto> startObservingCustomer(String username);
    List<CustomerDto> stopObservingCustomer(String username);
    Customer getAuthenticatedCustomer();
    Customer findCustomerByUsername(String username);
}
