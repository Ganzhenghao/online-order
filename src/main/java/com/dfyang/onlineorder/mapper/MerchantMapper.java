package com.dfyang.onlineorder.mapper;

import com.dfyang.onlineorder.entity.Merchant;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantMapper {

    /**
     * 查询已认证店家（分页）
     * @param start
     * @param end
     * @return
     */
    @Select("select * from merchant where status = 1 limit #{start},#{end}")
    List<Merchant> findMerchantPage(Integer start, Integer end);

    /**
     * 搜索已认证店家（分页）
     * @param merchantName
     * @param start
     * @param end
     * @return
     */
    @Select("select * from merchant where merchant_name like concat('%', #{merchantName}, '%') and status = 1 limit #{start},#{end}")
    List<Merchant> searchMerchantPage(String merchantName, int start, int end);

    /**
     * 查询已认证店家总数
     * @return
     */
    @Select("select count(1) from merchant where status = 1")
    int findMerchantCount();

    /**
     * 查询已认证店家总数
     * @return
     */
    @Select("select count(1) from merchant where merchant_name like concat('%', #{merchantName}, '%') and status = 1")
    int findMerchantCountByName(String merchantName);

}
