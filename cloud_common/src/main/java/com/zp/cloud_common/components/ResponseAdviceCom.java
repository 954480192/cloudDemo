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
        Object obj = methodParameter.getMethodAnnotation(NoResponseAnotion.class);
        if(obj == null){
            return true;
        }
        log.info("-----------  {}方法不返回统一响应！  -------------",methodParameter.getMethod().getName());
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof ResultVo){
            return o;
        }
        log.info("---------- 处理响应{}",new Gson().toJson(o));
        return ResponseVo.success(o);
    }
}
