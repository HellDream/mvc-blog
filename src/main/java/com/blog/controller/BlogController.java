package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController

public class BlogController {
    private BlogService blogService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView homepage(HttpServletRequest request){
        List<Blog> blogList = blogService.allBlog();
        request.getSession().setAttribute("blogList",blogList);
        request.getSession().setAttribute("type","home");
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/{articleId}")
    public ModelAndView detail(@PathVariable int articleId, HttpServletRequest request){
        Blog blog = blogService.detailBlog(articleId);
        request.getSession().setAttribute("blog",blog);
        return new ModelAndView("detail");
    }

    @RequestMapping(value = "/create")
    public ModelAndView createBlog(HttpServletRequest request){
        System.out.println(request.getMethod());
        if(request.getMethod().equals("POST")){
            Blog blog = new Blog();
            blog.setTitle(request.getParameter("title"));
            blog.setPassage(request.getParameter("passage"));
            blogService.addBlog(blog);
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("create");
    }

    @RequestMapping(value = "/search", params = {"keyword"})
    public ModelAndView searchBlog(HttpServletRequest request){
        System.out.println(request.getParameter("keyword"));
        String keyword = request.getParameter("keyword");
        List<Blog> blogList = blogService.searchBlog(keyword);
        request.getSession().setAttribute("blogList",blogList);
        request.getSession().setAttribute("type","search");
        return new ModelAndView("home");
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
}
