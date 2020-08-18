package com.speakout.speakoutapi.customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllObserversForCustomer(String username);
    List<CustomerDto> getAllObservedForCustomer(String username);
}
