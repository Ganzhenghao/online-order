package com.dfyang.onlineorder.service;

import com.dfyang.onlineorder.entity.MerchantProduct;
import com.dfyang.onlineorder.vo.ResponseVo;

public interface MerchantProductService {

    ResponseVo insertMerchantProduct(MerchantProduct merchantProduct);

}
