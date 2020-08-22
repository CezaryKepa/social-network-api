package com.speakout.speakoutapi.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String username);

//  SELECT customer FROM user_observers inner join customer ON user_observers.observer_id = customer.id where user_observers.observed_id = :id
    @Query(value = "SELECT c FROM Customer c inner join c.observed o where o.id = :id")
    Set<Customer> findAllObservedForId(@Param("id") Long id);

}
