package cn.edu.guet.popular_blog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pangjian
 * @ClassName JwtTokenUtil
 * @Description Jwt工具类
 * @date 2021/6/2 19:37
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * @Description: 根据已成功登录的用户信息生成token
     * @Param userDetails:spring security框架中的接口
     * @return java.lang.String
     * @date 2021/6/2 19:46
    */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claim = new HashMap<>();
        claim.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claim.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claim);
    }

    /**
     * @Description: 验证token，一要认证从 前端传过来的token的里面的用户名 和 账号密码查询出来的用户名 是否一致，二要认证时间是否过期
     * @Param token: 待认证的token
     * @Param userDetails: spring security框架中的类
     * @return boolean
     * @date 2021/6/2 20:04
    */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    
    /**
     * @Description: token是否能被刷新，没过期是不可以被刷新，过期了可以被刷新
     * @Param token: 
     * @return boolean
     * @date 2021/6/2 20:17
    */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * @Description: 刷新token
     * @Param token:
     * @return java.lang.String
     * @date 2021/6/2 20:21
    */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }


    /**
     * @Description: 判断token是否失效
     * @Param token:
     * @return boolean
     * @date 2021/6/2 20:05
    */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        // 过期时间要在当前时间之后
        return expireDate.before(new Date());
    }

    /**
     * @Description: 从token中获取过期时间
     * @Param token:
     * @return java.util.Date
     * @date 2021/6/2 20:09
    */
    private Date getExpiredDateFromToken(String token) {

        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();

    }

    /**
     * @Description: 从token中获取登录用户名
     * @Param token: token
     * @return java.lang.String
     * @date 2021/6/2 19:53
    */
    public String getUserNameFromToken(String token){
        String username;
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * @Description:
     * @Param token: 从token中获取荷载,需要密钥
     * @return io.jsonwebtoken.Claims
     * @date 2021/6/2 19:57
    */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
               claims = Jwts.parser()
                            .setSigningKey(secret)
                            .parseClaimsJws(token)
                            .getBody();
        } catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * @Description: 根据荷载生成token
     * @Param claim: 荷载
     * @return java.lang.String
     * @date 2021/6/2 19:51
    */
    private String generateToken(Map<String,Object> claim){
        return Jwts.builder()
                .setClaims(claim)
                // 设置过期时间：当前时间加失效时间
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * @Description: 生成失效时间
     * @return java.util.Date
     * @date 2021/6/2 19:49
    */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

}
