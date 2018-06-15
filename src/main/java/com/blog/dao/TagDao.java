package com.blog.dao;

import com.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDao {
    private final static String CREATE_TAG_SQL = "insert into tag(tag_name) values (?)";
    private final static String FILTER_BY_TAG = "select * from t_article where article_id in (select article_id" +
            " from blog_to_tag where tag_id = ?)";
    private JdbcTemplate jdbcTemplate;

    public void createTag(String tagName){
        jdbcTemplate.update(CREATE_TAG_SQL,tagName);
    }

    public List<Blog> filterByTag(int tagId){
        return jdbcTemplate.query(FILTER_BY_TAG,new Object[]{tagId},
                new BeanPropertyRowMapper<Blog>(Blog.class));
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
