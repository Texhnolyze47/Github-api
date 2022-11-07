package com.texhnolyze.githubapiv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Flux;
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
     * Este método hace la función de usar el Api de Github para mandar un json a la
     * api de github, cuando esto se hace de forma exitosa el usuario recibe una
     * invitación a en su correo.
     *
     * @param user un objeto de tipo Members
     * @return Members es un objeto que se manda como un json al api de github
     */
    @PostMapping("/add")
    public Members addMembers(@RequestBody Members user) {

            try {
                return webClientBuilder.build()
                        .post()
                        .uri(ADD_MEMBERS)
                        .header("Authorization", "Bearer " + TOKEN)
                        .body(BodyInserters.fromValue(user))
                        .retrieve()
                        .bodyToMono(Members.class)
                        // limita la cantidad de requests que se envían
                        .retryWhen(Retry.backoff( 3, Duration.ofSeconds(5))
                                .filter(ex -> WebClientFilter.is5xxException(ex))
                                // excepción en caso se pase el límite requests fallidos
                                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
                                        new ServiceException("Intentos máximos alcanzados", HttpStatus.SERVICE_UNAVAILABLE.value()))))

                        .block();

            }catch (WebClientRequestException we){
                throw new ServiceException(we.getMessage());
            }


    }


    /**
     * Este método tiene la función de simplemente regresar una lista de usuarios que pertenecen a la org
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
