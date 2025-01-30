package com.ec.app.microservices.services;

import com.ec.app.microservices.AccountStatusRequestVo;
import com.ec.app.microservices.AccountStatusResponseVo;

import java.util.List;

/**
 * Service interface for reports resources
 *
 * @author dalonv
 * @version 1.0
 */
public interface IReportService {

    /**
     * Return account status
     *
     * @param filters AccountStatusRequestVo
     * @return List<AccountStatusRequestVo>
     */
    List<AccountStatusResponseVo> findAccountStatus(AccountStatusRequestVo filters);

}
