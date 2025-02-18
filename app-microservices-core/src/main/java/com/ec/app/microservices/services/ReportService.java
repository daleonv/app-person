package com.ec.app.microservices.services;

import com.ec.app.microservices.AccountStatusRequestVo;
import com.ec.app.microservices.AccountStatusResponseVo;
import com.ec.app.microservices.common.ICommonService;
import com.ec.app.microservices.constants.PersonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Service for report resources
 *
 * @author daleonv
 * @version 1.0
 */
@Lazy
@Service
@Transactional
public class ReportService implements IReportService {

    @Lazy
    @Autowired
    private ICustomerService customerService;

    @Lazy
    @Autowired
    private ICommonService commonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountStatusResponseVo> findAccountStatus(AccountStatusRequestVo filters) {
        return customerService.findAccountStatus(filters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] downloadAccountStatus(AccountStatusRequestVo filters) {
        List<AccountStatusResponseVo> report = customerService.findAccountStatus(filters);
        if (report.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        report.forEach(data -> {
            SimpleDateFormat formatter = new SimpleDateFormat(PersonConstants.YEAR_MONTH_DAY_PATTERN);
            String dateString = formatter.format(data.getDate());
            data.setDateString(dateString);
        });
        return commonService.generateReport(report, PersonConstants.JRXML_CLIENT_REPORT, filters.getExtension());
    }
}
