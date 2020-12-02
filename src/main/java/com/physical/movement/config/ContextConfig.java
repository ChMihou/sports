package com.physical.movement.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ContextConfig {
    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return registry -> {
            ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error_page");
            registry.addErrorPages(page404);
        };
    }
}
