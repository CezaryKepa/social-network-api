package com.speakout.speakoutapi.customer;

import com.speakout.speakoutapi.user.ApplicationUser;
import com.speakout.speakoutapi.user.ApplicationUserRepository;
import com.speakout.speakoutapi.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ApplicationUserRepository userRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ApplicationUserRepository userRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDto> getAllObserversForCustomer(String username) {
        Customer customerEntity = findCustomerByUsername(username);
        Set<Customer> observers = customerEntity.getObserved();

        return mapCustomerSetToCustomerDTOList(observers);
    }

    @Override
    public List<CustomerDto> getAllObservedForCustomer(String username) {
        Customer customerEntity = findCustomerByUsername(username);
        Set<Customer> observed = customerRepository.findAllObservedForId(customerEntity.getId());

        return mapCustomerSetToCustomerDTOList(observed);
    }

    @Override
    public List<CustomerDto> startObservingCustomer(String username) {
        Customer customer =  getAuthenticatedCustomer();

        Customer observedEntity = findCustomerByUsername(username);

        Set<Customer> observedCustomers = customer.getObserved();
        observedCustomers.add(observedEntity);

        customer.setObserved(observedCustomers);
        customerRepository.save(customer);

        return mapCustomerSetToCustomerDTOList(observedCustomers);
    }
    @Override
    public List<CustomerDto> stopObservingCustomer(String username) {
        Customer customer =  getAuthenticatedCustomer();

        Customer observedEntity = findCustomerByUsername(username);

        Set<Customer> observedCustomers = customer.getObserved();
        observedCustomers.remove(observedEntity);

        customer.setObserved(observedCustomers);
        customerRepository.save(customer);

        return mapCustomerSetToCustomerDTOList(observedCustomers);
    }

    @Override
    public Customer getAuthenticatedCustomer(){
        String principal = (String) SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        Optional<ApplicationUser> user = userRepository.findByEmail(principal);
        ApplicationUser userEntity = user.orElseThrow(UserNotFoundException::new);
        return userEntity.getCustomer();
    }

    Customer findCustomerByUsername(String username){
        Optional<Customer> observed = customerRepository.findByUsername(username);
        return observed.orElseThrow(CustomerNotFoundException::new);
    }

    private List<CustomerDto> mapCustomerSetToCustomerDTOList(Set<Customer> set){
        return set
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }
}
