package com.dfyang.onlineorder.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDesc;

    private String productImg;

    private Integer productSell;

}
