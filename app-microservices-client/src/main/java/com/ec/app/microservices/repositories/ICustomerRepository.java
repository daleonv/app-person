package com.ec.app.microservices.repositories;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.microservices.AccountStatusResponseVo;
import com.ec.app.microservices.config.IQueryDslBaseRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for customer resources
 *
 * @author daleonv
 * @version 1.0
 */
public interface ICustomerRepository extends IQueryDslBaseRepository<CustomerEntity> {

    /**
     * Return customer list information
     *
     * @return List<CustomerEntity>
     */
    List<CustomerEntity> findCustomerList();

    /**
     * Return customer information
     *
     * @return CustomerEntity
     */
    Optional<CustomerEntity> findCustomer(Long customerId);


    /**
     * Return customer information by customerId
     *
     * @return Optional<CustomerEntity>
     */
    Optional<CustomerEntity> findById(Long customerId);


    /**
     * Return account status
     *
     * @param customerId  Long
     * @param initialDate Date
     * @param endDate     Date
     * @return List<AccountStatusResponseVo>
     */
    List<AccountStatusResponseVo> findReportByFilters(Long customerId, Date initialDate, Date endDate);
}
