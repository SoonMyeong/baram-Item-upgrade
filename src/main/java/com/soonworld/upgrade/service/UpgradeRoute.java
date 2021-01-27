package com.soonworld.upgrade.service;

import com.soonworld.upgrade.service.vo.UpgradeItem;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeRoute {

    private final UpgradeItemCase itemCase;

    @Autowired
    public UpgradeRoute(UpgradeItemCase itemCase) {
        this.itemCase = itemCase;
    }

    /**
     * 아이템 사용 유무(caseNumber)에 따른 메소드 라우팅 해주는 메소드 (으매 말이 참 어렵네)
     *
     */
    public void routeUpgradeMethod(UpgradeItem item, int caseNumber){
        int number = (int) (Math.random()*100)+1; //강화 결과

        switch(item.getCurrentItemLevel()){
            case 1:
            case 2:
                if(caseNumber==1) itemCase.upgradeOne(item,100,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,100,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,100,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,100,number);
                break;
            case 3:
                if(caseNumber==1) itemCase.upgradeOne(item,90,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,90,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,90,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,90,number);
                break;
            case 4:
                if(caseNumber==1) itemCase.upgradeOne(item,80,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,80,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,80,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,80,number);
                break;
            case 5:
                if(caseNumber==1) itemCase.upgradeOne(item,70,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,70,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,70,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,70,number);
                break;
            case 6:
                if(caseNumber==1) itemCase.upgradeOne(item,60,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,60,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,60,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,60,number);
                break;
            case 7:
                if(caseNumber==1) itemCase.upgradeOne(item,50,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,50,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,50,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,50,number);
                break;
            case 8 :
                if(caseNumber==1) itemCase.upgradeOne(item,40,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,40,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,40,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,40,number);
                break;
            case 9:
                if(caseNumber==1) itemCase.upgradeOne(item,30,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,30,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,30,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,30,number);
                break;
            case 10:
                if(caseNumber==1) itemCase.upgradeOne(item,20,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,20,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,20,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,20,number);
                break;
            case 11:
                if(caseNumber==1) itemCase.upgradeOne(item,15,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,15,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,15,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,15,number);
                break;
            case 12:
                if(caseNumber==1) itemCase.upgradeOne(item,12,number);
                else if(caseNumber==2) itemCase.upgradeTwo(item,12,number);
                else if(caseNumber==3) itemCase.upgradeThree(item,12,number);
                else if(caseNumber==4) itemCase.upgradeFour(item,12,number);
                break;

            default: System.out.println("upgrade Item Routing  number Exception!");
        }
    }
}
