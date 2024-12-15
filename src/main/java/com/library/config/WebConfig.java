package com.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Match all /api/ endpoints
                        .allowedOrigins("http://localhost:3000") // Allow frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // HTTP methods allowed
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow credentials if needed
            }
        };
    }
}