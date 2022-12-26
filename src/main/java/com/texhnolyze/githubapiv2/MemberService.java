package com.texhnolyze.githubapiv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class MemberService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${app.token}")
    private String token;

    private final String ADD_MEMBERS = "https://api.github.com/orgs/Escihu-Wizards/invitations";
    private final String LIST_MEMBERS = "https://api.github.com/orgs/Escihu-Wizards/members";



    public Members addMembers(Members user) {
        return webClientBuilder.build()
                .post()
                .uri(ADD_MEMBERS)
                .header("Authorization", "Bearer " + token)
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .bodyToMono(Members.class)
                .block();
    }

    public Flux<GithubUser> getAllMembers() {

        return webClientBuilder.build().get().uri(LIST_MEMBERS)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(GithubUser.class);
    }

}
