package com.dfyang.onlineorder.controller;

import com.dfyang.onlineorder.service.MerchantService;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.SuccessVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MerchantService merchantService;

    /**
     * 默认跳转首页第一页
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        PageVo pageVo = merchantService.findMerchantPage(1, 16);
        model.addAttribute("pageVo", pageVo);
        return "index";
    }

    /**
     * 跳转首页（分页）
     * @return
     */
    @GetMapping("/index/{page}/{size}")
    public String index(@PathVariable Integer page, @PathVariable Integer size, Model model) {
        if (page == null)
            page = 1;
        if (size == null)
            size = 16;
        PageVo pageVo = merchantService.findMerchantPage(page, size);
        model.addAttribute("pageVo", pageVo);
        return "index";
    }

    /**
     * 登录成功界面
     * @return
     */
    @GetMapping("/success/{code}")
    public String success(@PathVariable("code") int code, Model model) {
        HttpSession session = request.getSession();
        SuccessVo successVo = SuccessVo.getSuccessVoByCode(code);
        if (StringUtils.isEmpty(successVo.getLink())) {
            String referer = (String) session.getAttribute("referer");
            session.removeAttribute("referer");
            // 跳转到前一页面，为空则跳转到首页
            if (StringUtils.isNotEmpty(referer)) {
                successVo.setLink(referer);
            } else {
                successVo.setLink("/");
            }
        }
        model.addAttribute("successVo", successVo);
        return "common/success";
    }

}
