package com.dfyang.onlineorder.service.impl;

import com.dfyang.onlineorder.bean.ResponseVoFactory;
import com.dfyang.onlineorder.dao.MerchantDao;
import com.dfyang.onlineorder.entity.Merchant;
import com.dfyang.onlineorder.entity.User;
import com.dfyang.onlineorder.enums.MerchantStatusEnum;
import com.dfyang.onlineorder.mapper.MerchantMapper;
import com.dfyang.onlineorder.service.MerchantService;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantDao merchantDao;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ResponseVoFactory responseVoFactory;

    /**
     * 申请成为店家
     * @param credentials
     * @return
     */
    @Override
    public Merchant applyMerchant(String credentials) {
        Merchant merchant = new Merchant();
        User user = (User) httpSession.getAttribute("user");
        merchant.setUserId(user.getUserId());
        merchant.setStatus(MerchantStatusEnum.WAIT_REVIEW.getStatus());
        merchant.setCredentials(credentials);
        merchant.setMerchantImg("/upload/default.jpg");
        merchant.setMerchantGrade(new BigDecimal(0));
        merchant.setMinConsume(new BigDecimal(0));
        merchant.setDeliveryCost(new BigDecimal(0));
        merchant.setMerchantName(user.getNickname());
        merchant.setSell(0);
        merchant = merchantDao.save(merchant);
        return merchant;
    }

    /**
     * 通过店家id查询状态
     * @param merchantId
     * @return
     */
    @Override
    public Integer findStatusByMerchantId(Integer merchantId) {
        return merchantDao.findById(merchantId).get().getStatus();
    }

    /**
     * 通过用户id查询店家
     * @param userId
     * @return
     */
    @Override
    public Merchant findMerchantByUserId(Integer userId) {
        return merchantDao.findMerchantByUserId(userId);
    }

    /**
     * 修改店铺图片
     * @param merchantImg
     * @return
     */
    @Transactional
    @Override
    public ResponseVo updateMerchantImg(String merchantImg) {
        Integer merchantId = (Integer) httpSession.getAttribute("merchantId");
        Merchant findMerchant = merchantDao.findById(merchantId).get();
        findMerchant.setMerchantImg(merchantImg);
        return responseVoFactory.setSuccess();
    }

    @Transactional
    @Override
    public ResponseVo updateMerchant(Merchant merchant) {
        Integer merchantId = (Integer) httpSession.getAttribute("merchantId");
        Merchant findMerchant = merchantDao.findById(merchantId).get();
        findMerchant.setDeliveryCost(merchant.getDeliveryCost());
        findMerchant.setMerchantName(merchant.getMerchantName());
        findMerchant.setMinConsume(merchant.getMinConsume());
        return responseVoFactory.setSuccess(findMerchant);
    }

    @Override
    public List<Merchant> findAll() {
        return merchantDao.findAll();
    }

    @Override
    public PageVo findMerchantPage(int page, int size) {
        List<Merchant> merchants = merchantMapper.findMerchantPage((page - 1) * size, size);
        int total = merchantMapper.findMerchantCount();
        int tmp = total / size;
        int pages = total % size == 0 ? tmp : tmp + 1;
        return new PageVo(page, size, total, pages, merchants);
    }

    @Override
    public Merchant findMerchantById(Integer merchantId) {
        return merchantDao.findById(merchantId).get();
    }

    @Override
    public PageVo searchMerchantPage(String merchantName, int page, int size) {
        List<Merchant> merchants = merchantMapper.searchMerchantPage(merchantName, (page - 1) * size, size);
        int total = merchantMapper.findMerchantCountByName(merchantName);
        int tmp = total / size;
        int pages = total % size == 0 ? tmp : tmp + 1;
        return new PageVo(page, size, total, pages, merchants);
    }
}
