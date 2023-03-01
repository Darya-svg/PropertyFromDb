package com.example.propertyFromDb.config;

import com.example.propertyFromDb.domain.DatabaseConnection;
import com.example.propertyFromDb.repository.impl.PropertyRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.util.StringUtils;


@RequiredArgsConstructor
@ConditionalOnProperty(name = "configuration.enabled", havingValue = "true")
public class LoadDBPropertiesPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // get environment properties
        var url = environment.getProperty("configuration.datasource.url");
        var username = environment.getProperty("configuration.datasource.username");
        var password = environment.getProperty("configuration.datasource.password");
        var service = environment.getProperty("spring.application.name");

        if (!StringUtils.hasText(url)) {
            throw new RuntimeException("Database url is missed");
        }

        var propertyRepository = new PropertyRepositoryImpl(new DatabaseConnection(url, username, password));

        var propertiesFromDB = propertyRepository.getPropertiesFromDBByServiceName(service);
        var propertySource = new PropertiesPropertySource("databaseConnection", propertiesFromDB);

        environment.getPropertySources().addFirst(propertySource);

    }
}
