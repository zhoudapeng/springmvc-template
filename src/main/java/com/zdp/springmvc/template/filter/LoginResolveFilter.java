package com.zdp.springmvc.template.filter;

import com.zdp.springmvc.template.component.ApplicationContextUtil;
import com.zdp.springmvc.template.consts.ParamConsts;
import com.zdp.springmvc.template.entity.common.User;
import com.zdp.springmvc.template.service.UserResolveService;
import com.zdp.springmvc.template.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/9
 * Time 下午5:07
 */
public class LoginResolveFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoginResolveFilter.class);
    private UserResolveService userResolveService = ApplicationContextUtil.getBean(UserResolveService.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init LoginResolveFilter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String accessToken = request.getParameter(ParamConsts.ACCESS_TOKEN);
        User user = userResolveService.resolve(accessToken);
        logger.info("解析登录信息:user=" + JsonUtil.toJson(user));
        if (user != null) {
            request.setAttribute(ParamConsts.USER,user);
        }
        chain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {
        logger.info("destroy LoginResolveFilter...");
    }
}
