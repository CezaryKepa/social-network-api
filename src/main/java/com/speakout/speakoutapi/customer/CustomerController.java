package com.speakout.speakoutapi.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/customer")
@RestController
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/observed")
    public List<CustomerDto> getAllObservedForCustomer(@RequestParam String username){
        return customerService.getAllObservedForCustomer(username);
    }
    @GetMapping("/observers")
    public List<CustomerDto> getAllObserversForCustomer(@RequestParam String username){
        return customerService.getAllObserversForCustomer(username);
    }
    @PutMapping("/start-observing")
    public List<CustomerDto> startObservingCustomer(@RequestParam String username){
        return customerService.startObservingCustomer(username);
    }
    @PutMapping("/stop-observing")
    public List<CustomerDto> stopObservingCustomer(@RequestParam String username){
        return customerService.stopObservingCustomer(username);
    }


}
