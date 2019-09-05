package com.dfyang.onlineorder.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum IndentStatusEnum {
    WAIT_MERCHANT_AGREE(1, "等待商家确认"),
    WAIT_RIDER_AGREE(2, "等待骑手接单"),
    OUT_OF_DELIVERY(3, "派送中"),
    FINISHED(4, "完结"),
    REFUSED(5, "卖家拒绝接单")

    ;

    private int status;

    private String msg;

    IndentStatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
