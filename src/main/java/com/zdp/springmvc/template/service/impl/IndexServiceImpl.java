package com.zdp.springmvc.template.service.impl;

import com.zdp.springmvc.template.entity.common.User;
import com.zdp.springmvc.template.entity.index.IndexResponse;
import com.zdp.springmvc.template.service.IndexService;
import org.springframework.stereotype.Service;


/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/9
 * Time 下午5:51
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Override
    public IndexResponse assemble(User user) {
        IndexResponse response = new IndexResponse();
        response.setNow(System.currentTimeMillis());
        // TODO 在这里组装对象
        return response;
    }
}
