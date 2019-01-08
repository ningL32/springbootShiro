package com.example.demo.controller.playIdiom;

import com.example.demo.pojo.playIdiom.PlayUser;
import com.example.demo.service.playIdiom.PlayUserService;
import com.example.demo.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("play")
public class PlayIdiomController {
    @Autowired
    private PlayUserService playUserService;


    @PostMapping("/pUser")
    @ResponseBody
    public BaseResult allUser(@RequestParam("id")Integer id){
        PlayUser playUser = playUserService.selectById(id);
        BaseResult br = BaseResult.ok();
        br.put("pUser",playUser);
        return  br;
    }
}
