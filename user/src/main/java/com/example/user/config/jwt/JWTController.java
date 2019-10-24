package com.example.user.config.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
public class JWTController {

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/index")
    public Object index(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        return jwtUtil.createToken(null,"zp","");
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.abbreviate("abcdefg",3));
    }
}
