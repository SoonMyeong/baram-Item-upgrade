package com.soonworld.upgrade.controller;


import com.soonworld.upgrade.controller.dto.UpgradeItemRequestDto;
import com.soonworld.upgrade.controller.dto.UpgradeItemResponseDto;
import com.soonworld.upgrade.service.UpgradeItemService;
import com.soonworld.upgrade.service.vo.UpgradeItem;
import com.soonworld.upgrade.utils.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UpgradeController {


    private ModelMapperUtil modelMapperUtil;
    private final UpgradeItemService upgradeItemService;

    @Autowired
    public UpgradeController(ModelMapperUtil modelMapperUtil, UpgradeItemService upgradeItemService) {
        this.modelMapperUtil = modelMapperUtil;
        this.upgradeItemService = upgradeItemService;
    }

    @PostMapping("/upgrade")
    public Mono<UpgradeItemResponseDto> upgradeItem(@RequestBody UpgradeItemRequestDto requestDto){
        return upgradeItemService.upgradeItem(UpgradeItem.builder()
                .currentItemLevel(requestDto.getCurrentItemLevel())
                .upgradeItemUsed(requestDto.isUpgradeItemUsed())
                .upgradeDoubleItemUsed(requestDto.isUpgradeDoubleItemUsed())
                .build()).map(v -> UpgradeItemResponseDto.builder()
                                    .upgradeResult(v.isUpgradeResult())
                                    .resultItemAddLevel(v.getResultItemAddLevel())
                                    .build());
    }
}
