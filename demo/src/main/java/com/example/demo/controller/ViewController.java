package com.example.demo.controller;

import com.example.demo.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ViewController {
    @RequestMapping("/login")
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName("/pages/login.html");
        return mv;
    }
    @RequestMapping("/hell")
    @RequiresPermissions("hello")
    public ModelAndView hello(ModelAndView mv) {
        mv.setViewName("/pages/index.html");
        return mv;
    }
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/pages/index.html");
        return mv;
    }
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();//取出当前验证主体
        if (subject != null) {
            subject.logout();//不为空，执行一次logout的操作，将session全部清空
        }
        return "login";
    }

    @RequestMapping("unauthorized")
    public String unauthorized() {
        return "unAuthorize";
    }

    @RequestMapping("/admin")
    @ResponseBody//注解之后只是返回json数据,不返回界面
    public String admin() {
        return "admin success";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit() {
        return "edit success";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello success";
    }


    /*
     * 整个form表单的验证流程：
     *
     * 将登陆的用户/密码传入UsernamePasswordToken，当调用subject.login(token)开始，调用Relam的doGetAuthenticationInfo方法，开始密码验证
     * 此时这个时候执行我们自己编写的CredentialMatcher（密码匹配器），执行doCredentialsMatch方法，具体的密码比较实现在这实现
     *
     * */
    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);


        Subject subject = SecurityUtils.getSubject();
        //import org.apache.shiro.session.Session;
        Session session=subject.getSession();
        //设置session 过期时间 为 180000ms == 3 分钟
        session.setTimeout(180000);
        try {
            System.out.println("获取到信息，开始验证！！");
            subject.login(token);//登陆成功的话，放到session中
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "redirect:index";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:login";
        }
    }
}
