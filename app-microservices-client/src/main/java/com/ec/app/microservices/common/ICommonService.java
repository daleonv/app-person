package com.ec.app.microservices.common;

import java.util.List;

/**
 * Service interface for common report resources
 *
 * @author daleonv
 * @version 1.0
 */

public interface ICommonService {

    /**
     * Generate report
     *
     * @param reportData List<?>
     * @param jrxmlPath  String
     * @param extension  String
     * @return byte[]
     */
    byte[] generateReport(List<?> reportData, String jrxmlPath, String extension);
}
