package com.zdp.springmvc.template.service.impl;

import com.zdp.springmvc.template.entity.common.User;
import com.zdp.springmvc.template.entity.login.LoginRequest;
import com.zdp.springmvc.template.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/19
 * Time 下午12:12
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public User login(LoginRequest request) {
        User user = new User();
        user.setMchId("1");
        user.setName("abc");
        return user;
    }

    @Override
    public void logout(User user) {
        return;
    }
}
