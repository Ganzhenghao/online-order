package com.dfyang.onlineorder.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
@Data
@Entity
@Table(name = "indent")
public class Indent {

    /** 订单id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer indentId;

    /** 创建者id */
    private Integer userId;

    /** 订单状态 */
    private Integer indentStatus;

    /** 订单总金额 */
    private BigDecimal totalAmount;

    /** 店家id */
    private Integer merchantId;

    /** 收货人 */
    private String nickname;

    /** 收货地址 */
    private String address;

    /** 订单创建时间 */
    private Date createTime;

}
