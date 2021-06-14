package com.soonworld.upgrade.model.dto;


import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpgradeItemResponseDto {
    private boolean upgradeResult; //강화 결과
    private int resultItemAddLevel; //강화 결과 장비 add 레벨 (-1, 0, 1 ,2)
}
