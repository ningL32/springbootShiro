package com.example.demo.service.impl.playIdiom;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.playIdiom.PlayPlayDao;
import com.example.demo.pojo.playIdiom.PlayPlay;
import com.example.demo.service.playIdiom.PlayPlayService;
import org.springframework.stereotype.Service;

@Service("playPlayService")
public class PlayPlayServiceImpl extends ServiceImpl<PlayPlayDao,PlayPlay> implements PlayPlayService {
}
