package com.neoniou.timaner.dao;

import com.neoniou.timaner.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author neo.zzj
 * @date 2019-11-17
 */
@Repository
public interface UserDao extends Mapper<User> {

    /**
     * selectById
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User selectById(int id);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> queryAll();

    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select id, username, email from user where email = #{email} and password = #{password}")
    User login(User user);

    /**
     * 查询手机号是否存在
     * @param email
     * @return
     */
    @Select("select count(*) from user where email = #{email}")
    int selectPhone(String email);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    @Select("select count(*) from user where username = #{username}")
    int selectUsername(String username);

    /**
     * 注册
     * @param user 对象
     * @return count
     */
    @Insert("insert into user(email, password) values(#{email}, #{password})")
    int addUser(User user);

    /**
     * 根据uid查询用户邮箱
     * @param uid uid
     * @return user(email)
     */
    @Select("select email from user where id = #{uid}")
    User selectEmailByUid(int uid);

    /**
     * 重置密码
     * @param user 对象
     * @return count
     */
    @Update("update user set password = #{password} where email = #{email}")
    int updateUser(User user);
}
