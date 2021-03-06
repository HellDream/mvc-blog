package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.domain.Tag;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@RestController

public class BlogController {
    private BlogService blogService;
    private TagService tagService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView homepage(HttpServletRequest request){
        if(request.getSession().getAttribute("user_id")==null){
            return checkLoginView(request);
        }
        List<Blog> blogList = blogService.allBlog();
        request.getSession().setAttribute("blogList",blogList);
        request.getSession().setAttribute("type","home");
        return new ModelAndView("home");
    }


    private ModelAndView checkLoginView(HttpServletRequest request){
        request.getSession().setAttribute("previousPage", request.getRequestURI());
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/{articleId}")
    public ModelAndView detail(@PathVariable int articleId, HttpServletRequest request){
        if(request.getSession().getAttribute("user_id")==null){
            return checkLoginView(request);
        }
        Blog blog = blogService.detailBlog(articleId);
        request.getSession().setAttribute("blog",blog);
        return new ModelAndView("detail");
    }

    @RequestMapping(value = "/create")
    public ModelAndView createBlog(HttpServletRequest request){
        if(request.getSession().getAttribute("user_id")==null){
            return checkLoginView(request);
        }
        List<Tag> tags = tagService.selectAllTag();
        System.out.println(tags.get(0).getTagName());
        request.getSession().setAttribute("tags",tags);
        System.out.println(request.getSession().getAttribute("user_id"));
        int userId = (Integer) request.getSession().getAttribute("user_id");
        System.out.println(request.getMethod());
        if(request.getMethod().equals("POST")){
            Blog blog = new Blog();
            blog.setTitle(request.getParameter("title"));
            blog.setPassage(request.getParameter("my-editormd-markdown-doc"));
            Date date = new DateFormatter().formattedCurrentDate();
            blog.setPublishDate(date);
            blogService.addBlog(blog);
            String[] tagNames = request.getParameterValues("tag");
            int articleId = blog.getArticleId();
            System.out.println(articleId);
            if(tagNames!=null&&tagNames.length>0){
                for(String tagName:tagNames){
                    int tagId = tagService.selectTag(tagName);
                    blogService.addTag(articleId,tagId);
                }
            }
            System.out.println("userId:"+userId);
            blogService.addUserArticle(userId, articleId);
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

    @RequestMapping(value = "/my")
    public ModelAndView myBlog(HttpServletRequest request){
        if(request.getSession().getAttribute("user_id")==null){
            return checkLoginView(request);
        }
        int userId = (Integer) request.getSession().getAttribute("user_id");
        List<Blog> blogList = blogService.allBlog(userId);
        request.getSession().setAttribute("blogList",blogList);
        request.getSession().setAttribute("type","my-blog");
        return new ModelAndView("home");
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
}
