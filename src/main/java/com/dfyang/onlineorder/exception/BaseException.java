package com.dfyang.onlineorder.exception;

/**
 * @Author: 55411
 * @Date: 2019/8/19 21:50
 * @Description: 自定义异常基类
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
