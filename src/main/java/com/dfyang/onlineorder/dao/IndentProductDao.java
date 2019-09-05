package com.dfyang.onlineorder.dao;

import com.dfyang.onlineorder.entity.IndentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndentProductDao extends JpaRepository<IndentProduct, Integer> {

}
