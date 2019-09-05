package com.dfyang.onlineorder.enums;

import com.dfyang.onlineorder.vo.ResponseVo;
import lombok.Getter;

/**
 * @Author: 55411
 * @Date: 2019/7/23 20:33
 * @Description: HTTP状态码枚举
 */
@Getter
public enum HttpStatusEnum {
    SUCCESS(200, "成功"),
    SERVER_ERROR(500, "服务端错误"),
    ;

    /** 状态码 */
    private int status;

    /** 描述 */
    private String desc;

    HttpStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 是否为成功状态码
     * @param status
     * @return
     */
    public static boolean isSuccess (int status) {
        return status == SUCCESS.getStatus() ? true : false;
    }

    /**
     * 是否为成功状态码
     * @param responseVo
     * @return
     */
    public static boolean isSuccess (ResponseVo responseVo) {
        return responseVo.getStatus() == SUCCESS.getStatus() ? true : false;
    }
}
