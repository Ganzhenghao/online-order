package com.dfyang.onlineorder.controller;

import com.dfyang.onlineorder.dto.input.IndentDto;
import com.dfyang.onlineorder.dto.input.UserInfoDto;
import com.dfyang.onlineorder.dto.input.UsernamePasswordDto;
import com.dfyang.onlineorder.entity.Merchant;
import com.dfyang.onlineorder.entity.User;
import com.dfyang.onlineorder.enums.HttpStatusEnum;
import com.dfyang.onlineorder.service.IndentService;
import com.dfyang.onlineorder.service.MerchantService;
import com.dfyang.onlineorder.service.ProductService;
import com.dfyang.onlineorder.service.UserService;
import com.dfyang.onlineorder.utils.CheckUtil;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 用户controller层
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ProductService productService;

    @Autowired
    private IndentService indentService;

    /**
     * 跳转注册界面
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "/user/register";
    }

    /**
     * 处理用户注册
     * @param user
     * @param result
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseVo doRegister(@Valid User user, BindingResult result) {
        CheckUtil.checkParam(result);
        ResponseVo responseVo = userService.insertUser(user);
        User insertUser = (User) responseVo.getData();
        request.getSession().setAttribute("user", insertUser); // 将注册后的用户存入session
        return responseVo;
    }

    /**
     * 跳转用户登录
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    /**
     * 处理用户登录
     * @param usernamePasswordDto
     * @param result
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseVo doLogin(@Valid UsernamePasswordDto usernamePasswordDto, BindingResult result) {
        CheckUtil.checkParam(result);
        ResponseVo responseVo = userService.loginUser(usernamePasswordDto);
        if (!HttpStatusEnum.isSuccess(responseVo))
            return responseVo;
        User loginUser = (User) responseVo.getData();
        request.getSession().setAttribute("user", loginUser); // 将登录后的用户存入session
        Merchant merchant = merchantService.findMerchantByUserId(loginUser.getUserId());
        if (merchant != null) {
            session.setAttribute("merchant", merchant);
            session.setAttribute("merchantId", merchant.getMerchantId());
            session.setAttribute("merchantStatus", merchant.getStatus());
            session.setAttribute("merchantImg", merchant.getMerchantImg());
        }
        return responseVo;
    }

    /**
     * 跳转用户个人信息界面
     * @return
     */
    @GetMapping("/info")
    public String info() {
        CheckUtil.checkLogin(request);
        User user = (User) request.getSession().getAttribute("user"); // 将登录后的用户存入session
        System.err.println(user);
        Merchant merchant = merchantService.findMerchantByUserId(user.getUserId());
        if (merchant != null) {
            session.setAttribute("merchant", merchant);
            session.setAttribute("merchantId", merchant.getMerchantId());
            session.setAttribute("merchantStatus", merchant.getStatus());
            session.setAttribute("merchantImg", merchant.getMerchantImg());
        }
        return "/user/info";
    }

    /**
     * 修改用户个人信息
     * @param userInfoDto
     * @return
     */
    @PostMapping("/info")
    @ResponseBody
    public ResponseVo doInfo(UserInfoDto userInfoDto) {
        ResponseVo responseVo = userService.alterUser(userInfoDto);
        User updateUser = (User) responseVo.getData();
        request.getSession().setAttribute("user", updateUser); // 将修改后的用户存入session
        return responseVo;
    }

    /**
     * 修改用户密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/password")
    @ResponseBody
    public ResponseVo password(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword) {
        return userService.alterPassword(oldPassword, newPassword);
    }

    /**
     * 跳转忘记密码页面
     * @return
     */
    @GetMapping("/forget")
    public String forget() {
        return "/user/forget";
    }

    /**
     * 忘记密码
     * @param user
     * @return
     */
    @PostMapping("/forget")
    @ResponseBody
    public ResponseVo doForget(User user) {
        return userService.forgetPassword(user);
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public String logout()  {
        session.removeAttribute("user");
        session.removeAttribute("merchantId");
        session.removeAttribute("merchantStatus");
        return "redirect:/";
    }

    /**
     * 跳转店家商品详情页面
     * @param merchantId
     * @param model
     * @return
     */
    @GetMapping("/merchantDetail/{merchantId}/{page}/{size}")
    public String merchantDetail(@PathVariable Integer merchantId, @PathVariable int page, @PathVariable int size, Model model) {
        PageVo pageVo = productService.findMerchantProductPage(merchantId, page, size);
        model.addAttribute("pageVo", pageVo);
        Merchant merchant = merchantService.findMerchantById(merchantId);
        model.addAttribute("merchant", merchant);
        return "/user/merchantDetail";
    }

    /**
     * 搜索商铺名称
     * @param merchantName
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/search/{merchantName}/{page}/{size}")
    public String search(@PathVariable String merchantName,
                         @PathVariable int page,
                         @PathVariable int size,
                         Model model) {
        PageVo pageVo = merchantService.searchMerchantPage(merchantName, page, size);
        model.addAttribute("merchantName", merchantName);
        model.addAttribute("pageVo", pageVo);
        return "/user/search";
    }

    /**
     * 支付订单
     * @param IndentDto
     * @return
     */
    @PostMapping("/pay")
    @ResponseBody
    public ResponseVo pay(@RequestBody IndentDto IndentDto) {
        return indentService.insertIndent(IndentDto);
    }

    /**
     * 跳转订单页面
     * @return
     */
    @GetMapping("/indent/{page}/{size}")
    public String indent(@PathVariable Integer page, @PathVariable Integer size, Model model) {
        PageVo pageVo = indentService.findIndentOutputDtoPage(page, size);
        model.addAttribute("pageVo", pageVo);
        return "/user/indent";
    }

}
