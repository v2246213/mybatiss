package com.ctmp01.web.service.Impl;

import com.ctmp01.web.entity.User;
import com.ctmp01.web.mapper.UserMapper;
import com.ctmp01.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/26 0026.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }
}
