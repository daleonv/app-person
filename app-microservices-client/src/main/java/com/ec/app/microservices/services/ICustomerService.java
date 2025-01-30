package com.ec.app.microservices.services;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.microservices.AccountStatusRequestVo;
import com.ec.app.microservices.AccountStatusResponseVo;
import com.ec.app.microservices.CustomerVo;

import java.util.List;

/**
 * Service interface for customer resources
 *
 * @author dalonv
 * @version 1.0
 */
public interface ICustomerService {

    /**
     * Return customer list information
     *
     * @return List<CustomerEntity>
     */
    List<CustomerEntity> findCustomerList();

    /**
     * Save new customer
     *
     * @param customer CustomerVo
     */
    void saveCustomer(CustomerVo customer);

    /**
     * Update customer information.
     *
     * @param customer CustomerVo
     */
    void updateCustomer(CustomerVo customer);

    /**
     * Delete a customer.
     *
     * @param customerId Long
     */
    void deleteCustomer(Long customerId);

    /**
     * Return account status
     *
     * @param filters AccountStatusRequestVo
     * @return List<AccountStatusResponseVo>
     */
    List<AccountStatusResponseVo> findAccountStatus(AccountStatusRequestVo filters);
}
