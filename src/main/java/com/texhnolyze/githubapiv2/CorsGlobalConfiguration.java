package com.texhnolyze.githubapiv2;

import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

public class CorsGlobalConfiguration implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("https://escihu-wizards.netlify.app")
                .allowedMethods("GET","POST")
                .maxAge(3600);


    }
}
