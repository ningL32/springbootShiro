package com.example.demo.controller.playIdiom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("vplay")
public class PlayViewController {

    @GetMapping("hello")
    public ModelAndView hello(ModelAndView mv){
        mv.setViewName("/play/login.html");
        return mv;
    }

}
