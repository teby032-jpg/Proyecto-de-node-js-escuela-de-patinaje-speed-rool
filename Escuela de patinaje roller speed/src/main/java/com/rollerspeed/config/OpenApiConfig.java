package com.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Roller Speed API")
                        .description("API REST para la gestión de la escuela de patinaje Roller Speed.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Roller Speed")
                                .url("https://github.com/teby032-jpg/Proyecto-de-node-js-escuela-de-patinaje-speed-rool")));
    }
}
