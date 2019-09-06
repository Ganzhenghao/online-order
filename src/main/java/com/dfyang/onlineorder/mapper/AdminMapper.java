package com.dfyang.onlineorder.mapper;

import com.dfyang.onlineorder.entity.Admin;
import com.dfyang.onlineorder.entity.Merchant;
import com.dfyang.onlineorder.entity.Product;
import com.dfyang.onlineorder.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName AdminMapper
 * Description TODO
 * Author Ganzhenghao
 * Date  2019/9/5 18:11
 * Version 1.0
 **/
@Repository
public interface AdminMapper {
    /**
     * 保存一个Admin账户
     * @param admin
     */
    @Insert("insert into admin values(null,#{username},#{password})")
    void saveAdmin(Admin admin);

    /**
     *查询一个admin账户
     * @param admin
     * @return
     */
    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin findAdmin(Admin admin);

    /**
     * 查询所商家
     * @return 商家的集合
     */
    @Results(id = "merchantMap",
            value = {
            @Result(id = true, column ="merchant_id",property = "merchantId"),
            @Result(column ="merchant_id",property = "userId"),
            @Result(column ="status",property = "status"),
            @Result(column ="merchant_name",property = "merchantName"),
            @Result(column ="merchant_grade",property = "merchantGrade"),
            @Result(column ="merchant_img",property = "merchantImg"),
            @Result(column ="min_consume",property = "minConsume"),
            @Result(column = "delivery_cost",property ="deliveryCost" ),
            @Result(column ="sell",property = "sell"),
            @Result(column ="credentials",property = "credentials")
            }
    )
    @Select("select * from merchant")
    List<Merchant> findAllMerchant();

    /**
     * 查询所有用户
     * @return
     */
    @Results(id = "userMap",
            value = {
            @Result(id = true ,column = "user_id",property = "userId"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "email",property = "email"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "address",property = "address"),
            @Result(column = "status",property = "status")
            }
    )
    @Select("select * from user")
    List<User> findAllUser();

    /**
     * 查询所有商品
     * @return
     */
    @Results(id = "productMap",
            value = {
            @Result(id = true ,column = "product_id",property = "productId"),
            @Result(column = "product_name",property = "productName"),
            @Result(column = "product_price",property = "productPrice"),
            @Result(column = "product_desc",property = "productDesc"),
            @Result(column = "product_img",property = "productImg"),
            @Result(column = "product_sell",property = "productSell"),
            @Result(column = "product_status",property = "productStatus")
            }
    )
    @Select("select * from product")
    List<Product> findAllProduct();

    /**
     * 修改商家状态码
     * @param merchant
     */
    @Update("update merchant set status = #{status} where merchant_id = #{merchantId}")
    void changeMerchantStatus(Merchant merchant);

    /**
     * 修改用户状态码
     * @param user
     */
    @Update("update user set status = #{status} where user_id = #{userId}")
    void changeUserStatus(User user);

    /**
     * 修改商品状态码
     * @param product
     */
    @Update("update product set product_status = #{status} where product_id = #{productId}")
    void changeProductStatus(Product product);

}
