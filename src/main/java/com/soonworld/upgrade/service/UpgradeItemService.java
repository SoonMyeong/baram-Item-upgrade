package com.soonworld.upgrade.service;

import com.soonworld.upgrade.model.vo.UpgradeItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpgradeItemService {

    public Mono<UpgradeItem> upgradeItem(UpgradeItem item) throws NullPointerException{
        try {
            if(!item.isUpgradeItemUsed() && !item.isUpgradeDoubleItemUsed()) { //강화비급 x, 강화촉진제 x
                return checkUpgradeItem(item.getCurrentItemLevel(),false,false);
            }else if(item.isUpgradeItemUsed() && !item.isUpgradeDoubleItemUsed()) { //강화비급 o, 강화촉진제 x
                return checkUpgradeItem(item.getCurrentItemLevel(),false,false);
            }else if(!item.isUpgradeItemUsed() && item.isUpgradeDoubleItemUsed()){ //강화비급 x , 강화촉진제 o
                return checkUpgradeItem(item.getCurrentItemLevel(),false,false);
            }else {  //강화비급 o , 강화촉진제 o
                return checkUpgradeItem(item.getCurrentItemLevel(),false,false);
            }
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }


//    /**
//     * 아이템 사용 유무(caseNumber)에 따른 메소드 라우팅 해주는 메소드
//     *
//     */
//    public void routeUpgradeMethod(UpgradeItem item, boolean isUpgradeB , boolean isUpgradeC){
//        int number = (int) (Math.random()*100)+1; //강화 결과
//
//        switch(item.getCurrentItemLevel()){
//            case 1:
//            case 2:
//                if(caseNumber==1) itemCase.upgradeOne(item,100,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,100,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,100,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,100,number);
//                break;
//            case 3:
//                if(caseNumber==1) itemCase.upgradeOne(item,90,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,90,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,90,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,90,number);
//                break;
//            case 4:
//                if(caseNumber==1) itemCase.upgradeOne(item,80,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,80,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,80,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,80,number);
//                break;
//            case 5:
//                if(caseNumber==1) itemCase.upgradeOne(item,70,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,70,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,70,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,70,number);
//                break;
//            case 6:
//                if(caseNumber==1) itemCase.upgradeOne(item,60,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,60,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,60,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,60,number);
//                break;
//            case 7:
//                if(caseNumber==1) itemCase.upgradeOne(item,50,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,50,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,50,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,50,number);
//                break;
//            case 8 :
//                if(caseNumber==1) itemCase.upgradeOne(item,40,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,40,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,40,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,40,number);
//                break;
//            case 9:
//                if(caseNumber==1) itemCase.upgradeOne(item,30,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,30,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,30,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,30,number);
//                break;
//            case 10:
//                if(caseNumber==1) itemCase.upgradeOne(item,20,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,20,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,20,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,20,number);
//                break;
//            case 11:
//                if(caseNumber==1) itemCase.upgradeOne(item,15,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,15,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,15,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,15,number);
//                break;
//            case 12:
//                if(caseNumber==1) itemCase.upgradeOne(item,12,number);
//                else if(caseNumber==2) itemCase.upgradeTwo(item,12,number);
//                else if(caseNumber==3) itemCase.upgradeThree(item,12,number);
//                else if(caseNumber==4) itemCase.upgradeFour(item,12,number);
//                break;
//
//            default: System.out.println("upgrade Item Routing  number Exception!");
//        }
//    }

    public Mono<UpgradeItem> checkUpgradeItem(int currentLevel, boolean isUpgradeB, boolean isUpgradeC) {
        int number = (int) (Math.random()*100)+1; //강화 결과
        int percent = 100;
        //TODO
        return upgrade(percent,number);
    }

    public Mono<UpgradeItem> upgrade(int percent, int num) {
        //TODO
        return Mono.just(UpgradeItem.builder().build());
    }


}
