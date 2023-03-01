package com.example.propertyFromDb.repository;

import org.springframework.util.StringUtils;

import java.util.Properties;

public interface PropertyRepository {
    Properties getPropertiesFromDBByServiceName(String service);
}
