package com.lrm.web;

import com.lrm.po.User;
import com.lrm.service.UserService;
import com.lrm.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String registerPage() {
        return "register";
    }

    @PostMapping("/do")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String nickname,
                           @RequestParam String password,
                           HttpSession session) {
        System.out.println("username:"+username);
        System.out.println("email:"+email);
        System.out.println("nickname:"+nickname);
        System.out.println("password:"+password);

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(MD5Utils.code(password));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setType(1);
        /**
         *  https://api.dujin.org/bing/1366.php
         *  https://api.dujin.org/bing/1920.php
         *  https://api.ixiaowai.cn/api/api.php
         *  https://api.ixiaowai.cn/gqapi/gqapi.php
         *  https://api.ixiaowai.cn/mcapi/mcapi.php
         *  https://acg.sx/images
         */
        user.setAvatar("https://acg.sx/images");

        user = userService.registerUser(user);

        user.setPassword(null);
        session.setAttribute("user",user);

        return "redirect:/";
    }
}
