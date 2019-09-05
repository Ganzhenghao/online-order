package com.dfyang.onlineorder.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单商品关联实体类
 */
@Data
@Entity
@Table(name = "indent_product")
public class IndentProduct {

    /** 商品id */
    @Id
    private Integer productId;

    /** 订单id */
    private Integer indentId;


    /** 商品数量 */
    private Integer number;

}
