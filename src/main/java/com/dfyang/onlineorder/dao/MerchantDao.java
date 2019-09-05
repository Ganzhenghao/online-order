package com.dfyang.onlineorder.dao;

import com.dfyang.onlineorder.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 店家dao层
 */
@Repository
public interface MerchantDao extends JpaRepository<Merchant, Integer> {

    /**
     * 通过用户id查询店家
     * @param userId
     * @return
     */
    Merchant findMerchantByUserId(Integer userId);

}
