package com.zdp.springmvc.template.entity.common;


/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/3
 * Time 下午2:24
 */
public class AppResponse {
    private Bstatus bstatus;
    private Object data;

    public Bstatus getBstatus() {
        return bstatus;
    }

    public void setBstatus(Bstatus bstatus) {
        this.bstatus = bstatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
