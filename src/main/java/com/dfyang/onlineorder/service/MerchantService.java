package com.dfyang.onlineorder.service;

import com.dfyang.onlineorder.entity.Merchant;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchantService {

    /**
     * 申请店家
     * @return
     */
    Merchant applyMerchant(String credentials);

    /**
     * 查询店家状态
     * @param merchantId
     * @return
     */
    Integer findStatusByMerchantId(Integer merchantId);


    /**
     * 通过用户id查询店家
     * @param userId
     * @return
     */
    Merchant findMerchantByUserId(Integer userId);

    /**
     * 修改店铺图片
     * @param merchantImg
     * @return
     */
    ResponseVo updateMerchantImg(String merchantImg);

    ResponseVo updateMerchant(Merchant merchant);

    List<Merchant> findAll();

    PageVo findMerchantPage(int page, int size);

    Merchant findMerchantById(Integer merchantId);

    PageVo searchMerchantPage(String merchantName, int page, int size);

}
