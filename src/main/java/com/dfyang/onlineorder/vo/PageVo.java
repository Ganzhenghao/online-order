package com.dfyang.onlineorder.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 55411
 * @Date: 2019/8/2 15:00
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {
    private Integer page;

    private Integer size;

    private Integer total;

    private Integer pages;

    private List<T> data;
}
