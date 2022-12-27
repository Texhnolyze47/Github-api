package com.texhnolyze.githubapiv2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(GithubUserController.class);

    @GetMapping("/")
    public String index(){
        return "Usa la url https://github-invitation.azurewebsites.net/users/all para usa la api";
    }

    @CrossOrigin("https://escihu-wizards.netlify.app")
    @PostMapping("/add")
    public Members addMembers(@Valid @RequestBody Members user) {
        logger.warn("Se registro de manera correcta el " + user.getEmail());
        return this.memberService.addMembers(user);
    }



    @GetMapping("/all")
    public Flux<GithubUser> getAllMembers() {
        return this.memberService.getAllMembers();
    }
}
