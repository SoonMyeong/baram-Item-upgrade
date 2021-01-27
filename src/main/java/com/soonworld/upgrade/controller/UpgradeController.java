package com.soonworld.upgrade.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UpgradeController {

    @GetMapping("/upgrade")
    public Mono<ResponseEntity<String>> upgradeItem(ServerHttpRequest request , ServerHttpResponse response){
        return Mono.just(ResponseEntity.ok("MSA 테스트"));
    }

}
