package com.zp.cloud_common.components;

import com.zp.cloud_common.utils.ResponseVo;
import com.zp.cloud_common.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionAdviceCom {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo exceptionHandler(Exception e){
        log.error(e.getMessage());
        return ResponseVo.error(-1,e.getMessage());
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResultVo userExceptionHandler(UserException e){
        log.error(e.getMessage());
        return ResponseVo.error(e.getCode(),e.getMsg());
    }

}
