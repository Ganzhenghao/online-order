package com.dfyang.onlineorder.exception;

/**
 * @Author: 55411
 * @Date: 2019/8/19 22:15
 * @Description: 未认证异常（用户未登录）
 */
public class UnverifiedException extends BaseException {

    public UnverifiedException() {
    }

    public UnverifiedException(String message) {
        super(message);
    }
}
