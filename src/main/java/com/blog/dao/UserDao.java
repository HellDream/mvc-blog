package com.blog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private static String FIND_MATCH_COUNT_SQL = "select count(*) from t_user where username=?";
    private static String LOGIN_SQL = "select * from t_user where username=? and password=DECODE(?,'abce')";

    public void signUpUser(String username, String password){
        String SIGN_UP_SQL = "insert into t_user(username,password) values(?,ENCODE(?,'abcd'))";
        jdbcTemplate.update(SIGN_UP_SQL,username,password);
    }

    public int findMatchUser(String username){
        return jdbcTemplate.queryForObject(FIND_MATCH_COUNT_SQL,new Object[]{username},Integer.class);
    }

    public boolean loginUser(String username, String password){
        
    }


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
