package com.dfyang.onlineorder.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 55411
 * @Date: 2019/7/23 20:27
 * @Description: 前端响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo<T> {

    /** 状态码 */
    private int status;

    /** 描述 */
    private String desc;

    /** 响应数据 */
    private T data;
}
