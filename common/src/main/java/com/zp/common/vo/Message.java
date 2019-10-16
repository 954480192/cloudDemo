package com.zp.common.vo;

import lombok.Data;

@Data
public class Message {
    public String msg = "成功！";
    public boolean success = true;
    public Object data;

    public Message() {
    }

    public Message(String msg, boolean success, Object data) {
        this.msg = msg;
        this.success = success;
        this.data = data;
    }
}
