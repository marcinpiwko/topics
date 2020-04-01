package com.siwz.app.api;

import com.siwz.app.api.translators.EmployeeTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicsApiConfiguration {

    @Bean
    public EmployeeTranslator employeeTranslator() {
        return new EmployeeTranslator();
    }
}
