package com.dfyang.onlineorder.controller;

import com.dfyang.onlineorder.dto.input.ProductDto;
import com.dfyang.onlineorder.entity.MerchantProduct;
import com.dfyang.onlineorder.entity.Product;
import com.dfyang.onlineorder.service.MerchantProductService;
import com.dfyang.onlineorder.service.ProductService;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpSession session;

    @Autowired
    private MerchantProductService merchantProductService;

    /**
     * 新增产品
     * @param productDto
     * @return
     */
    @PostMapping("/add")
    public String add(ProductDto productDto) {
        MultipartFile file = productDto.getProductImg();
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String randomName = UUID.randomUUID().toString().replace("-", "");
        String finalName = randomName + suffix;
        File uploadFile = new File(MerchantController.uploadPath + finalName);
        File dirs = new File(MerchantController.uploadPath);
        if (!dirs.exists())
            dirs.mkdirs();
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = productDto.convert();
        product.setProductImg("/upload/" + finalName);
        ResponseVo responseVo = productService.addProduct(product);
        product = (Product) responseVo.getData();
        MerchantProduct merchantProduct = new MerchantProduct();
        merchantProduct.setProductId(product.getProductId());
        Integer merchantId = (Integer) session.getAttribute("merchantId");
        merchantProduct.setMerchantId(merchantId);
        merchantProductService.insertMerchantProduct(merchantProduct);
        return "redirect:/merchant/product/1/5";
    }

    /**
     * 删除产品
     * @param productId
     * @return
     */
    @GetMapping("/delete/{productId}")
    @ResponseBody
    public ResponseVo delete(@PathVariable("productId") Integer productId) {
        return productService.deleteProduct(productId);
    }

    /**
     * 修改商品
     * @param productDto
     * @return
     */
    @PostMapping("/update")
    public String update(ProductDto productDto) {
        Product product = productDto.convert();
        String finalName = "";
        if (productDto.getProductImg() != null && !productDto.getProductImg().isEmpty()) {
            MultipartFile file = productDto.getProductImg();
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String randomName = UUID.randomUUID().toString().replace("-", "");
            finalName = randomName + suffix;
            File uploadFile = new File(MerchantController.uploadPath + finalName);
            File dirs = new File(MerchantController.uploadPath);
            if (!dirs.exists())
                dirs.mkdirs();
            try {
                file.transferTo(uploadFile);
                product.setProductImg("/upload/" + finalName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productService.updateProduct(product);
        return "redirect:/merchant/product";
    }
}
