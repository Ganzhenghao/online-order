package com.dfyang.onlineorder.dto.input;

import lombok.Data;

import java.util.Map;

@Data
public class IndentDto {

    private Map<Integer, Integer> productMap;

    private Integer merchantId;

}