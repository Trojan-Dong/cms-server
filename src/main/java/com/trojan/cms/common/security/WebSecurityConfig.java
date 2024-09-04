package com.trojan.cms.common.security;

import com.trojan.cms.common.security.filter.JwtAuthenticationFilter;
import com.trojan.cms.common.security.userdetails.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Spring Security配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Resource
    private MyUserDetailsService myUserDetailsService;
    
    @Resource
    public PasswordEncoder passwordEncoder;
    
    /**
     * 配置认证管理器，使用DaoAuthenticationProvider进行认证
     * @return 认证管理器实例
     * @throws Exception 如果配置过程中出现异常则抛出
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(Collections.singletonList(provider));
    }
    
    /**
     * 配置HTTP安全策略
     * @param httpSecurity HTTP安全构建器
     * @throws Exception 如果配置过程中出现异常则抛出
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 禁用CSRF保护
        httpSecurity.csrf().disable();
        // 设置会话管理策略为无状态
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 配置请求授权规则
        httpSecurity.authorizeRequests()
                // 对特定路径下的请求允许所有访问
                .antMatchers(new String[] {"/instances/**", "/actuator/**"}).permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/**/doc*/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 所有其他请求都需要认证
                //.anyRequest().authenticated();
                // 注释掉默认的认证，允许所有请求
                .anyRequest().permitAll();
        // 在UsernamePasswordAuthenticationFilter之前添加JWT认证过滤器
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 配置HTTP头部信息，禁用缓存控制（注：此处原文有误，应为httpSecurity.headers().cacheControl();）
        httpSecurity.headers().cacheControl();
    }
    
    /**
     * 配置Web安全策略，忽略特定路径的拦截
     * @param web Web安全构建器
     * @throws Exception 如果配置过程中出现异常则抛出
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 对特定路径下的请求不进行拦截
        web.ignoring().antMatchers("/user/auth/**", "/common/**", "/**/common/**", "/cate/get/**", "/article/get/**");
    }
}
