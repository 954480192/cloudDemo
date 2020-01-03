package com.zp.cloud_common.components;

import com.google.gson.Gson;
import com.zp.cloud_common.utils.ResponseVo;
import com.zp.cloud_common.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class ResponseAdviceCom  implements ResponseBodyAdvice{
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        Object obj = methodParameter.getMethodAnnotation(IgnoreAnotion.class);
        if(obj != null){
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof ResultVo){
            return o;
        }else if(o instanceof  String){
            return new Gson().toJson(ResponseVo.success(o));
        }
        log.info("-----------  {}方法需要封装统一响应！  -------------",methodParameter.getMethod().getName());
        return ResponseVo.success(o);
    }
}
