package com.soonworld.upgrade.service;

import com.soonworld.upgrade.model.vo.UpgradeItem;
import org.springframework.stereotype.Component;

@Component
public class UpgradeItemCase {

    /**
     *  장비 확률별 강화 Method 1
     *  강화비급 x , 강화촉진제 x
     *  return : void (vo에 바로 데이터 입력 함)
     */
    public void upgradeOne(UpgradeItem item, int percent, int num){
        if(num<=percent){
            item.setUpgradeResult(true);
            item.setResultItemAddLevel(1);
        }else{ //강화 실패
            item.setUpgradeResult(false);

            int addNum = (int) (Math.random()*10)+1;
            if(addNum<3){ //강화 유지의 기회를 줌
                item.setResultItemAddLevel(0);
            }else{
                item.setResultItemAddLevel(-1);
            }
        }
    }

    /**
     *  장비 확률별 강화 Method 2
     *  강화비급 o, 강화촉진제 x
     *  return : void (vo에 바로 데이터 입력 함)
     */
    public void upgradeTwo(UpgradeItem item, int percent, int num){
        if(num<=percent+3){ //강화비급은 3%의 확률
            item.setUpgradeResult(true);
            item.setResultItemAddLevel(1);
        }else{ //강화 실패
            item.setUpgradeResult(false);

            int addNum = (int) (Math.random()*10)+1;
            if(addNum<3){ //강화 유지의 기회를 줌
                item.setResultItemAddLevel(0);
            }else{
                item.setResultItemAddLevel(-1);
            }
        }
    }

    /**
     *  장비 확률별 강화 Method 3
     *  강화비급 x, 강화촉진제 o
     *  return : void (vo에 바로 데이터 입력 함)
     */
    public void upgradeThree(UpgradeItem item, int percent, int num){
        if(num<=percent){
            item.setUpgradeResult(true);
            int addLevel = (int) (Math.random()*10)+1;
            if(addLevel>5){ //강화촉진제 확률 50%
                item.setResultItemAddLevel(2);
            }else{
                item.setResultItemAddLevel(1);
            }
        }else{ //강화 실패
            item.setUpgradeResult(false);

            int addNum = (int) (Math.random()*10)+1;
            if(addNum<3){ //강화 유지의 기회를 줌
                item.setResultItemAddLevel(0);
            }else{
                item.setResultItemAddLevel(-1);
            }
        }
    }


    /**
     *  장비 확률별 강화 Method 4
     *  강화비급 o , 강화촉진제 o
     *  return : void (vo에 바로 데이터 입력 함)
     */
    public void upgradeFour(UpgradeItem item, int percent, int num){
        if(num<=percent+3){ //강화비급은 3%의 확률
            item.setUpgradeResult(true);
            int addLevel = (int) (Math.random()*10)+1;
            if(addLevel>5){ //강화촉진제 확률 50%
                item.setResultItemAddLevel(2);
            }else{
                item.setResultItemAddLevel(1);
            }
        }else{ //강화 실패
            item.setUpgradeResult(false);

            int addNum = (int) (Math.random()*10)+1;
            if(addNum<3){ //강화 유지의 기회를 줌
                item.setResultItemAddLevel(0);
            }else{
                item.setResultItemAddLevel(-1);
            }
        }

    }
}
