package com.soonworld.upgrade.service.vo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpgradeItem {
    @NonNull
    private int currentItemLevel;
    @NonNull
    private boolean upgradeItemUsed; //강화비급
    @NonNull
    private boolean upgradeDoubleItemUsed; //강화촉진제
    private boolean upgradeResult; //강화 결과
    private int resultItemAddLevel; //강화 결과 장비 add 레벨 (-1, 0, 1 ,2)
}
