package com.ec.app.microservices.controllers;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.microservices.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;


    @Test
    public void testGetCustomer_Success() throws Exception {
        CustomerEntity customer = CustomerEntity
                .builder()
                .customerId(2L)
                .name("John Doe")
                .build();

        when(customerService.findCustomer(2L)).thenReturn(customer);

        mockMvc.perform(get("/customers/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.customerId").value(2))
                .andExpect(jsonPath("$.data.name").value("John Doe"));
    }


    @Test
    public void testGetCustomer_NotFound() throws Exception {
        when(customerService.findCustomer(1000L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/customers/1000"))
                .andExpect(status().isNotFound());
    }

}
