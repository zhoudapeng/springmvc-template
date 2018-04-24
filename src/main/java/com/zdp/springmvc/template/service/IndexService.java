package com.zdp.springmvc.template.service;

import com.zdp.springmvc.template.entity.common.User;
import com.zdp.springmvc.template.entity.index.IndexResponse;

public interface IndexService {
    IndexResponse assemble(User user);
}
