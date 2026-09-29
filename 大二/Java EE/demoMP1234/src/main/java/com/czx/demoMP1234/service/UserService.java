package com.czx.demoMP1234.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czx.demoMP1234.entity.User;

public interface UserService extends IService<User> {
    User login(String username, String password);
}