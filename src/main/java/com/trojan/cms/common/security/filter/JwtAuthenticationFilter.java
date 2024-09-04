package com.trojan.cms.common.security.filter;

import com.trojan.cms.common.security.authentication.UserPrincipal;
import com.trojan.cms.common.security.jwt.JwtToken;
import com.trojan.cms.common.sys.sysConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * JWT认证过滤器 该类继承自OncePerRequestFilter，确保每个请求只被处理一次
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Resource
    private JwtToken jwtToken; // 注入JWT令牌处理类
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 设置响应头，允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", "*");
        
        // 从请求头中获取JWT令牌
        String token = request.getHeader(sysConst.TOKEN);
        if (token != null) {
            // 从令牌中获取用户名
            String username = jwtToken.getUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 验证JWT令牌的有效性
                if (jwtToken.validateToken(token)) {
                    // 获取用户的角色权限
                    List<GrantedAuthority> authorities = jwtToken.getAuthorities(token);
                    // 创建认证对象
                    UsernamePasswordAuthenticationToken authentication = new UserPrincipal(username, null, authorities);
                    // 设置令牌中的额外信息
                    authentication.setDetails(jwtToken.getDetails(token));
                    // 将认证对象设置到Security上下文中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        // 继续请求链
        filterChain.doFilter(request, response);
    }
}
