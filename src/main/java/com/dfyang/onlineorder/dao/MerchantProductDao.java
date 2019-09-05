package com.dfyang.onlineorder.dao;

import com.dfyang.onlineorder.entity.MerchantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProductDao extends JpaRepository<MerchantProduct, Integer> {

}
