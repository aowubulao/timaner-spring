package com.neoniou.timaner.service.impl;

import com.neoniou.timaner.dao.UserDao;
import com.neoniou.timaner.pojo.User;
import com.neoniou.timaner.service.UserService;
import com.neoniou.timaner.util.RedisUtil;
import com.neoniou.timaner.util.exception.ExceptionEnum;
import com.neoniou.timaner.util.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author neo.zzj
 * @date 2019-11-17
 */
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(int id) {
        User user = userDao.selectById(id);
        if (user == null) {
            throw new MyException(ExceptionEnum.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public List<User> selectAll() {
        return userDao.queryAll();
    }

    @Override
    public User login(User user) {
        User loginUser = userDao.login(user);
        if (loginUser == null) {
            throw new MyException(ExceptionEnum.LOGIN_FAIL);
        }

        return loginUser;
    }

    @Override
    public Boolean checkEmail(String email) {
        int count = userDao.selectPhone(email);
        return count == 0;
    }

    @Override
    public Boolean checkUsername(String username) {
        int count = userDao.selectUsername(username);
        return count == 0;
    }

    @Override
    public void register(User user, String checkCode) {
        //验证 验证码
        RedisUtil redisUtil = new RedisUtil();
        if (!redisUtil.getCheckCode(user.getEmail(), checkCode)) {
            throw new MyException(ExceptionEnum.CHECK_CODE_WRONG);
        }

        //影响的行数
        int count = userDao.addUser(user);
        if (count == 0) {
            throw new MyException(ExceptionEnum.REGIST_FAIL);
        }
    }

    @Override
    public void resetPassword(User user, String checkCode) {
        //验证 验证码
        RedisUtil redisUtil = new RedisUtil();
        if (!redisUtil.getCheckCode(user.getEmail(), checkCode)) {
            throw new MyException(ExceptionEnum.CHECK_CODE_WRONG);
        }

        //影响的行数
        int count = userDao.updateUser(user);
        if (count == 0) {
            throw new MyException(ExceptionEnum.RESET_PASSWORD_FAIL);
        }
    }
}
