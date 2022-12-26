package com.texhnolyze.githubapiv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

/**
 * Esta clase se encarga de tener los métodos necesarios para consultar una lista integrantes y
 * enviar invitaciones
 */
@RestController
@RequestMapping("/users")
public class GithubUserController {

    @Autowired
    private MemberService memberService;


    @GetMapping("/")
    public String index(){
        return "Usa la url https://github-invitation.azurewebsites.net/users/all para usa la api";
    }

    @CrossOrigin("https://escihu-wizards.netlify.app")
    @PostMapping("/add")
    public Members addMembers(@Valid @RequestBody Members user) {
        return this.memberService.addMembers(user);
    }



    @GetMapping("/all")
    public Flux<GithubUser> getAllMembers() {
        return this.memberService.getAllMembers();
    }
}
