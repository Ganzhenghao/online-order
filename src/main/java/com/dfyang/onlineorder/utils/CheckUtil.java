package com.dfyang.onlineorder.utils;

import com.dfyang.onlineorder.exception.AdminUnverifiedException;
import com.dfyang.onlineorder.exception.IllegalParamException;
import com.dfyang.onlineorder.exception.UnverifiedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: 55411
 * @Date: 2019/8/19 21:36
 * @Description: 非法校验工具类
 */
@Component
public class CheckUtil {

    /**
     * 验证BindingResult并返回单个错误消息
     * @param result
     * @return
     */
    public static void checkParam(BindingResult result) {
        if (result.hasErrors())
            throw new IllegalParamException(result.getFieldError().getDefaultMessage());

    }

    /**
     * 检查用户是否进行登录
     * @param request
     */
    public static void checkLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null)
            throw new UnverifiedException("用户未登录");
    }

    public static void checkMerchantLogin(HttpSession session) {
        if (session.getAttribute("merchant") == null)
            throw new UnverifiedException("商家未登录");
    }

    /**
     * 检查用户是否进行登录，并验证BindingResult并返回单个错误消息
     * @param session
     * @param result
     */
    public static void checkLoginAndParam(HttpSession session, BindingResult result) {
        if (session.getAttribute("user") == null)
            throw new UnverifiedException("用户未登录");
        if (result.hasErrors())
            throw new IllegalParamException(result.getFieldError().getDefaultMessage());
    }

    /**
     * 检查管理员是否进行登录
     * @param request
     */
    public static void checkAdminLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") == null)
            throw new AdminUnverifiedException("管理员未登录");
    }

    /**
     * 检测
     * @param obj
     */
    public static void checkNull(Object obj) {
        if (obj == null)
            throw new NullPointerException();
    }

}
