package com.speakout.speakoutapi.customer;

import com.speakout.speakoutapi.comment.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController controller;


    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    void getAllObservedForCustomer() throws Exception {
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(CustomerDto.builder().username("user1").build());
        customers.add(CustomerDto.builder().username("user2").build());
        customers.add(CustomerDto.builder().username("user3").build());
        given(customerService.getAllObservedForCustomer("user")).willReturn(customers);
        mockMvc.perform(get("/api/customer/observed")
                    .param("username", "user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllObserversForCustomer() throws Exception {
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(CustomerDto.builder().username("user1").build());
        customers.add(CustomerDto.builder().username("user2").build());
        customers.add(CustomerDto.builder().username("user3").build());
        given(customerService.getAllObserversForCustomer("user")).willReturn(customers);
        mockMvc.perform(get("/api/customer/observers")
                      .param("username", "user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void startObservingCustomer() throws Exception {
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(CustomerDto.builder().username("user1").build());
        customers.add(CustomerDto.builder().username("user2").build());
        customers.add(CustomerDto.builder().username("user3").build());
        given(customerService.startObservingCustomer("user3")).willReturn(customers);
        mockMvc.perform(put("/api/customer/start-observing")
                .param("username", "user3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void stopObservingCustomer() throws Exception {
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(CustomerDto.builder().username("user1").build());
        customers.add(CustomerDto.builder().username("user2").build());
        given(customerService.stopObservingCustomer("user3")).willReturn(customers);
        mockMvc.perform(put("/api/customer/stop-observing")
                .param("username", "user3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}