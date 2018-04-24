package com.zdp.springmvc.template.controller;

import com.zdp.springmvc.template.annotation.LoggedIn;
import com.zdp.springmvc.template.entity.common.User;
import com.zdp.springmvc.template.service.IndexService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/9
 * Time 下午5:37
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Resource
    private IndexService indexService;

    /**
     * Controller参数中直接使用 @LogginedIn User user
     * @param user
     * @return
     */
    @RequestMapping("")
    public Object home(@LoggedIn User user) {
        return indexService.assemble(user);
    }
}
