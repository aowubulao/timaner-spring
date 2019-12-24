package com.neoniou.timaner.service;

/**
 * @author neo.zzj
 * @date 2019-11-24
 */
public interface EmailService {

    /**
     * 发送验证码
     * @param accepter 接收地址
     */
    void sendCheckCode(String accepter) throws Exception;

    /**
     * 在redis检测该邮箱是否重复申请
     * @param email 邮箱
     * @return 是否能发送
     */
    Boolean isEmailInTime(String email);
}
