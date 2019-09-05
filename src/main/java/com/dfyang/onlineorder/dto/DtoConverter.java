package com.dfyang.onlineorder.dto;

/**
 * @Author: 55411
 * @Date: 2019/8/20 19:07
 * @Description: DTO转换器
 */
public interface DtoConverter<S, T> {

    /**
     * 转换为T（目标target）
     * @return
     */
    T convert();

    /**
     * 转换为S（源source）
     * @param t
     * @return
     */
    S forward(T t);
}
