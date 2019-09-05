package com.dfyang.onlineorder.mapper;

import com.dfyang.onlineorder.entity.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    /**
     * 查询店家商品
     * @param merchantId
     * @return
     */
    @Select("select product_id,product_name,product_price,product_desc,product_img from Product where product_id in" +
            " (select product_id from merchant_product where merchant_id = #{merchantId})")
    List<Product> findMerchantProductList(Integer merchantId);

    /**
     * 查询店家商品（分页）
     * @param merchantId
     * @param start
     * @param end
     * @return
     */
    @Select("select product_id,product_name,product_price,product_desc,product_img,product_sell from Product where product_id in" +
            " (select product_id from merchant_product where merchant_id = #{merchantId}) limit #{start},#{end}")
    List<Product> findMerchantProductPage(Integer merchantId, Integer start, Integer end);

    /**
     * 查询店家商品总数
     * @param merchantId
     * @return
     */
    @Select("select count(1) from merchant_product where merchant_id  = #{merchantId}")
    int findMerchantProductCount(Integer merchantId);
}
