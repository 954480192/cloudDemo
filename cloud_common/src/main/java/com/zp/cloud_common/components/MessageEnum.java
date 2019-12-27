package com.zp.cloud_common.components;

import lombok.Getter;
@Getter
public enum MessageEnum implements CodeEnum{
    ERROR(-1,"失败"),
    SUCCESS(0,"成功");
    private Integer code;
    private String msg;
    MessageEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
