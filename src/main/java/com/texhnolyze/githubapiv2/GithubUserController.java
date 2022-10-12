package com.texhnolyze.githubapiv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/users")
public class GithubUserController {

    private final String LIST_MEMBERS = "https://api.github.com/orgs/Escihu-Wizards/members";
    private final String ADD_MEMBERS = "https://api.github.com/orgs/Escihu-Wizards/invitations";

    private final String TOKEN = "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";

    @Autowired
    private WebClient.Builder webClientBuilder;


    /**
     * Este metodo hace la funcion de usar el Api de Github para mandar un json a la
     * api de github, cuando esto se hace de forma exitosa el usuario recibe una
     * invitacion a en su correo.
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Members addMembers(@RequestBody Members user) {

        return webClientBuilder.build()
                .post()
                .uri(ADD_MEMBERS)
                .header("Authorization", "Bearer " + TOKEN)
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .bodyToMono(Members.class).log()
                .block();



    }


    /**
     * Este metodo tiene la funcion de simplemente regresar una lista de usuarios que pertenecen a la org
     * de github
     */
    @GetMapping("/all")
    public Flux<GithubUserDTO> getAllMembers() {

        return webClientBuilder.build().get().uri(LIST_MEMBERS)
                .header("Authorization", "Bearer " + TOKEN)
                .retrieve()
                .bodyToFlux(GithubUserDTO.class);
    }


}
