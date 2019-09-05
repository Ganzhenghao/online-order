package com.dfyang.onlineorder.service;

import com.dfyang.onlineorder.dto.input.UserInfoDto;
import com.dfyang.onlineorder.dto.input.UsernamePasswordDto;
import com.dfyang.onlineorder.entity.User;
import com.dfyang.onlineorder.vo.ResponseVo;

public interface UserService {

    /**
     * 新增用户
     * @param user
     */
    ResponseVo insertUser(User user);

    /**
     * 用户登录
     * @param usernamePasswordDto
     * @return
     */
    ResponseVo loginUser(UsernamePasswordDto usernamePasswordDto);

    /**
     * 忘记密码
     * @param user
     * @return
     */
    ResponseVo forgetPassword(User user);

    /**
     * 修改用户信息
     * @param userInfoDto
     * @return
     */
    ResponseVo alterUser(UserInfoDto userInfoDto);

    /**
     * 修改用户密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    ResponseVo alterPassword(String oldPassword, String newPassword);
}
