package com.blog.dao;

import com.blog.domain.Blog;
import com.blog.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TagDao {
    private final static String CREATE_TAG_SQL = "insert into tag(tag_name) values (?)";
    private final static String FILTER_BY_TAG = "select * from t_article where article_id in (select article_id" +
            " from blog_to_tag where tag_id = ?)";
    private final static String SELECT_TAG_ID_SQL = "select tag_id from tag where tag_name = ?";
    private final static String SELECT_ALL_TAG = "select * from tag";
    private JdbcTemplate jdbcTemplate;

    public void createTag(String tagName){
        jdbcTemplate.update(CREATE_TAG_SQL,tagName);
    }

    public int selectTag(String tagName){
        final ArrayList<Integer> tagIds = new ArrayList<Integer>();
        jdbcTemplate.query(SELECT_TAG_ID_SQL, new Object[]{tagName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                tagIds.add(resultSet.getInt("tag_id"));
            }
        });
        return tagIds.get(0);
    }

    public List<Blog> filterByTag(int tagId){
        return jdbcTemplate.query(FILTER_BY_TAG,new Object[]{tagId},
                new BeanPropertyRowMapper<Blog>(Blog.class));
    }

    public List<Tag> selectAllTag(){
        return jdbcTemplate.query(SELECT_ALL_TAG,
                new BeanPropertyRowMapper<Tag>(Tag.class));
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
