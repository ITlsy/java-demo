package com.lsy.dao;

import com.lsy.entitiy.User;
import com.lsy.util.DbHelp;
import com.lsy.util.Page;
import com.lsy.vo.UserVo;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class UserDao {
  /*public void save(User user) {
        String sql="insert into t_user(username,password,email,phone,state,avatar)values(?,?,?,?,?,?)";
        DbHelp.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone(),user.getState(),user.getAvatar());
    }*/

    /*public User findByUsername(String username) {
        String sql="select * from t_user where username  = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),username);
    }*/
    /*public User findByEmail(String email) {
        String sql="select * from t_user where email = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),email);
    }*/
/*
    public void update(User user) {
        String sql="update t_user set password=?,email=?,phone=?,state=?,avatar=? where id=?";
        DbHelp.update(sql,user.getPassword(),user.getEmail(),user.getPhone(),user.getState(),user.getAvatar(),user.getId());

    }*/

    /*public User findById(Integer id) {
        String sql="select * from t_user where id=?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),id);
    }*/

    //分页查询状态不为0的所有用户
   /* public List<User> findAllUsers(Page<UserVo> page) {
        String sql = "select * from t_user where state != 0 order by createtime limit ?,?";
        return DbHelp.query(sql,new BeanListHandler<>(User.class),page.getStart(),page.getPageSize());

    }*/

    //获取所有状态不为0用户的数量
    /*public Integer count() {
        String sql="select count(*) from t_user where state!=0 order by id";
        return DbHelp.query(sql,new ScalarHandler<Long>()).intValue();
    }*/

   /* public UserVo findAllUserVo(Integer id) {
        String sql = "select tll.logintime lastLogintime,tll.ip loginIp,tu.id userid,tu.username username,tu.createtime ,tu.state userState from t_login_log tll ,t_user tu where userid = ? order by logintime desc limit 0,1";
        return DbHelp.query(sql,new BeanHandler<>(UserVo.class),id);
    }*/
}
