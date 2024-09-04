package com.trojan.cms.common.security.jwt;

import com.trojan.cms.common.security.userdetails.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JwtToken类用于处理JWT(token)的生成和验证
 */
@Component
public class JwtToken {
    
    // 从application.properties文件中注入密钥
    @Value("${jwt.secret}")
    private String secret;
    
    // 从application.properties文件中注入过期时间
    @Value("${jwt.expiration}")
    private Long expiration;
    
    /**
     * 从token中获取创建时间
     *
     * @param token 当前令牌
     * @return 创建时间，如果解析失败则返回null
     */
    private Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }
    
    /**
     * 从token中获取过期时间
     *
     * @param token 当前令牌
     * @return 过期时间，如果解析失败则返回null
     */
    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
    
    /**
     * 从token中获取权限列表
     *
     * @param token 当前令牌
     * @return 权限列表，如果解析失败则返回null
     */
    public List<GrantedAuthority> getAuthorities(String token) {
        List<GrantedAuthority> authorities;
        try {
            Claims claims = getClaimsFromToken(token);
            String strAuthority = (String) claims.get("scope");
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(strAuthority);
        } catch (Exception e) {
            authorities = null;
        }
        return authorities;
    }
    
    /**
     * 从token中获取Claims信息
     *
     * @param token 当前令牌
     * @return Claims信息，如果解析失败则返回null
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        
        return claims;
    }
    
    /**
     * 生成token的过期时间
     *
     * @return 过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
    
    /**
     * 判断token是否已经过期
     *
     * @param token 当前令牌
     * @return 如果token已经过期返回true，否则返回false
     */
    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        if (expirationDate == null) return false;
        return expirationDate.before(new Date());
    }
    
    /**
     * 从token中获取用户名
     *
     * @param token 当前令牌
     * @return 用户名，如果解析失败则返回null
     */
    public String getUsername(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    /**
     * 从token中获取用户详细信息
     *
     * @param token 当前令牌
     * @return 用户详细信息，如果解析失败则返回null
     */
    public Map<String, Object> getDetails(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return (Map<String, Object>) claims.get("details");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 根据用户详情生成token
     *
     * @param userDetails 用户详情
     * @return 生成的token
     */
    public String generateToken(MyUserDetails userDetails) {
        
        StringBuilder strAuthority = new StringBuilder();
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
        for (GrantedAuthority grantedAuthority : authorities) {
            strAuthority.append(grantedAuthority.getAuthority()).append(",");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("scope", strAuthority.toString());
        claims.put("details", userDetails.getDetails());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    /**
     * 验证token是否有效
     *
     * @param token 当前令牌
     * @return 如果token未过期返回true，否则返回false
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
