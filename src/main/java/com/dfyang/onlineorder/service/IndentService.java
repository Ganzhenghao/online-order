package com.dfyang.onlineorder.service;

import com.dfyang.onlineorder.dto.input.IndentDto;
import com.dfyang.onlineorder.vo.PageVo;
import com.dfyang.onlineorder.vo.ResponseVo;

public interface IndentService {

    /**
     * 新增订单
     * @param indentDto
     * @return
     */
    ResponseVo insertIndent(IndentDto indentDto);

    PageVo findIndentOutputDtoPage(int page, int size);

    PageVo findMerchantIndentOutputDtoPage(Integer merchantId, int page, int size);

    ResponseVo agreeIndent(Integer indentId);

    ResponseVo refuseIndent(Integer indentId);
}
