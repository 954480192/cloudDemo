package com.example.user.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Map<String,Object> claims,String subject,String audience){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime()+ expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                //主题
                .setSubject(subject)
                // 接收者
                .setAudience(audience)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public String refreshToken(String token){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime()+ expiration * 1000);
        Claims claims = getClaimsFromToken(token);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String username = getObjectFromTokenByKey(token,"username",String.class);
        return username.equals(userDetails.getUsername())
                && validExpirationOfToken(token);
    }

    public Claims getClaimsFromToken(String token){
        return  Jwts.parser().
                setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getObjectFromTokenByKey(String token,String key,Class<T> type){
        Claims claims = getClaimsFromToken(token);
        return  claims.get(key,type);
    }

    public <T> T getClaimsFromToken(String token, Function<Claims,T> resolver){
        Claims claims = getClaimsFromToken(token);
        return resolver.apply(claims);
    }

    public String getIPFromToken(String token){
        return getObjectFromTokenByKey(token,"ip",String.class);
    }


    public String getSubjectFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    // token签发时间
    public Date getIssuedAtFromToken(String token){
        return getClaimsFromToken(token,Claims::getIssuedAt);
    }

    //过期时间
    public boolean validExpirationOfToken(String token){
        return getClaimsFromToken(token,Claims::getExpiration).before(new Date());
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.parse("2019-08-01").isBefore(LocalDate.now()));
    }

}
