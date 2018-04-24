package com.zdp.springmvc.template.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zdp.springmvc.template.entity.common.User;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/9
 * Time 下午2:06
 */
public class JsonUtil {
    public static String toJson(Object object) {
        if (object instanceof String) {
            return String.valueOf(object);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(object);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("周大鹏");
        System.out.println(toJson(user));
    }
}
