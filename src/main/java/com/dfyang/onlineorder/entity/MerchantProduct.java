package com.dfyang.onlineorder.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "merchant_product")
@Entity
public class MerchantProduct {

    @Id
    private Integer productId;

    private Integer merchantId;
}
