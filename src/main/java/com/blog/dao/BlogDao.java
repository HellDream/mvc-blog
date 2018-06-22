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
    private final static String SELECT_ALL_SQL_USER = " select * from t_article where article_id in (select article_id from t_user_article where user_id=?)";
    private final static String ADD_ARTICLE_SQL = "insert into t_article(title,passage,publish_date) values(?,?,?)";
    private final static String SEARCH_SQL = "select * from t_article where title REGEXP ? or passage REGEXP ?";
    private final static String DETAIL_SQL = "select * from t_article where article_id = ?";
    private final static String ARTICLE_TAG_SQL = "select * from tag where tag_id in(select tag_id from blog_to_tag where article_id = ?)";
    private final static String ADD_TAG_SQL = "insert into blog_to_tag(article_id,tag_id) values(?,?)";
    private final static String TOTAL_ARTICLE_SQL = "select count(*) from t_article";
    private final static String USER_ARTICLE_SQL = "insert into t_user_article(user_id,article_id) values(?,?)";
    private final static String SELECT_ALL_SQL = "select * from t_article";
    private JdbcTemplate jdbcTemplate;

    public int addBlog(Blog blog){
        return jdbcTemplate.update(ADD_ARTICLE_SQL, blog.getTitle(),blog.getPassage(),blog.getPublishDate());
    }

    public void addUserArticle(int userId, int articleId){
        jdbcTemplate.update(USER_ARTICLE_SQL,userId,articleId);
    }

    public int getLatestId(){
        return jdbcTemplate.queryForObject(TOTAL_ARTICLE_SQL,Integer.class);
    }

    public void addTag(int articleId, int tagId){
        jdbcTemplate.update(ADD_TAG_SQL,articleId,tagId);
    }

    public List<Blog> allBlog(int userId){
        List<Blog> blogList = jdbcTemplate.query(SELECT_ALL_SQL_USER,new Object[]{userId},
                new BeanPropertyRowMapper<Blog>(Blog.class));
        return setBlogTags(blogList);

    }
    public List<Blog> allBlog(){
        List<Blog> blogList = jdbcTemplate.query(SELECT_ALL_SQL,
                new BeanPropertyRowMapper<Blog>(Blog.class));
        return setBlogTags(blogList);
    }

    private List<Blog> setBlogTags(List<Blog> blogList){
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
                blog.setPublishDate(resultSet.getDate("publish_date"));
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
