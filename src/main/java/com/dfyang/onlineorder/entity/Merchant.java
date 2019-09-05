package com.dfyang.onlineorder.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 店家
 */

@Table(name = "merchant")
@Entity
@Data
public class Merchant {

    /** 店家ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer merchantId;

    /** 用户ID */
    private Integer userId;

    /** 状态 */
    private Integer status;

    /** 证明文件 */
    private String credentials;

    /** 店铺名称 */
    private String merchantName;

    /** 店铺评级 */
    private BigDecimal merchantGrade;

    /** 最低消费 */
    private BigDecimal minConsume;

    /** 配送费 */
    private BigDecimal deliveryCost;

    /** 销量 */
    private Integer sell;

    /** 商铺图片 */
    private String merchantImg;

}
