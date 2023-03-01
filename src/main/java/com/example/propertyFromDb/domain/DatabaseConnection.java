package com.example.propertyFromDb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@Getter
public class DatabaseConnection {
    private String url;
    private String username;
    private String password;
}
