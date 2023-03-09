package com.texhnolyze.githubapiv2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfigurations implements WebMvcConfigurer {

    @Value("${allowed-origin}")
    private String allowedOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/users")
                .allowedOrigins(allowedOrigin)
                .allowedHeaders("POST","GET");
    }
}
