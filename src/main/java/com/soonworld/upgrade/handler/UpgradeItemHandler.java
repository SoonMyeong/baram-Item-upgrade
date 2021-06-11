package com.soonworld.upgrade.handler;

import com.soonworld.upgrade.model.dto.UpgradeItemRequestDto;
import com.soonworld.upgrade.model.dto.UpgradeItemResponseDto;
import com.soonworld.upgrade.model.vo.UpgradeItem;
import com.soonworld.upgrade.service.UpgradeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpgradeItemHandler {
    private final UpgradeItemService upgradeItemService;

    public Mono<ServerResponse> upgradeItem(ServerRequest request) {
        return request.bodyToMono(UpgradeItemRequestDto.class)
                .flatMap(req-> {
                    UpgradeItem upgradeItem = UpgradeItem.builder()
                            .upgradeItemUsed(req.isUpgradeItemUsed())
                            .upgradeDoubleItemUsed(req.isUpgradeDoubleItemUsed())
                            .currentItemLevel(req.getCurrentItemLevel())
                            .build();

                    return upgradeItemService.upgradeItem(upgradeItem)
                                    .flatMap(res-> {
                                        UpgradeItemResponseDto responseDto = UpgradeItemResponseDto.builder()
                                                .upgradeResult(res.isUpgradeResult())
                                                .resultItemAddLevel(res.getResultItemAddLevel())
                                                .build();
                                        return ServerResponse.ok().body(responseDto,UpgradeItemResponseDto.class);
                                    })
                            .onErrorResume(e->ServerResponse.badRequest().bodyValue(e.getMessage()));
                })
                .onErrorResume(e->ServerResponse.badRequest().bodyValue(e.getMessage()));
    }
}
