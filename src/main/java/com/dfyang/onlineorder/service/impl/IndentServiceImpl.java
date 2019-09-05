package com.dfyang.onlineorder.service.impl;

import com.dfyang.onlineorder.bean.ResponseVoFactory;
import com.dfyang.onlineorder.dao.IndentDao;
import com.dfyang.onlineorder.dao.IndentProductDao;
import com.dfyang.onlineorder.dao.ProductDao;
import com.dfyang.onlineorder.dto.input.IndentDto;
import com.dfyang.onlineorder.dto.output.IndentOutputDto;
import com.dfyang.onlineorder.dto.output.IndentProductOutputDto;
import com.dfyang.onlineorder.entity.Indent;
import com.dfyang.onlineorder.entity.IndentProduct;
import com.dfyang.onlineorder.entity.Product;
import com.dfyang.onlineorder.entity.User;
import com.dfyang.onlineorder.enums.IndentStatusEnum;
import com.dfyang.onlineorder.mapper.IndentMapper;
import com.dfyang.onlineorder.mapper.IndentProductMapper;
import com.dfyang.onlineorder.service.IndentService;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IndentServiceImpl implements IndentService {

    @Autowired
    private IndentDao indentDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private IndentProductDao indentProductDao;

    @Autowired
    private ResponseVoFactory responseVoFactory;

    @Autowired
    private IndentMapper indentMapper;

    @Autowired
    private IndentProductMapper indentProductMapper;

    @Autowired
    private HttpSession session;

    /**
     * 新增订单
     * @param indentDto
     * @return
     */
    @Transactional
    @Override
    public ResponseVo insertIndent(IndentDto indentDto) {
        Indent indent = new Indent();
        indent.setIndentStatus(IndentStatusEnum.WAIT_MERCHANT_AGREE.getStatus());
        User user = (User) session.getAttribute("user");
        indent.setUserId(user.getUserId());
        indent.setNickname(user.getNickname());
        indent.setAddress(user.getAddress());
        indent.setTotalAmount(new BigDecimal(0));
        indent.setMerchantId(indentDto.getMerchantId());
        indent.setCreateTime(new Date());
        indent = indentDao.save(indent);
        BigDecimal totalAmount = new BigDecimal(0);
        for (Map.Entry<Integer, Integer> e : indentDto.getProductMap().entrySet()) {
            Integer productId = e.getKey();
            Product product = productDao.findById(productId).get();
            IndentProduct indentProduct = new IndentProduct();
            indentProduct.setIndentId(indent.getIndentId());
            indentProduct.setNumber(e.getValue());
            indentProduct.setProductId(productId);
            indentProductDao.save(indentProduct);
            totalAmount = totalAmount.add(product.getProductPrice().multiply(new BigDecimal(e.getValue())));
        }
        indent.setTotalAmount(totalAmount);
        return responseVoFactory.setSuccess();
    }

    @Override
    public PageVo findIndentOutputDtoPage(int page, int size) {
        User user = (User) session.getAttribute("user");
        List<IndentOutputDto> indentOutputDtos = indentMapper.findIndentOutputDto(user.getUserId(), (page - 1) * size, size);
        indentOutputDtos.forEach(i -> {
            List<IndentProductOutputDto> indentProductOutputDtos =
                    indentProductMapper.findIndentProductOutputDto(i.getIndentId());
            i.setIndentProductOutputDtos(indentProductOutputDtos);
        });
        int total = indentMapper.findTotalIndent(user.getUserId());
        int tmp = total / size;
        int pages = total % size == 0 ? tmp : tmp + 1;
        return new PageVo(page, size, total, pages, indentOutputDtos);
    }

    @Override
    public PageVo findMerchantIndentOutputDtoPage(Integer merchantId, int page, int size) {
        List<IndentOutputDto> indentOutputDtos = indentMapper.findMerchantIndentOutputDto(merchantId, (page - 1) * size, size);
        indentOutputDtos.forEach(i -> {
            List<IndentProductOutputDto> indentProductOutputDtos =
                    indentProductMapper.findIndentProductOutputDto(i.getIndentId());
            i.setIndentProductOutputDtos(indentProductOutputDtos);
        });
        int total = indentMapper.findMerchantTotalIndent(merchantId);
        int tmp = total / size;
        int pages = total % size == 0 ? tmp : tmp + 1;
        return new PageVo(page, size, total, pages, indentOutputDtos);
    }

    @Transactional
    @Override
    public ResponseVo agreeIndent(Integer indentId) {
        Indent indent = indentDao.findById(indentId).get();
        indent.setIndentStatus(IndentStatusEnum.WAIT_RIDER_AGREE.getStatus());
        return responseVoFactory.setSuccess();
    }

    @Transactional
    @Override
    public ResponseVo refuseIndent(Integer indentId) {
        Indent indent = indentDao.findById(indentId).get();
        indent.setIndentStatus(IndentStatusEnum.REFUSED.getStatus());
        return responseVoFactory.setSuccess();
    }

}
