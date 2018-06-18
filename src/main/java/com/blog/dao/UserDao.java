package com.blog.dao;

import com.blog.domain.User;
import com.blog.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private static String FIND_MATCH_COUNT_SQL = "select count(*) from t_user where username=?";
    private static String LOGIN_SQL = "select * from t_user where username=? and password=DECODE(?,'abce')";
    private static String CHECK_PASSWORD_SQL = "select password from t_user where username=?";
    private static String UPDATE_LOGIN_DATE_SQL = "update t_user set last_visit=? where user_id = ?";
    public void signUpUser(String username, String password){
        String SIGN_UP_SQL = "insert into t_user(username,password) values(?,ENCODE(?,'abcd'))";
        jdbcTemplate.update(SIGN_UP_SQL,username,password);
    }

    public int findMatchUser(String username){
        return jdbcTemplate.queryForObject(FIND_MATCH_COUNT_SQL,new Object[]{username},Integer.class);
    }

    public User loginUser(String username, String password){
        final User user = new User();
        jdbcTemplate.query(LOGIN_SQL, new Object[]{username, password}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        });
        user.setLastVisit(new DateFormatter().formattedCurrentDate());
        jdbcTemplate.update(UPDATE_LOGIN_DATE_SQL, user.getLastVisit(),user.getUserId());
        return user;
    }

    public boolean checkPassword(String username, String password){
        return password.equals(jdbcTemplate.queryForObject(CHECK_PASSWORD_SQL, new Object[]{username},String.class));
    }


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
