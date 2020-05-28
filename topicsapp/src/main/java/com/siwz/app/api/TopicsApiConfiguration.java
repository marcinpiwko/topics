package com.siwz.app.api;

import com.siwz.app.api.security.JwtAuthenticationEntryPoint;
import com.siwz.app.api.translators.EmployeeTranslator;
import com.siwz.app.api.translators.UserTranslator;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class TopicsApiConfiguration {

    @Bean
    public EmployeeTranslator employeeTranslator() {
        return new EmployeeTranslator();
    }

    @Bean
    public UserTranslator userTranslator() {
        return new UserTranslator(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customConfiguration() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Topics REST API docs").description("Topics REST API documentation"));
    }
}
