package com.neoniou.timaner.util.exception;

/**
 * @author neo.zzj
 * @date 2019-11-1
 */
public enum ExceptionEnum {

    /**
     *
     */
    USER_NOT_FOUND(404, "用户未找到！"),
    LOGIN_FAIL(400, "登录失败！"),
    SCHEDULE_NOT_FOUND(404, "日程未找到！"),
    SCHEDULE_ADD_FAIL(500, "Schedule保存失败！"),
    MAIL_SEND_FAIL(500, "邮件发送失败！"),
    CHECK_CODE_WRONG(500, "验证码错误！"),
    REGIST_FAIL(500, "注册失败！"),
    RESET_PASSWORD_FAIL(500, "重置密码失败！"),
    DELETE_SCHEDULE_FAIL(500,"删除Schedule失败！"),
    ;
    private int statusCode;
    private String message;

    ExceptionEnum() {
    }

    ExceptionEnum(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}