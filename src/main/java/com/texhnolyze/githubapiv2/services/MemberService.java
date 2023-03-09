package com.texhnolyze.githubapiv2.services;

import com.texhnolyze.githubapiv2.entities.Members;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;



public interface MemberService {
    Mono<Members> registerMember(@RequestBody  Members members);

}
