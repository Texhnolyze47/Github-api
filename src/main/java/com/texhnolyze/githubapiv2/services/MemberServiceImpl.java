package com.texhnolyze.githubapiv2.services;

import com.texhnolyze.githubapiv2.entities.Members;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MemberServiceImpl implements MemberService{

    private final WebClient  webClient;
    public MemberServiceImpl(WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public Mono<String> registerMember(@RequestBody Members members){
        return webClient.post()
                .uri("/invitations")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(members))
                .retrieve()
                .bodyToMono(Members.class)
                .map(response -> "Usuario registrado existosamente");
    }

}
