package com.soonworld.upgrade.controller;

import com.soonworld.upgrade.controller.dto.UpgradeItemRequestDto;
import com.soonworld.upgrade.controller.dto.UpgradeItemResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest(UpgradeController.class)
@AutoConfigureWebFlux
public class UpgradeControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private UpgradeController controller;

    @Test
    public void upgradeItem() throws Exception{
        UpgradeItemRequestDto requestDto = UpgradeItemRequestDto.builder()
                    .currentItemLevel(1)
                    .upgradeDoubleItemUsed(false)
                    .upgradeItemUsed(false)
                    .build();
        UpgradeItemResponseDto responseDto = UpgradeItemResponseDto.builder()
                .upgradeResult(true)
                .resultItemAddLevel(1)
                .build();

        when(controller.upgradeItem(requestDto)).thenReturn(Mono.just(responseDto));

        webTestClient.post()
                .uri("/upgrade")
                .body(Mono.just(requestDto),UpgradeItemRequestDto.class)
                .exchange()
                .expectStatus().isOk();

    }

}