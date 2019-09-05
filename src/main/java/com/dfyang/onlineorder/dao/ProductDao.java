package com.dfyang.onlineorder.dao;

import com.dfyang.onlineorder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {


}
