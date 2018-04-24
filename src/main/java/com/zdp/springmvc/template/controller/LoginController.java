package com.zdp.springmvc.template.controller;

import com.zdp.springmvc.template.annotation.NoNeedLogin;
import com.zdp.springmvc.template.entity.login.LoginRequest;
import com.zdp.springmvc.template.service.LoginService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/3
 * Time 下午2:21
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * 对于不需要登录的接口可以直接在Controller方法上或者Controller类上加上@NoNeedLogin的注解
     * @param request
     * @param result
     * @return
     */
    @RequestMapping(value = "")
    @NoNeedLogin
    public Object login(@Valid LoginRequest request, BindingResult result) {
        return loginService.login(request);
    }
}
