package com.zdp.springmvc.template.entity.login;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/8
 * Time 下午2:17
 */
public class LoginRequest {
    @NotEmpty(message = "userName不能为空")
    private String userName;
    @NotEmpty(message = "password不能为空")
    private String password;
    @NotEmpty(message = "jpushId不能为空")
    private String jpushId;
    @NotEmpty(message = "deviceType不能为空")
    private String deviceType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
