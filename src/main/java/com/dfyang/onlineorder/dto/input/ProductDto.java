package com.dfyang.onlineorder.dto.input;

import com.dfyang.onlineorder.dto.DtoConverter;
import com.dfyang.onlineorder.entity.Product;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductDto implements DtoConverter<ProductDto, Product> {

    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDesc;

    private MultipartFile productImg;


    @Override
    public Product convert() {
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        return product;
    }

    @Override
    public ProductDto forward(Product product) {
        BeanUtils.copyProperties(product, this);
        return this;
    }
}
