package com.sameer.medifind_backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI mediFindOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("MediFind API")

                        .description("Medicine Availability & Pharmacy Network Backend APIs")

                        .version("v1.0")

                        .contact(new Contact()

                                .name("Sameer Khan")

                                .email("your-email@example.com"))

                        .license(new License()

                                .name("MIT License")))

                .externalDocs(new ExternalDocumentation()

                        .description("Project Documentation")

                        .url("https://github.com/yourusername/medifind"));
    }
}