package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.pojo.User;

public interface UserService extends IService<User> {
    User findByUsername(String username);
}
