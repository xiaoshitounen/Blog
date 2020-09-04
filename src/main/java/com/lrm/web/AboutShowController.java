package com.lrm.web;

import com.lrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by limi on 2017/10/24.
 */
@Controller
public class AboutShowController {

    @Autowired
    private UserService userService;

    @GetMapping("/about/{id}")
    public String about(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "about";
    }
}
