package com.dfyang.onlineorder.service.impl;

import com.dfyang.onlineorder.entity.Admin;
import com.dfyang.onlineorder.entity.Merchant;
import com.dfyang.onlineorder.entity.Product;
import com.dfyang.onlineorder.entity.User;
import com.dfyang.onlineorder.mapper.AdminMapper;
import com.dfyang.onlineorder.service.AdminService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName AdminServiceImpl
 * Description TODO
 * Author Ganzhenghao
 * Date  2019/9/5 18:19
 * Version 1.0
 **/

@Service
public class AdminServiceImpl implements AdminService {
    @Resource(name = "adminMapper")
    private AdminMapper adminDao;
    @Override
    public void saveAdmin(Admin admin) {
        adminDao.saveAdmin(admin);
    }

    @Override
    public Admin findAdmin(Admin admin) {
        adminDao.findAdmin(admin);
        return adminDao.findAdmin(admin);
    }

    @Override
    public List<Merchant> findAllMerchant(int page, int size) {
        PageHelper.startPage(page, size);
        return adminDao.findAllMerchant();
    }

    @Override
    public List<User> findAllUser(int page, int size) {
        PageHelper.startPage(page, size);
        return adminDao.findAllUser();
    }

    @Override
    public List<Product> findAllProduct(int page, int size) {
        PageHelper.startPage(page, size);
        return adminDao.findAllProduct();
    }


    @Override
    public void changeMerchantStatus(Merchant merchant) {
        adminDao.changeMerchantStatus(merchant);
    }

    @Override
    public void changeUserStatus(User user) {
        adminDao.changeUserStatus(user);
    }

    @Override
    public void changeProductStatus(Product product) {
        adminDao.changeProductStatus(product);
    }


}
