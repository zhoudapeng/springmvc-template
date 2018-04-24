package com.zdp.springmvc.template.controller;

import com.zdp.springmvc.template.enumeration.ResultCode;
import com.zdp.springmvc.template.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/24
 * Time 下午3:53
 */
@RestController
@RequestMapping("/exception")
public class ExceptionDemo {

    @RequestMapping("/demo")
    public Object demo() {
        throw new BusinessException(ResultCode.SYSTEM_ERROR);
    }
}
