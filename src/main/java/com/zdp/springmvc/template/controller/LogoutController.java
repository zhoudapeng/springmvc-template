package com.zdp.springmvc.template.controller;

import com.zdp.springmvc.template.annotation.LoggedIn;
import com.zdp.springmvc.template.entity.common.User;
import com.zdp.springmvc.template.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/19
 * Time 下午2:53
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {
    @Resource
    private LoginService loginService;

    @RequestMapping("")
    public Object logout(@LoggedIn User user) {
        loginService.logout(user);
        return null;
    }
}
