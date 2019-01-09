package com.example.demo.dao.playIdiom;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.pojo.playIdiom.PlayIdiom;

import java.util.List;

public interface PlayIdiomDao extends BaseMapper<PlayIdiom>{

    List<PlayIdiom> queryAll();
}
