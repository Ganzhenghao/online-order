package com.dfyang.onlineorder.dto.input;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 用户名密码
 */
@Data
public class UsernamePasswordDto {

    /** 用户名 */
    @NotNull(message = "用户名不能为空")
    @Length(min = 6, max = 16, message = "用户名为6-16位")
    private String username;

    /** 密码 */
    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码为6-16位")
    private String password;

}
