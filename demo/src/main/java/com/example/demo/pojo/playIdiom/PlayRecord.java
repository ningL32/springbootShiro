package com.example.demo.pojo.playIdiom;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("play_record")
public class PlayRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String idioms;
    @TableField("play_record_id")
    private String playRecordId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdioms() {
        return idioms;
    }

    public void setIdioms(String idioms) {
        this.idioms = idioms;
    }

    public String getPlayRecordId() {
        return playRecordId;
    }

    public void setPlayRecordId(String playRecordId) {
        this.playRecordId = playRecordId;
    }
}
