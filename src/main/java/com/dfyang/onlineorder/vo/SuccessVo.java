package com.dfyang.onlineorder.vo;

import com.dfyang.onlineorder.constants.SuccessConstants;
import lombok.Data;

/**
 * @Author: 55411
 * @Date: 2019/7/25 09:08
 * @Description: 用于显示在success.html
 */
@Data
public class SuccessVo {

    /** 描述 */
    private String desc;

    /** 链接信息 */
    private String link;

    /** 链接描述 */
    private String linkDesc;

    /**
     * 获取注册成功SuccessVo
     * @return
     */
    public static SuccessVo getRegisterSuccessVo() {
        SuccessVo successVo = new SuccessVo();
        successVo.setDesc("注册成功，新手上路，3s后将自动转入之前页面");
        successVo.setLinkDesc("如果您的浏览器没有自动跳转，请点击次链接");
        return successVo;
    }

    /**
     * 获取登录成功SuccessVo
     * @return
     */
    public static SuccessVo getLoginSuccessVo() {
        SuccessVo successVo = new SuccessVo();
        successVo.setDesc("欢迎您回来，3s后将自动转入之前页面");
        successVo.setLinkDesc("如果您的浏览器没有自动跳转，请点击次链接");
        return successVo;
    }

    /**
     * 获取修改密码成功SuccessVo
     * @return
     */
    public static SuccessVo getAlterPasswordSuccessVo() {
        SuccessVo successVo = new SuccessVo();
        successVo.setDesc("修改密码成功，3s后将转入个人信息页面");
        successVo.setLinkDesc("如果您的浏览器没有自动跳转，请点击次链接");
        successVo.setLink("/user/login");
        return successVo;
    }

    /**
     * 申请店家成功SuccessVo
     * @return
     */
    public static SuccessVo getApplyMerchantSuccessVo() {
        SuccessVo successVo = new SuccessVo();
        successVo.setDesc("修改成功，请耐心等待管理员审核，3s后将转入个人信息页面");
        successVo.setLinkDesc("如果您的浏览器没有自动跳转，请点击次链接");
        successVo.setLink("/user/info");
        return successVo;
    }

    /**
     * 支付成功SuccessVo
     * @return
     */
    public static SuccessVo getPaySuccessVo() {
        SuccessVo successVo = new SuccessVo();
        successVo.setDesc("支付成功，请耐心等待管理员审核，3s后将转入订单页面");
        successVo.setLinkDesc("如果您的浏览器没有自动跳转，请点击次链接");
        successVo.setLink("/user/indent/1/5");
        return successVo;
    }


    /**
     * 获取SuccessVo通过code
     * @param code
     * @return
     */
    public static SuccessVo getSuccessVoByCode(int code) {
        switch (code) {
            case SuccessConstants.REGISTER_SUCCESS: return getRegisterSuccessVo();
            case SuccessConstants.LOGIN_SUCCESS: return getLoginSuccessVo();
            case SuccessConstants.ALTER_PASSWORD_SUCCESS: return getAlterPasswordSuccessVo();
            case SuccessConstants.APPLY_MERCHANT_SUCCESS: return getApplyMerchantSuccessVo();
            case SuccessConstants.PAY_SUCCESS: return getPaySuccessVo();
            default: return new SuccessVo();
        }
    }

}
