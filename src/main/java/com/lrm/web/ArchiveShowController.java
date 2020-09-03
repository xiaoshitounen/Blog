package com.lrm.web;

import com.lrm.po.Blog;
import com.lrm.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by limi on 2017/10/23.
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }

    @GetMapping("/archives/{id}")
    public String archivesOfUser(Model model, @PathVariable Long id) {
        int blogCount = 0;

        Map<String, List<Blog>> yearsBlog = blogService.archiveBlog();

        Map<String, List<Blog>> yearsBlogOfUser = new HashMap<>();
        for (String s : yearsBlog.keySet()) {
            List<Blog> blogs = new ArrayList<>();
            for (Blog blog : yearsBlog.get(s)) {
                if (blog.getUser().getId().equals(id)) {
                    blogs.add(blog);
                    blogCount++;
                }
            }

            if (blogs.size() > 0) {
                yearsBlogOfUser.put(s, blogs);
            }
        }

        model.addAttribute("archiveMap", yearsBlogOfUser);
        model.addAttribute("blogCount", blogCount);
        return "archives";
    }
}
