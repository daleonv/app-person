package com.ec.app.microservices.services;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.entities.procedures.PersonEntity;
import com.ec.app.microservices.AccountStatusRequestVo;
import com.ec.app.microservices.AccountStatusResponseVo;
import com.ec.app.microservices.CustomerVo;
import com.ec.app.microservices.repositories.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service for customer resources
 *
 * @author daleonv
 * @version 1.0
 */
@Lazy
@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Lazy
    @Autowired
    private ICustomerRepository customerRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerEntity> findCustomerList() {
        return customerRepository.findCustomerList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerEntity findCustomer(Long customerId) {
        return customerRepository.findCustomer(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCustomer(CustomerVo customer) {
        customerRepository.save(CustomerEntity.builder()
                .password(customer.getPassword())
                .status(customer.getStatus())
                .name(customer.getName())
                .gender(PersonEntity.Gender.valueOf(customer.getGender()))
                .age(customer.getAge())
                .identification(customer.getIdentification())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCustomer(CustomerVo customer) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customer.getCustomerId());
        if (optionalCustomer.isPresent()) {
            CustomerEntity existingCustomer = getCustomerEntity(customer,
                    optionalCustomer.orElse(new CustomerEntity()));
            customerRepository.update(existingCustomer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomer(Long customerId) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            optionalCustomer.ifPresent(customer -> customerRepository.delete(customer));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountStatusResponseVo> findAccountStatus(AccountStatusRequestVo filters) {
        return customerRepository.findReportByFilters(filters.getCustomerId(),
                filters.getInitialDate(), filters.getEndDate());
    }

    private static CustomerEntity getCustomerEntity(CustomerVo customer, CustomerEntity optionalCustomer) {
        optionalCustomer.setPassword(customer.getPassword());
        optionalCustomer.setStatus(customer.getStatus());
        optionalCustomer.setName(customer.getName());
        optionalCustomer.setGender(PersonEntity.Gender.valueOf(customer.getGender()));
        optionalCustomer.setAge(customer.getAge());
        optionalCustomer.setIdentification(customer.getIdentification());
        optionalCustomer.setAddress(customer.getAddress());
        optionalCustomer.setPhone(customer.getPhone());
        return optionalCustomer;
    }
}
