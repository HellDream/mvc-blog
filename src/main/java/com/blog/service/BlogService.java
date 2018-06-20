package com.blog.service;

import com.blog.dao.BlogDao;
import com.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Transactional
    public void addBlog(Blog blog){
        int a = blogDao.addBlog(blog);
        System.out.println(a);
    }

    @Transactional
    public List<Blog> allBlog(int userId){
        return blogDao.allBlog(userId);
    }

    @Transactional
    public List<Blog> searchBlog(String keyword){
        return blogDao.searchBlog(keyword);
    }

    @Transactional
    public void addUserArticle(int userId, int articleId) {
        blogDao.addUserArticle(userId,articleId);
    }
    @Transactional
    public Blog detailBlog(int articleId){
        return blogDao.detailBlog(articleId);
    }
    @Transactional
    public int getLatestId(){
        return blogDao.getLatestId();
    }
    @Transactional
    public void addTag(int articleId, int tagId){
        blogDao.addTag(articleId,tagId);
    }
    @Autowired
    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }
}
