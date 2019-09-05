package com.dfyang.onlineorder.service;

import com.dfyang.onlineorder.entity.Product;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.ResponseVo;

import java.util.List;

public interface ProductService {

    /**
     * 添加商品
     * @param product
     * @return
     */
    ResponseVo addProduct(Product product);

    /**
     * 查询店家商品
     * @param merchant
     * @return
     */
    List<Product> findMerchantProductList(Integer merchant);

    /**
     * 查询店家商品（分页）
     * @param merchantId
     * @param page
     * @param size
     * @return
     */
    PageVo findMerchantProductPage(Integer merchantId, Integer page, Integer size);

    /**
     * 删除产品
     * @param productId
     * @return
     */
    ResponseVo deleteProduct(Integer productId);

    /**
     * 修改商品
     * @param product
     * @return
     */
    ResponseVo updateProduct(Product product);

}
