package com.example.demo.service.playIdiom;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.pojo.playIdiom.PlayIdiom;

import java.util.List;

public interface PlayIdiomService extends IService<PlayIdiom> {

    List<PlayIdiom> queryAll();
}
