package com.zdp.springmvc.template.entity.common;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/3
 * Time 下午2:23
 */
public class Bstatus {
    private int code;
    private String desc;
    private String msg;

    public Bstatus() {
    }

    public Bstatus(int code, String desc, String msg) {
        this.code = code;
        this.desc = desc;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Builder {
        private int code;
        private String desc;
        private String msg;

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Bstatus build() {
            return new Bstatus(code,desc,msg);
        }
    }
}
