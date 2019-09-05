package com.dfyang.onlineorder.enums;

import lombok.Getter;

/**
 * 店家状态枚举
 */
@Getter
public enum MerchantStatusEnum {

    WAIT_REVIEW(0, "等待审核"),
    REVIEW_SUCCESS(1, "审核通过"),
    REVIRE_FAILURE(2, "审核失败")
    ;

    /** 状态码 */
    private int status;

    /** 描述 */
    private String msg;

    MerchantStatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
