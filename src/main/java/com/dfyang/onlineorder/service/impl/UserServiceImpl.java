package com.dfyang.onlineorder.service.impl;

import com.dfyang.onlineorder.bean.ResponseVoFactory;
import com.dfyang.onlineorder.dao.UserDao;
import com.dfyang.onlineorder.dto.input.UserInfoDto;
import com.dfyang.onlineorder.dto.input.UsernamePasswordDto;
import com.dfyang.onlineorder.entity.User;
import com.dfyang.onlineorder.service.UserService;
import com.dfyang.onlineorder.utils.MD5Util;
import com.dfyang.onlineorder.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpSession session;

    @Autowired
    private ResponseVoFactory responseVoFactory;

    @Autowired
    private UserDao userDao;

    /**
     * 新增用户
     * @param user
     */
    @Override
    public ResponseVo insertUser(User user) {
        if (userDao.existsUserByUsername(user.getUsername()))
            return responseVoFactory.setFailure("用户名已存在!");
        if (userDao.existsUserByEmail(user.getEmail()))
            return responseVoFactory.setFailure("邮箱已存在!");
        user.setPassword(MD5Util.encrypt(user.getPassword())); // 加密
        user.setNickname(user.getUsername()); // 默认昵称为用户名
        User insertUser = userDao.save(user);
        return insertUser == null ?
                responseVoFactory.setFailure() : responseVoFactory.setSuccess(insertUser);
    }

    /**
     * 用户登录
     * @param usernamePasswordDto
     * @return
     */
    @Override
    public ResponseVo loginUser(UsernamePasswordDto usernamePasswordDto) {
        User findUser = userDao.findUserByUsernameAndPassword(usernamePasswordDto.getUsername(),
                MD5Util.encrypt(usernamePasswordDto.getPassword()));
        return findUser == null ?
                responseVoFactory.setFailure("用户名或密码输入错误!") : responseVoFactory.setSuccess(findUser);
    }

    /**
     * 忘记密码
     * @param user
     * @return
     */
    @Transactional
    @Override
    public ResponseVo forgetPassword(User user) {
        User findUser = userDao.findUserByUsernameAndEmail(user.getUsername(), user.getEmail());
        findUser.setPassword(MD5Util.encrypt(user.getPassword()));
        return responseVoFactory.setSuccess();
    }

    /**
     * 修改用户信息
     * @param userInfoDto
     * @return
     */
    @Transactional
    @Override
    public ResponseVo alterUser(UserInfoDto userInfoDto) {
        User sessionUser = (User) session.getAttribute("user");
        User findUser = userDao.findById(sessionUser.getUserId()).get();
        findUser.setPhone(userInfoDto.getPhone());
        findUser.setNickname(userInfoDto.getNickname());
        findUser.setAddress(userInfoDto.getAddress());
        return responseVoFactory.setSuccess(findUser);
    }

    /**
     * 修改用户密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @Transactional
    @Override
    public ResponseVo alterPassword(String oldPassword, String newPassword) {
        User sessionUser = (User) session.getAttribute("user");
        User findUser = userDao.findUserByUserIdAndPassword(sessionUser.getUserId(), MD5Util.encrypt(oldPassword));
        if (findUser == null)
            return responseVoFactory.setFailure("密码输入有误!");
        findUser.setPassword(MD5Util.encrypt(newPassword));
        return responseVoFactory.setSuccess();
    }

}
