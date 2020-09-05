package com.lrm.web;

import com.lrm.po.User;
import com.lrm.service.UserService;
import com.lrm.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class EditController {
    @Autowired
    private UserService userService;

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findUserById(id));

        return "edit";
    }

    @PostMapping("/edit/username/{id}")
    public String editName(@PathVariable Long id,
                           @RequestParam String username) {
        User user = userService.findUserById(id);
        user.setUsername(username);
        user.setUpdateTime(new Date());
        userService.updateUser(id, user);

        return "redirect:/edit/"+id;
    }

    @PostMapping("/edit/email/{id}")
    public String editEmail(@PathVariable Long id,
                           @RequestParam String email) {
        User user = userService.findUserById(id);
        user.setEmail(email);
        user.setUpdateTime(new Date());
        userService.updateUser(id, user);

        return "redirect:/edit/"+id;
    }

    @PostMapping("/edit/password/{id}")
    public String editPassword(@PathVariable Long id,
                            @RequestParam String password) {
        User user = userService.findUserById(id);
        user.setPassword(MD5Utils.code(password));
        user.setUpdateTime(new Date());
        userService.updateUser(id, user);

        return "redirect:/edit/"+id;
    }
}
