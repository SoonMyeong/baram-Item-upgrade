package com.soonworld.upgrade.service;

import com.soonworld.upgrade.model.vo.UpgradeItem;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Service 테스트")
class UpgradeItemServiceTest {
    private UpgradeItemService service;

    @BeforeEach
    void setUp() {
        this.service = new UpgradeItemService();
    }

    @Test
    @DisplayName("upgradeItem 동작 확인")
    void upgradeItem_start() {
        UpgradeItem upgradeItem = UpgradeItem.builder()
                .upgradeItemUsed(true)
                .upgradeDoubleItemUsed(true)
                .currentItemLevel(1) //100%확률
                .build();

        Mono<UpgradeItem> test = service.upgradeItem(upgradeItem);
        StepVerifier.create(test)
                .thenConsumeWhile(res-> {
                    assertTrue(res.isUpgradeResult());
                    return true;
                }).verifyComplete();
    }

    @Nested
    @DisplayName("upgrade 메소드 테스트")
    class upgradeMethod{
        int upgradeB = 3;

        @Test
        @DisplayName("강화 성공 했을 때")
        void success_upgrade() {
            int result = 80;
            int percent = 90;

            Mono<UpgradeItem> test = service.upgrade(result,percent,0,0);
            StepVerifier.create(test)
                    .thenConsumeWhile(res-> {
                        assertTrue(res.isUpgradeResult());
                        return true;
                    }).verifyComplete();
        }

        @Test
        @DisplayName("강화비급으로 인한 성공")
        void success_upgrade_success_upgradeB() {
            int result = 91;
            int percent = 90;

            Mono<UpgradeItem> test = service.upgrade(result,percent,upgradeB,0);
            StepVerifier.create(test)
                    .thenConsumeWhile(res-> {
                        assertTrue(res.isUpgradeResult());
                        return true;
                    }).verifyComplete();

        }

        @Test
        @DisplayName("강화 성공, 촉진제 성공 (+2레벨 증가)")
        void success_upgrade_success_upgradeC() {
            int result = 80;
            int percent = 90;
            int upgradeC = 51;

            Mono<UpgradeItem> test = service.upgrade(result,percent,0,upgradeC);
            StepVerifier.create(test)
                    .thenConsumeWhile(res-> {
                        assertEquals(res.getResultItemAddLevel(),2);
                        return true;
                    }).verifyComplete();

        }

        @Test
        @DisplayName("강화 실패 했을 때")
        void fail_upgrade() {
            int result = 80;
            int percent = 70;

            Mono<UpgradeItem> test = service.upgrade(result,percent,0,0);
            StepVerifier.create(test)
                    .thenConsumeWhile(res-> {
                        assertFalse(res.isUpgradeResult());
                        return true;
                    }).verifyComplete();

        }

        @Test
        @DisplayName("강화 유지")
        void keep_upgrade() {
            int result = 20;
            int percent = 10;

            Mono<UpgradeItem> test = service.upgrade(result,percent,0,0);
            StepVerifier.create(test)
                    .thenConsumeWhile(res-> {
                        assertEquals(res.getResultItemAddLevel(),-1);
                        return true;
                    }).verifyComplete();
        }
    }


    @DisplayName("currentlevelToPercent 메소드 테스트")
    @RepeatedTest(value = 12, name="입력 레벨 {currentRepetition} 테스트")
    void currentLevelToPercent(RepetitionInfo repetitionInfo) {
        if(repetitionInfo.getCurrentRepetition()==1) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),100);
        }
        if(repetitionInfo.getCurrentRepetition()==2) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),100);
        }
        if(repetitionInfo.getCurrentRepetition()==3) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),90);
        }
        if(repetitionInfo.getCurrentRepetition()==4) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),80);
        }
        if(repetitionInfo.getCurrentRepetition()==5) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),70);
        }
        if(repetitionInfo.getCurrentRepetition()==6) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),60);
        }
        if(repetitionInfo.getCurrentRepetition()==7) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),50);
        }
        if(repetitionInfo.getCurrentRepetition()==8) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),40);
        }
        if(repetitionInfo.getCurrentRepetition()==9) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),30);
        }
        if(repetitionInfo.getCurrentRepetition()==10) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),20);
        }
        if(repetitionInfo.getCurrentRepetition()==11) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),15);
        }
        if(repetitionInfo.getCurrentRepetition()==12) {
            assertEquals(service.currentLevelToPercent(repetitionInfo.getCurrentRepetition()),12);
        }
    }



}