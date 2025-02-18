package com.ec.app.microservices.constants;

import com.ec.app.microservices.resources.PersonProperties;

/**
 * Person constants values used throughout the application
 *
 * @author daleonv
 * @version 1.0
 */

public class PersonConstants {

    public static final String CREATED_MESSAGE = PersonProperties.
            getString("com.ec.sig.person.created.status.message");
    public static final String UPDATED_MESSAGE = PersonProperties.
            getString("com.ec.sig.person.updated.status.message");
    public static final String DELETED_MESSAGE = PersonProperties.
            getString("com.ec.sig.person.deleted.status.message");
    public static final String JRXML_CLIENT_REPORT = PersonProperties.
            getString("com.ec.sig.person.jrxml.client.report.name");
    public static final String YEAR_MONTH_DAY_PATTERN = PersonProperties.
            getString("com.ec.sig.person.pattern.year.month.day");
}
