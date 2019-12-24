package com.neoniou.timaner.util;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author neo.zzj
 * @date 2019-11-29
 */
public class RedisUtil {

    private Jedis jedis = new Jedis("127.0.0.1");
    /**
     * 在Redis上存储验证码
     * @param email 邮箱
     * @param checkCode 验证码
     */
    public void setCheckCode(String email, String checkCode) {
        //将验证码存入redis ，15分钟过期
        jedis.del(email);
        jedis.set(email, checkCode);
        jedis.expire(email, 60 * 15);
    }

    /**
     * 在Redis上验证邮箱、验证码
     * @param email 邮箱
     * @param checkCode 验证码
     * @return 是否匹配
     */
    public boolean getCheckCode(String email, String checkCode) {
        String redisCheckCode = jedis.get(email);
        return checkCode.equalsIgnoreCase(redisCheckCode);
    }

    /**
     * 判断该邮箱是否在60s重复发送邮件
     * @param email 邮箱
     * @return true:未 false:是
     */
    public boolean isEmailInTime(String email) {
        String str = jedis.get(email);
        if (str == null) {
            jedis.set(email, "123");
            jedis.expire(email, 60);
            return true;
        } else {
            return false;
        }
    }
}
