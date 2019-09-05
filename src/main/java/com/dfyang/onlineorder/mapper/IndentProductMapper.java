package com.dfyang.onlineorder.mapper;

import com.dfyang.onlineorder.dto.output.IndentProductOutputDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndentProductMapper {

    @Select("select ip.number,p.product_name from indent_product ip" +
            " join product p on ip.product_id = p.product_id where indent_id = #{indentId}")
    List<IndentProductOutputDto> findIndentProductOutputDto(Integer indentId);

}
