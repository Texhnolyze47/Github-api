package com.texhnolyze.githubapiv2.controller;

import com.texhnolyze.githubapiv2.entities.Members;
import com.texhnolyze.githubapiv2.exceptions.MemberRegistrationException;
import com.texhnolyze.githubapiv2.services.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


/**
 * Esta clase se encarga de tener los métodos necesarios para consultar una lista integrantes y
 * enviar invitaciones
 */
@RestController
@RequestMapping("/users")
public class GithubUserController {

    private final MemberService memberService;

    public GithubUserController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody Members members){
        return memberService.registerMember(members)
                .map( response -> "Usuario registrado")
                .map(ResponseEntity::ok)
                .onErrorMap(HttpMessageNotReadableException.class, ex -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

}
