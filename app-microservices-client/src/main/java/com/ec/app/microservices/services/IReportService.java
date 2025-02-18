package com.ec.app.microservices.services;

import com.ec.app.microservices.AccountStatusRequestVo;
import com.ec.app.microservices.AccountStatusResponseVo;

import java.util.List;

/**
 * Service interface for reports resources
 *
 * @author daleonv
 * @version 1.0
 */
public interface IReportService {

    /**
     * Return account report
     *
     * @param filter AccountStatusRequestVo
     * @return List<AccountStatusRequestVo>
     */
    List<AccountStatusResponseVo> findAccountStatus(AccountStatusRequestVo filter);

    /**
     * Download account report
     *
     * @param filter AccountStatusRequestVo
     * @return byte[]
     */
    byte[] downloadAccountStatus(AccountStatusRequestVo filter);

}
