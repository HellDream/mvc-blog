package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TagController {
    private TagService tagService;

    @RequestMapping(value = "/tag/{tagId}")
    public ModelAndView filterByTag(@PathVariable int tagId, HttpServletRequest request){
        List<Blog> blogList = tagService.filterByTag(tagId);
        request.getSession().setAttribute("blogList",blogList);
        request.getSession().setAttribute("type","tag");
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/tag/create")
    public ModelAndView createTag(HttpServletRequest request){
        String tagName = request.getParameter("tagName");
        tagService.createTag(tagName);
        return new ModelAndView("redirect:/");
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
}
