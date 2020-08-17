package com.speakout.speakoutapi.customer;


import com.speakout.speakoutapi.base_entity.DateMapper;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}