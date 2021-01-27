package com.soonworld.upgrade.controller.dto;


import lombok.*;

/**
 * 장비 업데이트시 전달 받을 request DTO
 * 현재 버전에서는 굳이 장비이름을 받지 않아도 된다.
 * 1. 현재 장비 레벨
 * 2. 강화비급 사용 유무
 * 3. 강화촉진베 사용 유무
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpgradeItemRequestDto {
    @NonNull
    private int currentItemLevel;
    @NonNull
    private boolean upgradeItemUsed;
    @NonNull
    private boolean upgradeDoubleItemUsed;
}
