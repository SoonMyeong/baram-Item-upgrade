package com.soonworld.upgrade.service;

import com.soonworld.upgrade.model.vo.UpgradeItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpgradeItemService {

    /**
     * 강화 결과값을 구하고, 강화비급과 강화촉진제의 사용유무에 따른 값을 부여 해 주는 메소드
     *
     * @param item
     * @return
     * @throws NullPointerException
     */
    public Mono<UpgradeItem> upgradeItem(UpgradeItem item) throws NullPointerException {
        //강화 결과
        int result = (int) (Math.random() * 100) + 1;
        //성공 확률
        int percent = currentLevelToPercent(Optional.of(item.getCurrentItemLevel()).orElse(0));
        //강화 비급 확률 3%
        int upgradeB = 3;
        //강화 촉진제 사용 결과 확률
        int upgraceC = (int) (Math.random() * 100) + 1;

        if (!item.isUpgradeItemUsed() && !item.isUpgradeDoubleItemUsed()) { //강화비급 x, 강화촉진제 x
            return upgrade(result,percent,0,0);
        } else if (item.isUpgradeItemUsed() && !item.isUpgradeDoubleItemUsed()) { //강화비급 o, 강화촉진제 x
            return upgrade(result,percent,upgradeB,0);
        } else if (!item.isUpgradeItemUsed() && item.isUpgradeDoubleItemUsed()) { //강화비급 x , 강화촉진제 o
            return upgrade(result,percent,0,upgraceC);
        } else {  //강화비급 o , 강화촉진제 o
            return upgrade(result,percent,upgradeB,upgraceC);
        }
    }

    /**
     * 아래 파라미터를 입력 받아 확률에 따라 강화 성공과 실패 그리고 유지를 결정 짓고 <br>
     *  Vo를 만들어 리턴 하는 메소드
     *
     * @param result  강화 결과
     * @param percent  현재 장비 레벨에서의 강화 성공 확률
     * @param upgradeB 강화비급 (3%)
     * @param upgradeC 강화촉진제 (50%)
     * @return
     */
    public Mono<UpgradeItem> upgrade(int result, int percent, int upgradeB, int upgradeC) {
        int resultItemAddLevel = 0;
        boolean upgradeResult = false;

        if(percent<=result+upgradeB) {
            upgradeResult = true;
            if(upgradeC>50) {
                resultItemAddLevel = 2;
            }else {
                resultItemAddLevel = 1;
            }
        }else {
            //강화 유지 확률 30%
            int addNum = (int) (Math.random()*100)+1;
            if(addNum>30){
                resultItemAddLevel = -1;
            }
        }

        return Mono.just(UpgradeItem.builder()
                .upgradeResult(upgradeResult)
                .resultItemAddLevel(resultItemAddLevel)
                .build());
    }



    /**
     * 입력받은 장비의 현재 레벨을 강화 성공 확률로 리턴
     *
     * @param currentLevel
     * @return result
     */
    public int currentLevelToPercent(int currentLevel) {
        int result = 0;
        switch (currentLevel) {
            case 1:
            case 2:
                result = 100;
                break;
            case 3:
                result = 90;
                break;
            case 4:
                result = 80;
                break;
            case 5:
                result = 70;
                break;
            case 6:
                result = 60;
                break;
            case 7:
                result = 50;
                break;
            case 8:
                result = 40;
                break;
            case 9:
                result = 30;
                break;
            case 10:
                result = 20;
                break;
            case 11:
                result = 15;
                break;
            case 12:
                result = 12;
                break;

            default:
                result = 1;
        }
        return result;
    }
}
