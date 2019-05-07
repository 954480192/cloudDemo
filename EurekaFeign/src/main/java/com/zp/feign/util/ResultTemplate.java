package com.zp.feign.util;

import lombok.Data;

@Data
public class ResultTemplate<T> {
    String message = "成功";
    boolean success = true;
    T data;

    public ResultTemplate(){

    }
    public ResultTemplate(T data){
        this.data = data;
    }
    public ResultTemplate(String message,boolean success,T data){
        this.data = data;
        this.message = message;
        this.success = success;
    }
}
