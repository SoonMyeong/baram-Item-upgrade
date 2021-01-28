package com.soonworld.upgrade.service.vo;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpgradeItem {
    private int currentItemLevel;
    private boolean upgradeItemUsed; //강화비급
    private boolean upgradeDoubleItemUsed; //강화촉진제
    private boolean upgradeResult; //강화 결과
    private int resultItemAddLevel; //강화 결과 장비 add 레벨 (-1, 0, 1 ,2)
}
