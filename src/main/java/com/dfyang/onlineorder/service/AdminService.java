package com.dfyang.onlineorder.service;

import com.dfyang.onlineorder.entity.Admin;
import com.dfyang.onlineorder.entity.Merchant;
import com.dfyang.onlineorder.entity.Product;
import com.dfyang.onlineorder.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName AdminService
 * Description TODO
 * Author Ganzhenghao
 * Date  2019/9/5 18:19
 * Version 1.0
 **/
public interface AdminService {
    /**
     * 保存一个Admin账户
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     *查询一个admin账户
     * @param admin
     * @return
     */
    Admin findAdmin(Admin admin);

    /**
     * 查询所商家
     * @return 商家的集合
     */
    List<Merchant> findAllMerchant(int page,int size);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser(int page , int size);

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAllProduct(int page , int size);

    /**
     * 修改商家状态码
     * @param merchant
     */
    void changeMerchantStatus(Merchant merchant);

    /**
     * 修改用户状态码
     * @param user
     */
    void changeUserStatus(User user);

    /**
     * 修改商品状态码
     * @param product
     */
    void changeProductStatus(Product product);

}
