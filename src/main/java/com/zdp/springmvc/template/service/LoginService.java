package com.zdp.springmvc.template.service;

import com.zdp.springmvc.template.entity.common.User;
import com.zdp.springmvc.template.entity.login.LoginRequest;

public interface LoginService {
    User login(LoginRequest request);

    void logout(User user);
}
