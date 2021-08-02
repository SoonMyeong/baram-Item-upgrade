package com.soonworld.upgrade.handler;

import com.soonworld.upgrade.exception.CustomException;
import com.soonworld.upgrade.model.dto.UpgradeItemRequestDto;
import com.soonworld.upgrade.model.dto.UpgradeItemResponseDto;
import com.soonworld.upgrade.model.vo.UpgradeItem;
import com.soonworld.upgrade.service.UpgradeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpgradeItemHandler {
    private final UpgradeItemService upgradeItemService;
    private final Validator validator;

    public Mono<ServerResponse> upgradeItem(ServerRequest request) {
        Mono<UpgradeItemRequestDto> requestDtoMono =
                request.bodyToMono(UpgradeItemRequestDto.class).doOnNext(this::validate);

        return requestDtoMono
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
                                        return ServerResponse.ok().bodyValue(responseDto);
                                    })
                            .onErrorResume(e->ServerResponse.badRequest().bodyValue(e.getMessage()));
                })
                .onErrorResume(e->ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    private void validate(UpgradeItemRequestDto requestDto) {
        System.out.println("test");
        Errors errors = new BeanPropertyBindingResult(requestDto, UpgradeItemRequestDto.class.getName());
        validator.validate(requestDto, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }
}
