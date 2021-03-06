package com.blog.service;

import com.blog.dao.TagDao;
import com.blog.domain.Blog;
import com.blog.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {
    private TagDao tagDao;

    @Transactional
    public void createTag(String tagName){
        tagDao.createTag(tagName);
    }

    @Transactional
    public List<Blog> filterByTag(int tagId){
        return tagDao.filterByTag(tagId);
    }

    @Transactional
    public List<Tag> selectAllTag(){
        return tagDao.selectAllTag();
    }
    @Transactional
    public int selectTag(String tagName){
        return tagDao.selectTag(tagName);
    }
    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }
}
