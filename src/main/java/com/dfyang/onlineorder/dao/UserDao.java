package com.dfyang.onlineorder.dao;

import com.dfyang.onlineorder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户dao层
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 通过用户ID和密码查询用户
     * @param userId
     * @param password
     * @return
     */
    User findUserByUserIdAndPassword(Integer userId, String password);

    User findUserByUsernameAndEmail(String username, String email);

    /**
     * 查询邮箱是否已经注册
     * @param email
     * @return
     */
    boolean existsUserByEmail(String email);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    boolean existsUserByUsername(String username);

}
