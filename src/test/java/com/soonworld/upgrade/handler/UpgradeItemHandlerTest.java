package com.soonworld.upgrade.handler;

import com.soonworld.upgrade.model.vo.UpgradeItem;
import com.soonworld.upgrade.router.UpgradeItemRouter;
import com.soonworld.upgrade.service.UpgradeItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UpgradeItemRouter.class, UpgradeItemHandler.class})
@WebFluxTest
@DisplayName("handler 테스트")
class UpgradeItemHandlerTest {
    @MockBean
    private UpgradeItemService service;
    @Autowired
    private ApplicationContext context;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    @DisplayName("정상 로직 결과 확인")
    void sucess_business_logic() {
        String req = "{\"currentItemLevel\":\"1\","
                +"\"upgradeItemUsed\":\"false\","
                +"\"upgradeDoubleItemUsed\":\"false\"}";
        UpgradeItem upgradeItem = UpgradeItem.builder()
                .upgradeResult(true)
                .resultItemAddLevel(1)
                .build();

        when(service.upgradeItem(any())).thenReturn(Mono.just(upgradeItem));
        webTestClient.post().uri("/upgrade")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.resultItemAddLevel").isEqualTo("1")
                .jsonPath("$.upgradeResult").isEqualTo(true);
    }

    @Test
    @DisplayName("서비스 에러")
    void exception_service() {
        String req = "{\"currentItemLevel\":\"1\","
                +"\"upgradeItemUsed\":\"false\","
                +"\"upgradeDoubleItemUsed\":\"false\"}";
        when(service.upgradeItem(any())).thenReturn(Mono.error(new Exception("강제 에러")));
        webTestClient.post().uri("/upgrade")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .value(res->assertEquals(res,"강제 에러"));
    }

    @Test
    @DisplayName("request 에러")
    void exception_request() {
        String req = "에러에러";
        String errorMessage = "400 BAD_REQUEST \"Failed to read HTTP message\"; nested exception is org.springframework.core.codec.DecodingException: JSON decoding error: Invalid UTF-8 start byte 0x97; nested exception is com.fasterxml.jackson.core.JsonParseException: Invalid UTF-8 start byte 0x97\n" +
                " at [Source: (org.springframework.core.io.buffer.DefaultDataBuffer$DefaultDataBufferInputStream); line: 1, column: 3]";
        UpgradeItem upgradeItem = UpgradeItem.builder()
                .upgradeResult(true)
                .resultItemAddLevel(1)
                .build();

        when(service.upgradeItem(any())).thenReturn(Mono.just(upgradeItem));
        webTestClient.post().uri("/upgrade")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .value(res-> assertEquals(res,errorMessage));
    }


}