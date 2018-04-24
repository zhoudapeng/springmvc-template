package com.zdp.springmvc.template.interceptor;

import com.zdp.springmvc.template.annotation.NoNeedLogin;
import com.zdp.springmvc.template.entity.common.AppResponse;
import com.zdp.springmvc.template.entity.common.Bstatus;
import com.zdp.springmvc.template.enumeration.ResultCode;
import com.zdp.springmvc.template.exception.BusinessException;
import com.zdp.springmvc.template.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/3
 * Time 下午2:28
 */
@Order(2)
@Aspect
@Component
public class ControllerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Pointcut("execution(* com.zdp.springmvc.template.controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut(){}

    @Around("controllerMethodPointcut()")
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        AppResponse response = new AppResponse();
        ResultCode resultCode = ResultCode.SUCCESS;
        Object data = null;
        String msg = "";
        try {
            Object[] args = pjp.getArgs();
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    if (arg != null && arg instanceof BindingResult) {
                        BindingResult result = (BindingResult) arg;
                        if (result.hasErrors()) {
                            List<ObjectError> errorList = result.getAllErrors();
                            for (ObjectError error : errorList) {
                                throw new BusinessException(ResultCode.PARAM_ERR, error.getDefaultMessage());
                            }
                        }
                    }
                }
            }
            data = pjp.proceed();
        }catch (BusinessException be) {
            logger.warn("业务异常",be);
            resultCode = be.getResultCode();
            msg = be.getMsg();
        }catch (Exception e) {
            logger.warn("系统异常",e);
            resultCode = ResultCode.SYSTEM_ERROR;
        }
        if (data instanceof AppResponse) {
            response = (AppResponse) data;
        }
        response.setBstatus(new Bstatus.Builder().setCode(resultCode.getCode()).setDesc(resultCode.getDesc()).setMsg(msg).build());
        response.setData(data);
        return JsonUtil.toJson(response);
    }
}
