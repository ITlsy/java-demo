package com.lsy.dao.impl;

import com.lsy.dao.UserDao;
import com.lsy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11 0011.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void save(User user) {
        String sql="insert into t_user (username,password) values(?,?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public void update(User user) {
        String sql="update t_user set username=?,password=? where id=?";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getId());
    }

    @Override
    public User findById(Integer id) {
        String sql="select * from t_user where id=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user=new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                return user;
            }
        },id);

    }

    @Override
    public List<User> findAll() {
        String sql="select * from t_user";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
        /*return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user=new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                return user;
            }
        });*/

    }

    @Override
    public void delete(Integer id) {
    String sql="delete from t_user where id=?";
    jdbcTemplate.update(sql,id);
    }

    @Override
    public User findByUsername(String username) {
        String sql="select * from t_user where username=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username);

    }

    @Override
    public Long count() {
      String sql="select count(*) from t_user";
      return jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<Long>());
    }
}
