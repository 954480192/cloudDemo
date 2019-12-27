package com.zp.cloud_common.components;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ErrorEnum implements CodeEnum {
    /** 10~100 参数 **/
    PARAMS_EMPTY(10,"参数为空！"),
    ENTITY_NOT_FOUND(101,"未找到实体！");
    Integer code;
    String msg;
    ErrorEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
