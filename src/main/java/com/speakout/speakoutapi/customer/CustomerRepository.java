package com.speakout.speakoutapi.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String username);

    //TODO REFACTOR TO JPQL
    @Query(value = "SELECT customer FROM user_observers inner join customer ON user_observers.observer_id = customer.id where user_observers.observed_id = :id",
            nativeQuery = true)
    Set<Customer> findAllObservedForUsername(@Param("id") String id);

}
