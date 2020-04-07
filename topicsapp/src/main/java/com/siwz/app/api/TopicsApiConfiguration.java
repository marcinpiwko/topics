package com.siwz.app.api;

import com.siwz.app.api.translators.EmployeeTranslator;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicsApiConfiguration {

    @Bean
    public EmployeeTranslator employeeTranslator() {
        return new EmployeeTranslator();
    }

    @Bean
    public OpenAPI customConfiguration() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Topics REST API docs").description("Topics REST API documentation"));
    }
}
