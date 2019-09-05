package com.dfyang.onlineorder.dto.output;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class IndentOutputDto {

    private Integer indentId;

    private Integer indentStatus;

    private String merchantName;

    private String merchantImg;

    private List<IndentProductOutputDto> indentProductOutputDtos;

    private String address;

    private String nickname;

    private BigDecimal totalAmount;

}
