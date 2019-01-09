package com.example.demo.pojo.playIdiom;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("paly_play")
public class PlayPlay {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("user_id")
    private Integer userId;
    private String idioms;
    @TableField("idiom_record_id")
    private String playRecordId;
    private String score;
    @TableField("create_time")
    private Date createTime;
    @TableField("true_flag")
    private String trueFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTrueFlag() {
        return trueFlag;
    }

    public void setTrueFlag(String trueFlag) {
        this.trueFlag = trueFlag;
    }
}
