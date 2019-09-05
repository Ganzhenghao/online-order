package com.dfyang.onlineorder.exception;

import com.dfyang.onlineorder.bean.ResponseVoFactory;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 55411
 * @Date: 2019/8/19 21:52
 * @Description: 自定义异常处理器
 */
@ControllerAdvice
@ResponseBody
public class BaseExceptionHandler {

    @Autowired
    private ResponseVoFactory responseVoFactory;

    @ExceptionHandler(value = Exception.class)
    public ResponseVo exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();
        if (e instanceof BaseException) {
            // 如果用户未登录，跳转登录界面
            if (e instanceof UnverifiedException)
                request.getRequestDispatcher("/user/login").forward(request, response);
            // 如果管理员未登录，跳转登录界面
            if (e instanceof AdminUnverifiedException)
                request.getRequestDispatcher("/manage").forward(request, response);
            return responseVoFactory.setFailure(e.getMessage());
        } else {
            return responseVoFactory.setFailure();
        }
    }

}
