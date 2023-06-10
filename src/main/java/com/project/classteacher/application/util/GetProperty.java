package com.project.classteacher.application.util;

public class GetProperty {
    public static <T> T execute(String propertyName, T defaultValue) {
        T propertyValue = (T) System.getProperty(propertyName);
        if (propertyValue == null) {
            propertyValue = defaultValue;
        }
        return propertyValue;
    }
}
