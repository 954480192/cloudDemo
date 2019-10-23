package com.example.user.config.jwt;

import lombok.Data;

public enum  Constant {
    SIGIN_KEY("test_key");

    String value;
    Constant(String value){
        this.value = value;
    }
}
