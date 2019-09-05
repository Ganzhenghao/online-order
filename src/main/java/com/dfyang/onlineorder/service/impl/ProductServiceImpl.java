package com.dfyang.onlineorder.service.impl;

import com.dfyang.onlineorder.bean.ResponseVoFactory;
import com.dfyang.onlineorder.dao.MerchantProductDao;
import com.dfyang.onlineorder.dao.ProductDao;
import com.dfyang.onlineorder.entity.Product;
import com.dfyang.onlineorder.mapper.ProductMapper;
import com.dfyang.onlineorder.service.ProductService;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ResponseVoFactory responseVoFactory;

    @Autowired
    private MerchantProductDao merchantProductDao;

    /**
     * 新增商品
     * @param product
     * @return
     */
    @Override
    public ResponseVo addProduct(Product product) {
        product.setProductSell(0);
        product = productDao.save(product);
        return product == null ?
                responseVoFactory.setFailure() : responseVoFactory.setSuccess(product);
    }

    /**
     * 查询店家商品
     * @param merchant
     * @return
     */
    @Override
    public List<Product> findMerchantProductList(Integer merchant) {
        return productMapper.findMerchantProductList(merchant);
    }

    /**
     * 查询店家商品(分页)
     * @param merchantId
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageVo findMerchantProductPage(Integer merchantId, Integer page, Integer size) {
        List<Product> products = productMapper.findMerchantProductPage(merchantId, (page - 1) * size, size);
        int total = productMapper.findMerchantProductCount(merchantId);
        int tmp = total / size;
        int pages = total % size == 0 ? tmp : tmp + 1;
        return new PageVo(page, size, total, pages, products);
    }

    /**
     * 删除产品
     * @param productId
     * @return
     */
    @Override
    public ResponseVo deleteProduct(Integer productId) {
        productDao.deleteById(productId);
        merchantProductDao.deleteById(productId);
        return responseVoFactory.setSuccess();
    }

    /**
     * 修改商品
     * @param product
     * @return
     */
    @Transactional
    @Override
    public ResponseVo updateProduct(Product product) {
        Product findProduct = productDao.findById(product.getProductId()).get();
        if (StringUtils.isNotEmpty(product.getProductName()))
            findProduct.setProductName(product.getProductName());
        if (StringUtils.isNotEmpty(product.getProductImg()))
            findProduct.setProductImg(product.getProductImg());
        findProduct.setProductPrice(product.getProductPrice());
        findProduct.setProductDesc(product.getProductDesc());
        return responseVoFactory.setSuccess();
    }
}
