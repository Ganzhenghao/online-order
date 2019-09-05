package com.dfyang.onlineorder.bean;

import com.dfyang.onlineorder.enums.HttpStatusEnum;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: 55411
 * @Date: 2019/7/23 20:43
 * @Description: ResponseVo工厂类
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ResponseVoFactory<T> {

    public ResponseVo<T> setSuccess() {
        return new ResponseVo<T>(HttpStatusEnum.SUCCESS.getStatus(), null, null);
    }

    public ResponseVo<T> setSuccess(String msg) {
        return new ResponseVo<T>(HttpStatusEnum.SUCCESS.getStatus(), msg, null);
    }

    public ResponseVo<T> setSuccess(T data) {
        return new ResponseVo<T>(HttpStatusEnum.SUCCESS.getStatus(), null, data);
    }

    public ResponseVo<T> setSuccess(String msg, T data) {
        return new ResponseVo<T>(HttpStatusEnum.SUCCESS.getStatus(), msg, data);
    }

    public ResponseVo<T> setFailure() {
        return setFailure("服务端内部错误");
    }

    public ResponseVo<T> setFailure(String msg) {
        return setFailure(HttpStatusEnum.SERVER_ERROR.getStatus(), msg);
    }

    public ResponseVo<T> setFailure(int code, String msg) {
        return setFailure(code, msg, null);
    }

    public ResponseVo<T> setFailure(int code, String msg, T data) {
        return new ResponseVo<T>(code, msg, data);
    }
}
