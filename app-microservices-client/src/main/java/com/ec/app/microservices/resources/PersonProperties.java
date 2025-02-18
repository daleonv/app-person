package com.ec.app.microservices.resources;

import java.util.MissingResourceException;

public class PersonProperties {

    public static final PersonResourceResolver MESSAGE_RESOLVER =
            new PersonResourceResolver("com.ec.app.microservices.resources.person");

    private PersonProperties() {
    }

    /**
     * Get string
     *
     * @param key Property key
     * @return Property value
     */
    public static String getString(String key) {
        try {
            return MESSAGE_RESOLVER.getString(key);
        } catch (MissingResourceException e) {
            throw new RuntimeException("Clave no encontrada en el ResourceBundle: " + key, e);
        }
    }

}
