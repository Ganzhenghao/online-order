package com.dfyang.onlineorder.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * ClassName Admin
 * Description TODO
 * Author Ganzhenghao
 * Date  2019/9/5 18:05
 * Version 1.0
 **/

@Data
public class Admin implements Serializable {
    /* 管理员id */
    private int id;
    /*管理员账号*/
    private String username;
    /*管理员密码*/
    private String password;
}
