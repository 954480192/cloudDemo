package com.zp.cloud_common.utils;

import com.zp.cloud_common.components.CodeEnum;
import com.zp.cloud_common.components.MessageEnum;

public class ResponseVo {
    public static ResultVo success(Object data){
        return new ResultVo(MessageEnum.SUCCESS.getCode(),MessageEnum.SUCCESS.getMsg(),data);
    }
    public static ResultVo error(){
        return new ResultVo(MessageEnum.ERROR.getCode(),MessageEnum.ERROR.getMsg(),null);
    }
    public static ResultVo error(Integer code,String msg){
        return new ResultVo(code,msg,null);
    }

    public static ResultVo info(CodeEnum msg){
        return new ResultVo(msg.getCode(),msg.getMsg(),null);
    }
}
