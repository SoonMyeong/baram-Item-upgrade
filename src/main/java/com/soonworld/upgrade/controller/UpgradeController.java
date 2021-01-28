package com.soonworld.upgrade.controller;


import com.soonworld.upgrade.controller.dto.UpgradeItemRequestDto;
import com.soonworld.upgrade.controller.dto.UpgradeItemResponseDto;
import com.soonworld.upgrade.service.UpgradeItemService;
import com.soonworld.upgrade.service.vo.UpgradeItem;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<UpgradeItemResponseDto>> upgradeItem(@RequestBody @NonNull UpgradeItemRequestDto requestDto){
        UpgradeItem item = upgradeItemService.upgradeItem(UpgradeItem.builder()
                .currentItemLevel(requestDto.getCurrentItemLevel())
                .upgradeDoubleItemUsed(requestDto.isUpgradeDoubleItemUsed())
                .upgradeItemUsed(requestDto.isUpgradeItemUsed())
                .build());

        return Mono.just(ResponseEntity.ok(UpgradeItemResponseDto.builder()
                .resultItemAddLevel(item.getResultItemAddLevel())
                .upgradeResult(item.isUpgradeResult())
                .build()));
    }

}
