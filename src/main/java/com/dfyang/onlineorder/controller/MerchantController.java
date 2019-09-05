package com.dfyang.onlineorder.controller;

import com.dfyang.onlineorder.entity.Merchant;
import com.dfyang.onlineorder.service.IndentService;
import com.dfyang.onlineorder.service.MerchantService;
import com.dfyang.onlineorder.service.ProductService;
import com.dfyang.onlineorder.utils.CheckUtil;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 店家controller
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController {

    /** 生成静态文件的根路径 */
    public static String uploadPath;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private IndentService indentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpSession session;

    static {
        try {
            uploadPath = ResourceUtils.getFile("classpath:static").getAbsolutePath() + "\\upload\\";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 申请成为店家
     * @param file
     * @return
     */
    @PostMapping("/apply")
    public String applyMerchant(@RequestBody @RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String randomName = UUID.randomUUID().toString().replace("-", "");
        String finalName = randomName + suffix;
        File uploadFile = new File(uploadPath + finalName);
        File dirs = new File(uploadPath);
        if (!dirs.exists())
            dirs.mkdirs();
        try {
            file.transferTo(uploadFile);
            Merchant merchant = merchantService.applyMerchant("/upload/" + finalName);
            session.setAttribute("merchantId", merchant.getMerchantId());
            session.setAttribute("merchantStatus", merchant.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/user/info";
    }

    /**
     * 跳转店家商品管理页面
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/product/{page}/{size}")
    public String product(@PathVariable Integer page, @PathVariable Integer size, Model model) {
        CheckUtil.checkMerchantLogin(session);
        Integer merchantId = (Integer) session.getAttribute("merchantId");
        PageVo pageVo = productService.findMerchantProductPage(merchantId, page, size);
        model.addAttribute("pageVo", pageVo);
        return "/merchant/product";
    }

    /**
     * 修改商家照片
     * @param file
     * @return
     */
    @PostMapping("/merchantImg")
    public String merchantImg(@RequestBody @RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String randomName = UUID.randomUUID().toString().replace("-", "");
        String finalName = randomName + suffix;
        File uploadFile = new File(uploadPath + finalName);
        File dirs = new File(uploadPath);
        if (!dirs.exists())
            dirs.mkdirs();
        try {
            file.transferTo(uploadFile);
            merchantService.updateMerchantImg("/upload/" + finalName);
            session.setAttribute("merchantImg", "/upload/" + finalName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/info";
    }

    /**
     * 更新商家信息
     * @param merchant
     * @return
     */
    @PostMapping("/update")
    public String update(Merchant merchant) {
        ResponseVo responseVo = merchantService.updateMerchant(merchant);
        Merchant updateMerchant = (Merchant) responseVo.getData();
        session.setAttribute("merchant", updateMerchant);
        return "redirect:/user/info";
    }

    /**
     * 跳转订单页面
     * @return
     */
    @GetMapping("/indent/{page}/{size}")
    public String indent(@PathVariable Integer page, @PathVariable Integer size, Model model) {
        CheckUtil.checkMerchantLogin(session);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        PageVo pageVo = indentService.findMerchantIndentOutputDtoPage(merchant.getMerchantId(), page, size);
        model.addAttribute("pageVo", pageVo);
        return "/merchant/indent";
    }

    /**
     * 接单
     * @param indentId
     * @return
     */
    @GetMapping("/agreeIndent/{indentId}")
    public String agreeIndent(@PathVariable Integer indentId) {
        CheckUtil.checkMerchantLogin(session);
        indentService.agreeIndent(indentId);
        return "redirect:/merchant/indent/1/5";
    }

    /**
     * 拒绝接单
     * @param indentId
     * @return
     */
    @GetMapping("/refuseIndent/{indentId}")
    public String refuse(@PathVariable Integer indentId) {
        CheckUtil.checkMerchantLogin(session);
        indentService.refuseIndent(indentId);
        return "redirect:/merchant/indent/1/5";
    }
}
