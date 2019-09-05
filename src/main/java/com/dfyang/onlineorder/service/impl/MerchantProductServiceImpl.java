package com.dfyang.onlineorder.service.impl;

import com.dfyang.onlineorder.bean.ResponseVoFactory;
import com.dfyang.onlineorder.dao.MerchantProductDao;
import com.dfyang.onlineorder.entity.MerchantProduct;
import com.dfyang.onlineorder.service.MerchantProductService;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantProductServiceImpl implements MerchantProductService {

    @Autowired
    private MerchantProductDao merchantProductDao;

    @Autowired
    private ResponseVoFactory responseVoFactory;

    @Override
    public ResponseVo insertMerchantProduct(MerchantProduct merchantProduct) {
        return merchantProductDao.save(merchantProduct) == null ?
                responseVoFactory.setFailure() : responseVoFactory.setSuccess();
    }
}
