
package com.example.banking.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.web.server.ServerHttpSecurity;

/**
 * Security configuration for Spring Cloud Gateway (reactive/WebFlux).
 * - For dev: permit all.
 * - In prod: replace with JWT validation, route-level authorization, etc.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // Disable CSRF for stateless APIs routed via gateway
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);

        // Allow everything during development.
        // Replace with .authorizeExchange() rules for production.
        http.authorizeExchange(exchange -> exchange
                .anyExchange().permitAll()
        );

        return http.build();
    }
}
