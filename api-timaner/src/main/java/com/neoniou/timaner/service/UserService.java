package com.neoniou.timaner.service;

import com.neoniou.timaner.pojo.User;

import java.util.List;

/**
 * user相关服务接口，user & username
 * @author neo.zzj
 * @date 2019-11-17
 */
public interface UserService {

    /**
     * 通过id获取用户
     * @param id 用户id
     * @return User
     */
    User getUser(int id);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();

    /**
     * 登录api
     * @param user 登录User对象
     * @return
     */
    User login(User user);

    /**
     * 异步请求检测邮箱有没有被注册
     * @param email
     * @return
     */
    Boolean checkEmail(String email);

    /**
     * 异步请求检测用户名有没有被注册
     * @param username
     * @return
     */
    Boolean checkUsername(String username);

    /**
     * 注册
     * @param user 对象
     * @param checkCode 验证码
     * @return true or false
     */
    void register(User user, String checkCode);

    /**
     * 重置密码
     * @param user 对象
     * @param checkCode 验证码
     * @return true or false
     */
    void resetPassword(User user, String checkCode);
}
