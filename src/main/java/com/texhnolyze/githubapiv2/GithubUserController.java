package com.texhnolyze.githubapiv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;


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

            try {
                Members miembro =  webClientBuilder.build()
                        .post()
                        .uri(ADD_MEMBERS)
                        .header("Authorization", "Bearer " + TOKEN)
                        .body(BodyInserters.fromValue(user))
                        .retrieve()
                        .bodyToMono(Members.class).log()
                        .retryWhen(Retry.backoff( 3, Duration.ofSeconds(5))
                                .filter(ex -> WebClientFilter.is5xxException(ex)))
                        .block();
                return miembro;

            }catch (WebClientRequestException we){
                throw new ServiceException(we.getMessage());
            }


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
