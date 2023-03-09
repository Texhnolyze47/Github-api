package com.texhnolyze.githubapiv2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${auth.api}")
    private String token;

    @Bean
    public WebClient webClient() {
         return  WebClient.builder()
                .baseUrl("https://api.github.com/orgs/Escihu-Wizards")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                .build();
    }

}
