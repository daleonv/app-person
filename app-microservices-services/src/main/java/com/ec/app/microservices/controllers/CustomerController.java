package com.ec.app.microservices.controllers;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.microservices.CustomerVo;
import com.ec.app.microservices.config.Response;
import com.ec.app.microservices.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customers")
@Lazy
public class CustomerController {
    @Lazy
    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public ResponseEntity<Response<List<CustomerEntity>>> findCustomerList() {
        return new ResponseEntity<>(Response.<List<CustomerEntity>>builder()
                .data(customerService.findCustomerList())
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Response<CustomerEntity>> findCustomer(@PathVariable Long customerId) {
        return new ResponseEntity<>(Response.<CustomerEntity>builder()
                .data(customerService.findCustomer(customerId))
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response<Void>> saveCustomer(
            @RequestBody CustomerVo customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(Response.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("Creado con éxito")
                .build(), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Response<Void>> updateCustomer(@PathVariable Long customerId,
                                                         @RequestBody CustomerVo customer) {
        customer.setCustomerId(customerId);
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Actualizado con éxito")
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Response<Void>> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Eliminado con éxito")
                .build(), HttpStatus.OK);
    }

}
