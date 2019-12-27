package com.zp.cloud_common.components;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class UserException extends RuntimeException {
    private Integer code;
    private String msg;
    public UserException(CodeEnum error){
        super(error.getMsg());
        this.code =error.getCode();
        this.msg = error.getMsg();
    }
    public UserException(Integer code,String msg){
        super(msg);
        this.code =code;
        this.msg = msg;
    }
}
