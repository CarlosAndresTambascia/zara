package com.between.zara.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApiConfig() {
        return new OpenAPI().info(
                new Info()
                        .title("Interview project")
                        .version("1.0.0-SNAPSHOT")
                        .description("Project for the interview process")
                        .contact(new Contact().email("calos.tambascia@gmail.com").name("Carlos Tambascia"))
        );
    }
}
