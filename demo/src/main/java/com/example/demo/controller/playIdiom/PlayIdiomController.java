package com.example.demo.controller.playIdiom;

import com.example.demo.pojo.playIdiom.PlayIdiom;
import com.example.demo.pojo.playIdiom.PlayPlay;
import com.example.demo.pojo.playIdiom.PlayRecord;
import com.example.demo.pojo.playIdiom.PlayUser;
import com.example.demo.service.playIdiom.PlayIdiomService;
import com.example.demo.service.playIdiom.PlayPlayService;
import com.example.demo.service.playIdiom.PlayRecordService;
import com.example.demo.service.playIdiom.PlayUserService;
import com.example.demo.utils.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.dc.pr.PRError;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("play")
@Api("打字测评游戏controller")
public class PlayIdiomController {
    @Autowired
    private PlayUserService playUserService;
    @Autowired
    private PlayIdiomService playIdiomService;
    @Autowired
    private PlayRecordService playRecordService;
    @Autowired
    private PlayPlayService playPlayService;

    @PostMapping("/pUser")
    @ApiOperation("获取用户信息")
    public BaseResult allUser(@RequestParam("id")Integer id){
        PlayUser playUser = playUserService.selectById(id);
        BaseResult br = BaseResult.ok();
        br.put("pUser",playUser);
        return  br;
    }
    @PostMapping("login")
    @ApiOperation("用户登录游戏")
    public BaseResult loginGame(@RequestParam(value = "loginName",required = true)String loginName,
                                @RequestParam(value = "pass",required = true)String pass){

        try {
            BaseResult br = BaseResult.ok("登录成功");
            HashMap<String,Object> map = new HashMap<>();
            map.put("login_name",loginName);
            map.put("pass",pass);
            List<PlayUser> list = playUserService.selectByMap(map);
            if(list.size() >0 ){
                return br;
            }
            return  BaseResult.error(500,"用户不存在，请重新输入");
        }catch (Exception e){
             return BaseResult.error("接口异常");
        }
    }

    @PostMapping("/idioms")
    @Transactional
    @ApiOperation("随机获取8条成语，从成语库")
    public BaseResult idioms(){
        try {
            BaseResult br = BaseResult.ok();
            List<PlayIdiom> list = playIdiomService.queryAll();
            StringBuilder stringBuilder = new StringBuilder();
            for (PlayIdiom  idiom: list) {
                stringBuilder = stringBuilder.append(idiom.getIdiom()+",");
            }
            br.put("idiomLis",list);
            //生成一次成语测评记录
            String idioms = stringBuilder.toString();
            PlayRecord playRecord = new PlayRecord();
            playRecord.setIdioms(idioms.substring(0,idioms.length()-1));
            playRecord.setPlayRecordId(new Date().getTime()+"");
            playRecordService.insert(playRecord);
            return br;
        }catch (Exception e){
            e.printStackTrace();
            return  BaseResult.error("接口异常");
        }
    }
    @PostMapping("beginAndNextIdiom")
    @ApiOperation("开始游戏，下一条成语接口")
    public BaseResult getNextIdiom(@RequestParam(value = "idiomId",required = true)String idiomId,
                                   @RequestParam(value = "userId",required = true)String userId){
        try {
            BaseResult br = BaseResult.ok();
            PlayUser playUser = playUserService.selectById(userId);
            if(playUser == null){
                return BaseResult.error("请先进行登录");
            }
            PlayIdiom idiom = playIdiomService.selectById(idiomId);
            if(idiom == null){
                return BaseResult.error("该条成语不存在，数据库数据异常");
            }
            br.put("idiom",idiom);
            return br;
        }catch (Exception e){
            e.printStackTrace();
            return BaseResult.error("接口异常");
        }
    }
    @PostMapping("commitIdiom")
    @ApiOperation("提交用户输入的成语")
    public BaseResult commitIdiom(@RequestParam(value = "userId",required = true)String userId,
                                  @RequestParam(value = "recordId",required = true)String recordId,
                                  @RequestParam(value = "idioms",required = false)String idioms,
                                  @RequestParam(value = "score",required = true)String score){
        try {
            PlayUser playUser = playUserService.selectById(userId);
            if(playUser == null){
                return BaseResult.error("请先进行登录");
            }
            PlayRecord playRecord = playRecordService.selectById(recordId);
            if(playRecord == null){
                return BaseResult.error("测评记录不存在");
            }
            PlayPlay playPlay = new PlayPlay();
            playPlay.setCreateTime( new Date());
            playPlay.setIdioms(idioms);
            playPlay.setPlayRecordId(recordId);
            playPlay.setUserId(Integer.parseInt(userId));
            playPlay.setScore(score);
            playPlayService.insert(playPlay);
            return BaseResult.ok(playUser.getLoginName()+"的测评记录上传成功");
        }catch (Exception e){
            e.printStackTrace();
            return BaseResult.error("接口异常");
        }
    }
}
