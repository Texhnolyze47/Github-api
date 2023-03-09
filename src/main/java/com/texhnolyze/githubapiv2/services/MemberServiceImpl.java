package com.texhnolyze.githubapiv2.services;

import com.texhnolyze.githubapiv2.entities.Members;

import com.texhnolyze.githubapiv2.exceptions.MemberRegistrationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class MemberServiceImpl implements MemberService{

    private final WebClient  webClient;
    public MemberServiceImpl(WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public Mono<Members> registerMember(@RequestBody Members members){
        return webClient.post()
                .uri("/invitations")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(members))
                .retrieve()
                .bodyToMono(Members.class)
                .onErrorMap(WebClientResponseException.class, ex -> new MemberRegistrationException("Error al leer la solicitud JSON " + ex.getMessage()));
    }

}
