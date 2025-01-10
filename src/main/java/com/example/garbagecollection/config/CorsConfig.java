package com.example.garbagecollection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8081")); // Allow localhost
        config.setAllowedMethods(Collections.singletonList("*")); // Allow all HTTP methods
        config.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers
        config.setAllowCredentials(true); // Allow credentials
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}