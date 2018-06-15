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
public class BlogDao {
    private final static String SELECT_ALL_SQL = " select * from t_article ";
    private final static String ADD_ARTICLE_SQL = "insert into t_article(title,passage) values(?,?)";
    private final static String SEARCH_SQL = "select * from t_article where title REGEXP ? or passage REGEXP ?";
    private final static String DETAIL_SQL = "select * from t_article where article_id = ?";
    private final static String ARTICLE_TAG_SQL = "select * from tag where tag_id in(select tag_id from blog_to_tag where article_id = ?)";
    private JdbcTemplate jdbcTemplate;

    public int addBlog(Blog blog){
        return jdbcTemplate.update(ADD_ARTICLE_SQL, blog.getTitle(),blog.getPassage());
    }

    public List<Blog> allBlog(){
        List<Blog> blogList = jdbcTemplate.query(SELECT_ALL_SQL,
                new BeanPropertyRowMapper<Blog>(Blog.class));
        for(Blog blog:blogList){
            final ArrayList<Tag> tags = new ArrayList<Tag>();
            jdbcTemplate.query(ARTICLE_TAG_SQL, new Object[]{blog.getArticleId()}, new RowCallbackHandler() {
                public void processRow(ResultSet resultSet) throws SQLException {
                    Tag tag = new Tag();
                    tag.setTagId(resultSet.getInt("tag_id"));
                    tag.setTagName(resultSet.getString("tag_name"));
                    tags.add(tag);
                }
            });
            blog.setTags(tags);
        }
        return blogList;
    }

    public List<Blog> searchBlog(String keword){
        return jdbcTemplate.query(SEARCH_SQL,new Object[]{keword, keword},new BeanPropertyRowMapper<Blog>(Blog.class));
    }

    public Blog detailBlog(int articleId){
        final Blog blog = new Blog();
        jdbcTemplate.query(DETAIL_SQL, new Object[]{articleId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                blog.setArticleId(resultSet.getInt("article_id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setPassage(resultSet.getString("passage"));
            }
        });
        final ArrayList<Tag> tags = new ArrayList<Tag>();
        jdbcTemplate.query(ARTICLE_TAG_SQL, new Object[]{articleId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                Tag tag = new Tag();
                tag.setTagId(resultSet.getInt("tag_id"));
                tag.setTagName(resultSet.getString("tag_name"));
                tags.add(tag);
            }
        });
        blog.setTags(tags);
        return blog;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
