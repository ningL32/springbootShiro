package com.example.demo.service.impl.playIdiom;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.playIdiom.PlayIdiomDao;
import com.example.demo.pojo.playIdiom.PlayIdiom;
import com.example.demo.service.playIdiom.PlayIdiomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PlayIdiomService")
public class PlayIdiomServiceImpl extends ServiceImpl<PlayIdiomDao,PlayIdiom> implements PlayIdiomService {

    @Override
    public List<PlayIdiom> queryAll() {
        return baseMapper.queryAll();
    }
}
