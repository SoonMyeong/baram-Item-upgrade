package com.soonworld.upgrade.model.dto;


import lombok.Getter;

import javax.validation.constraints.Min;

/**
 * 장비 업데이트시 전달 받을 request DTO
 * 현재 버전에서는 굳이 장비이름을 받지 않아도 된다.
 * 1. 현재 장비 레벨
 * 2. 강화비급 사용 유무
 * 3. 강화촉진베 사용 유무
 *
 */

@Getter
public class UpgradeItemRequestDto {
    @Min(value = 1, message = "최소 설정 레벨은 1 부터 입니다.")
    private int currentItemLevel;
    private boolean upgradeItemUsed;
    private boolean upgradeDoubleItemUsed;
}
