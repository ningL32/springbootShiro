package com.example.demo.service.impl.playIdiom;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.playIdiom.PlayUserDao;
import com.example.demo.pojo.playIdiom.PlayUser;
import com.example.demo.service.playIdiom.PlayUserService;
import org.springframework.stereotype.Service;

@Service("playUserService")
public class PlayUserServiceImpl extends ServiceImpl<PlayUserDao,PlayUser> implements PlayUserService {
}
