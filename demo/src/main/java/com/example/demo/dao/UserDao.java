package com.example.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.pojo.User;

public interface UserDao extends BaseMapper<User> {

    User findByUsername(String username);
}
