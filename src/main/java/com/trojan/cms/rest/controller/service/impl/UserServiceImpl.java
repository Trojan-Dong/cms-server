package com.trojan.cms.rest.controller.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trojan.cms.rest.controller.entity.User;
import com.trojan.cms.rest.controller.mapper.UserMapper;
import com.trojan.cms.rest.controller.service.UserService;
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
