package com.trojan.cms.common.security.authentication;

import com.trojan.cms.common.exception.BizException;
import com.trojan.cms.common.result.CodeMsg;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义用户主体类，用于封装认证信息 继承自UsernamePasswordAuthenticationToken，以支持Spring Security的认证机制
 */
public class UserPrincipal extends UsernamePasswordAuthenticationToken {
    
    /**
     * 构造函数，用于创建仅包含主体和凭证的认证令牌
     * @param principal   认证的主体，通常是用户信息
     * @param credentials 认证凭证，通常是密码
     */
    public UserPrincipal(Object principal, Object credentials) {
        super(principal, credentials);
    }
    
    /**
     * 构造函数，用于创建包含主体、凭证和权限的认证令牌
     * @param principal   认证的主体，通常是用户信息
     * @param credentials 认证凭证，通常是密码
     * @param authorities 用户的角色或权限集合
     */
    public UserPrincipal(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
    
    /**
     * 获取认证详情信息
     * @return 包含认证详情的Map对象
     */
    public Map<String, Object> detailHandler() {
        return (Map<String, Object>) getDetails();
    }
    
    /**
     * 获取用户ID
     * @return 用户ID
     */
    public Long getId() {
        return Long.parseLong(getValue("id").toString());
    }
    
    /**
     * 获取用户状态
     * @return 用户状态
     */
    public Integer getStatus() {
        return Integer.parseInt(getValue("status").toString());
    }
    
    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUsername() {
        return (String) getValue("username");
    }
    
    /**
     * 获取用户电话号码
     * @return 电话号码
     */
    public String getPhone() {
        return (String) getValue("phone");
    }
    
    /**
     * 获取用户邮箱
     * @return 邮箱地址
     */
    public String getEmail() {
        return (String) getValue("email");
    }
    
    /**
     * 获取用户序列号
     * @return 用户序列号
     */
    public String getUserSn() {
        return (String) getValue("userSn");
    }
    
    /**
     * 获取用户角色
     * @return 用户角色
     */
    public Integer getRole() {
        return Integer.parseInt(getValue("role").toString());
    }
    
    /**
     * 获取用户站点ID
     * @return 站点ID
     */
    public Long getSiteId() {
        return Long.parseLong(getValue("siteId").toString());
    }
    
    /**
     * 根据键获取认证详情中的值 如果值不存在，则抛出业务异常
     * @param key 键
     * @return 对应键的值
     */
    public Object getValue(String key) {
        Map<String, Object> details = (Map<String, Object>) getDetails();
        Object value = details.get(key);
        if (value == null) {
            throw new BizException(CodeMsg.BIZ_EXCEPTION_DETAILS_NULL_VALUE);
        } else {
            return value;
        }
    }
}
