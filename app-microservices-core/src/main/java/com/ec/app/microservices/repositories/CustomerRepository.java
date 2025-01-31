package com.ec.app.microservices.repositories;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.entities.procedures.QAccountEntity;
import com.ec.app.entities.procedures.QTransactionEntity;
import com.ec.app.microservices.AccountStatusResponseVo;
import com.ec.app.microservices.config.JPAQueryDslBaseRepository;
import com.querydsl.core.types.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.ec.app.entities.procedures.QCustomerEntity.customerEntity;

/**
 * Repository for customer resources
 *
 * @author daleonv
 * @version 1.0
 */
@Lazy
@Repository
public class CustomerRepository extends JPAQueryDslBaseRepository<CustomerEntity> implements ICustomerRepository {
    public CustomerRepository() {
        super(CustomerEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerEntity> findCustomerList() {
        return from(customerEntity).select(Projections.bean(CustomerEntity.class,
                                customerEntity.customerId,
                                customerEntity.name,
                                customerEntity.gender,
                                customerEntity.identification,
                                customerEntity.address,
                                customerEntity.phone,
                                customerEntity.customerId,
                                customerEntity.password,
                                customerEntity.status,
                                customerEntity.age
                        )
                )
                .stream().toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CustomerEntity> findCustomer(Long customerId) {
        return from(customerEntity).select(Projections.bean(CustomerEntity.class,
                                customerEntity.customerId,
                                customerEntity.name,
                                customerEntity.gender,
                                customerEntity.identification,
                                customerEntity.address,
                                customerEntity.phone,
                                customerEntity.customerId,
                                customerEntity.password,
                                customerEntity.status,
                                customerEntity.age
                        )
                )
                .where(customerEntity.customerId.eq(customerId))
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CustomerEntity> findById(Long customerId) {
        return from(customerEntity)
                .where(customerEntity.customerId.eq(customerId))
                .select(Projections.bean(CustomerEntity.class,
                        customerEntity.name,
                        customerEntity.gender,
                        customerEntity.identification,
                        customerEntity.address,
                        customerEntity.phone,
                        customerEntity.customerId,
                        customerEntity.password,
                        customerEntity.status,
                        customerEntity.age
                ))
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountStatusResponseVo> findReportByFilters(Long customerId, Date initialDate, Date endDate) {
        QAccountEntity account = QAccountEntity.accountEntity;
        QTransactionEntity transaction = QTransactionEntity.transactionEntity;
        return from(customerEntity).select(Projections.bean(AccountStatusResponseVo.class,
                        transaction.date,
                        customerEntity.name,
                        account.accountNumber,
                        account.accountType,
                        account.initialBalance,
                        account.status,
                        transaction.transactionType,
                        transaction.balance
                ))
                .innerJoin(customerEntity.accounts, account)
                .innerJoin(account.transactions, transaction)
                .where(customerEntity.customerId.eq(customerId))
                .where(transaction.date.between(initialDate, endDate))
                .stream().toList();
    }

}
