package com.dfyang.onlineorder.mapper;

import com.dfyang.onlineorder.dto.output.IndentOutputDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndentMapper {

    @Select("select i.indent_id,i.indent_status,i.address,i.nickname,i.total_amount, " +
            "m.merchant_name,m.merchant_img " +
            "from indent i " +
            "join merchant m on m.merchant_id = i.merchant_id " +
            "where i.user_id = #{userId}  order by i.create_time desc limit ${start},#{end}")
    List<IndentOutputDto> findIndentOutputDto(int userId, int start, int end);

    @Select("select count(1) from indent where user_id = #{userId}")
    int findTotalIndent(Integer userId);

    @Select("select i.indent_id,i.indent_status,i.address,i.nickname,i.total_amount, " +
            "m.merchant_name,m.merchant_img " +
            "from indent i " +
            "join merchant m on m.merchant_id = i.merchant_id " +
            "where i.merchant_id = #{merchantId}  order by i.create_time desc limit ${start},#{end}")
    List<IndentOutputDto> findMerchantIndentOutputDto(int merchantId, int start, int end);

    @Select("select count(1) from indent where merchant_id = #{merchantId}")
    int findMerchantTotalIndent(int merchantId);
}
