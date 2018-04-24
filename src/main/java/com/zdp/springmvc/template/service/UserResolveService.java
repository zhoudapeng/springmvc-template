package com.zdp.springmvc.template.service;

import com.zdp.springmvc.template.entity.common.User;

public interface UserResolveService {
    User resolve(String accessToken);
}
