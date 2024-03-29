package com.soonworld.upgrade.model.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "반드시 값이 존재 해야 합니다.")
    private int currentItemLevel;
    @NotNull(message = "반드시 값이 존재 해야 합니다.")
    private boolean upgradeItemUsed;
    @NotNull(message = "반드시 값이 존재 해야 합니다.")
    private boolean upgradeDoubleItemUsed;
}
