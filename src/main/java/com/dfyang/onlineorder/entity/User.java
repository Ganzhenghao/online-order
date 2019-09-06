package com.dfyang.onlineorder.entity;

import com.dfyang.onlineorder.enums.MerchantStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * 用户实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {

    /** 用户ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 昵称 */
    private String nickname;

    /** 邮箱 */
    private String email;

    /** 电话 */
    private String phone;

    /** 地址 */
    private String address;

    private Integer status;

}
