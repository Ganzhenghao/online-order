package com.dfyang.onlineorder.controller;

import com.dfyang.onlineorder.entity.Admin;
import com.dfyang.onlineorder.entity.Merchant;
import com.dfyang.onlineorder.entity.Product;
import com.dfyang.onlineorder.entity.User;
import com.dfyang.onlineorder.service.AdminService;
import com.dfyang.onlineorder.utils.MD5Util;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * ClassName AdminController
 * Description TODO
 * Author Ganzhenghao
 * Date  2019/9/5 17:53
 * Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @RequestMapping("/registerPage")
    public String register(){
        return "admin/admin-register";
    }

    @RequestMapping("/loggingPage")
    public String loggingPage(){
        return "admin/admin-logging";
    }

    @RequestMapping("/index")
    public String inedx(){
        return "admin/admin-index";
    }


    @RequestMapping("/saveAdmin")
    public String saveAdmin(String username , String password){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(MD5Util.encrypt(password));
        adminService.saveAdmin(admin);
        return "admin/admin-index";
    }

    @RequestMapping("/logging")
    public String logging(Admin admin,Model model){
        admin.setPassword(MD5Util.encrypt(admin.getPassword()));
        Admin admin1 = adminService.findAdmin(admin);
        System.out.println(admin1);
        if (admin1 != null){
            return "admin/admin-index";
        }else {
            model.addAttribute("loggingInfo", false);
            return "admin/admin-logging";
        }
    }

    /**
     *查询所有商家信息
     * @param page 查询第几页
     * @param size 每页有多少条记录
     * @return mv
     */
    @RequestMapping("/findAllMerchant")
    public ModelAndView findAllMerchant(@RequestParam(name = "page" ,defaultValue = "1") int page,
                                        @RequestParam(name = "size" ,defaultValue = "7") int size){
        List<Merchant> allMerchant = adminService.findAllMerchant(page, size);
        PageInfo<Merchant> pageInfo = new PageInfo<>(allMerchant);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("admin/admin-merchant-list");
        return mv;
    }
    /**
     *查询所有用户信息
     * @param page 查询第几页
     * @param size 每页有多少条记录
     * @return mv
     */
    @RequestMapping("/findAllUser")
    public ModelAndView findAllUser(@RequestParam(name = "page" ,defaultValue = "1") int page,
                                        @RequestParam(name = "size" ,defaultValue = "7") int size){
        List<User> allUser = adminService.findAllUser(page, size);
        PageInfo<User> pageInfo = new PageInfo<>(allUser);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("admin/admin-user-list");
        return mv;
    }

    /**
     * 查询所有商品信息
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAllProduct")
    public ModelAndView findAllProduct(@RequestParam(name = "page" ,defaultValue = "1") int page,
                                        @RequestParam(name = "size" ,defaultValue = "7") int size){
        List<Product> allProduct = adminService.findAllProduct(page, size);
        PageInfo<Product> pageInfo = new PageInfo<>(allProduct);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("admin/admin-product-list");
        return mv;
    }

    @RequestMapping("/status/{status}/{merchantId}")
    public String status(@PathVariable("merchantId") int merchantId,@PathVariable("status") int status){
        Merchant merchant = new Merchant();
        merchant.setMerchantId(merchantId);
        merchant.setStatus(status);
        adminService.changeMerchantStatus(merchant);
        return "redirect:/admin/findAllMerchant";
    }

    @RequestMapping("/userStatus/{status}/{userId}")
    public String userStatus(@PathVariable("userId") int userId,@PathVariable("status") int status){
        User user = new User();
        user.setUserId(userId);
        user.setStatus(status);
        adminService.changeUserStatus(user);
        return "redirect:/admin/findAllUser";
    }

    @RequestMapping("/productStatus/{status}/{productId}")
    public String productStatus(@PathVariable("productId") int productId,@PathVariable("status") int status){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductStatus(status);
        adminService.changeProductStatus(product);
        return "redirect:/admin/findAllProduct";
    }

}
