package com.trojan.cms.common.config;


import com.trojan.cms.common.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LogWebMvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(mvcInterceptor())
                // 拦截所有请求的路径
                .addPathPatterns("/**");
    }
    
    @Bean
    public LogInterceptor mvcInterceptor() {
        return new LogInterceptor();
    }
    
    
}
