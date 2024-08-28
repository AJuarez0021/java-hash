package com.work.encrypt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class SwaggerConfig.
 *
 * @author ajuarez
 */
@Configuration
@Slf4j
public class SwaggerConfig {

    /**
     * Open API.
     *
     * @return the open API
     */
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Encrypt").version("1.0.0")
                .description("Aplicacion para encriptar cadenas y archivos (checksum) a MD5, SHA1, SHA256 y SHA512").contact(getContact()));
    }

    /**
     * Gets the contact.
     *
     * @return the contact
     */
    private Contact getContact() {
        return new Contact().name("Encrypt")
                .url("https://www.music.com/")
                .email("encrypt@mail.com");
    }
}