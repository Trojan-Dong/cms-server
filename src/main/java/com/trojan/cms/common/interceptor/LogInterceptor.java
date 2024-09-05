package com.trojan.cms.common.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.trojan.cms.common.util.MDCTraceUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: DGJ
 * @Date: 2024/9/5
 * @Description: 自定义日志拦截器
 */
public class LogInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 客户端可以传入链路ID，需要唯一性
        String traceId = request.getHeader(MDCTraceUtil.TRACE_ID_HEADER);
        if (!StringUtils.isEmpty(traceId)) {
            MDCTraceUtil.putTrace(traceId);
        } else {
            MDCTraceUtil.addTrace();
        }
        return true;
    }
    
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        MDCTraceUtil.removeTrace();
    }
}
