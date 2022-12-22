package com.zdw.common.parent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JSONResult {
    //成功标识
    private boolean success = true;

    //消息
    private String msg;

    //返回数据
    private Object data;

    public JSONResult markSuccess(String msg,Object data) {
        this.msg = msg;
        this.data = data;
        return this;
    }

    public JSONResult markFail(String msg) {
        this.msg = msg;
        this.success = false;
        return this;
    }

    public JSONResult markFail(String msg,Object data) {
        this.msg = msg;
        this.success = false;
        this.data = data;
        return this;
    }

}