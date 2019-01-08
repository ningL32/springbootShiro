package com.example.demo.service.impl.playIdiom;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.playIdiom.PlayRecordDao;
import com.example.demo.pojo.playIdiom.PlayRecord;
import com.example.demo.service.playIdiom.PlayRecordService;
import org.springframework.stereotype.Service;

@Service("playRecordService")
public class PlayRecordServiceImpl extends ServiceImpl<PlayRecordDao,PlayRecord> implements PlayRecordService {
}
