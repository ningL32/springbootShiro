package com.example.demo.pojo.playIdiom;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("play_idiom")
public class PlayIdiom {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String idiom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdiom() {
        return idiom;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }
}
