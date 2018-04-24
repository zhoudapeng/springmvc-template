package com.zdp.springmvc.template.controller;

import com.zdp.springmvc.template.entity.common.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/24
 * Time 下午3:08
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping("/detail")
    public ModelAndView detail(User user) {
        ModelAndView mav = new ModelAndView("profile/detail");
        mav.addObject("user",user);
        return mav;
    }
}
