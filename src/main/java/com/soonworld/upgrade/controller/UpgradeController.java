package com.soonworld.upgrade.controller;


import com.soonworld.upgrade.controller.dto.UpgradeItemRequestDto;
import com.soonworld.upgrade.service.UpgradeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UpgradeController {

    private final UpgradeItemService upgradeItemService;

    @Autowired
    public UpgradeController(UpgradeItemService upgradeItemService) {
        this.upgradeItemService = upgradeItemService;
    }


    @GetMapping("/upgrade")
    public Mono<ResponseEntity<String>> upgradeItem(@RequestBody UpgradeItemRequestDto request){
//        upgradeItemService.upgradeItem(upgradeItemService.upgradeItem();
        return Mono.just(ResponseEntity.ok("MSA 테스트"));
    }

}
