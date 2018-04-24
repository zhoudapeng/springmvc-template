package com.zdp.springmvc.template.service.impl;

import com.zdp.springmvc.template.entity.common.User;
import com.zdp.springmvc.template.service.UserResolveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/9
 * Time 下午4:41
 */
@Service
public class UserResolveServiceImpl implements UserResolveService {
    private static final Logger logger = LoggerFactory.getLogger(UserResolveServiceImpl.class);
    @Override
    public User resolve(String accessToken) {
        User user = new User();
        user.setName("测试名");
        user.setMchId("测试id");
        user.setUserId("测试userId");
        return user;
    }
}
