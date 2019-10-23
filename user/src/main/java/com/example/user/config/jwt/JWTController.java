package com.example.user.config.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
public class JWTController {

    @GetMapping("/index")
    public Object index(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");

        return Jwts.parser().setSigningKey(Constant.SIGIN_KEY.value.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }
}
