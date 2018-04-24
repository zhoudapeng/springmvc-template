package com.zdp.springmvc.template.enumeration;

/**
 * 0表示成功
 * 1开头表示用户侧异常
 * 2开头表示服务侧异常
 */
public enum ResultCode {
    SUCCESS(0,"成功"),
    NON_LOGIN(1001,"未登录"),
    LOGIN_FAIL(1002,"登录失败"),
    PARAM_ERR(1003, "参数错误"),
    SYSTEM_ERROR(2,"系统异常");
    private int code;
    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
