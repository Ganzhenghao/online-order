package com.dfyang.onlineorder.dto.input;

import com.dfyang.onlineorder.dto.DtoConverter;
import com.dfyang.onlineorder.entity.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * 用户修改信息dto
 */
@Data
public class UserInfoDto implements DtoConverter<UserInfoDto, User> {

    @NotNull(message = "昵称不能为空")
    @Length(min = 2, max = 12, message = "昵称必须为2-12位")
    private String nickname;

    @NotNull(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号格式不对")
    private String phone;

    private String address;

    @Override
    public User convert() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    @Override
    public UserInfoDto forward(User user) {
        BeanUtils.copyProperties(user, this);
        return this;
    }
}
