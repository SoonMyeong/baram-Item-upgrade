package com.soonworld.upgrade.service;

import com.soonworld.upgrade.service.vo.UpgradeItem;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpgradeItemService {

    private final UpgradeRoute route;

    @Autowired
    public UpgradeItemService(UpgradeRoute route) {
        this.route = route;
    }

    public Mono<UpgradeItem> upgradeItem(UpgradeItem item) throws NullPointerException{
        try {
            if(!item.isUpgradeItemUsed() && !item.isUpgradeDoubleItemUsed()) { //강화비급x, 강화촉진제 x
                route.routeUpgradeMethod(item,1);
            }else if(item.isUpgradeItemUsed() && !item.isUpgradeDoubleItemUsed()) { //강화비급o, 강화촉진제 x
                route.routeUpgradeMethod(item,2);
            }else if(!item.isUpgradeItemUsed() && item.isUpgradeDoubleItemUsed()){ //강화비급x , 강화촉진제 o
                route.routeUpgradeMethod(item,3);
            }else if(item.isUpgradeItemUsed() && item.isUpgradeDoubleItemUsed()) { //강화비급o , 강화촉진제 o
                route.routeUpgradeMethod(item,4);
            }
        }catch(NullPointerException e){
            System.out.println("NullPointer Exception.");
        }

        return Mono.just(item);
    }
}
