package com.trojan.cms.rest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trojan.cms.rest.entity.User;
import com.trojan.cms.rest.mapper.UserMapper;
import com.trojan.cms.rest.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author author
 * @since 2021-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
