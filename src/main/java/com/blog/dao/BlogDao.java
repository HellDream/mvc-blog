package com.blog.dao;

import com.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BlogDao {
    private final static String SELECT_ALL_SQL = " select * from t_article ";
    private final static String ADD_ARTICLE_SQL = "insert into t_article(title,passage) values(?,?)";
    private final static String SEARCH_SQL = "select * from t_article where title REGEXP ? or passage REGEXP ?";
    private final static String DETAIL_SQL = "select * from t_article where article_id = ?";
    private JdbcTemplate jdbcTemplate;

    public int addBlog(Blog blog){
        return jdbcTemplate.update(ADD_ARTICLE_SQL, blog.getTitle(),blog.getPassage());
    }

    public List<Blog> allBlog(){
        return jdbcTemplate.query(SELECT_ALL_SQL,new BeanPropertyRowMapper<Blog>(Blog.class));
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
        return blog;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
