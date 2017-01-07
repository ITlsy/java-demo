package com.lsy.mapper;

import com.lsy.entitiy.User;
import com.lsy.util.Page;
import com.lsy.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/1/6 0006.
 */

public interface UserMapper {
    @Insert("insert into t_user(username,password,email,phone,state,avatar) " +
            "values(#{username},#{password},#{email},#{phone},#{state},#{avatar})")
    void save(User user);

    @Select("select * from t_user where username=#{username}")
    User findByUsername(String username);

    @Select("select * from t_user where email = #{email}")
    User findByEmail(String email);

    @Select("select * from t_user where id=#{id}")
    User findById(Integer id);

    @Select("select * from t_user where state != 0 order by createtime limit #{start},#{pageSize}")
    List<User> findAllUsers(Page<UserVo> page);

    @Update("update t_user set password=#{password},email=#{email},phone=#{phone},state=#{state},avatar=#{avatar} where id=#{id}")
    void update(User user);

    @Select("select count(*) from t_user where state!=0 order by id")
    Integer count();

    @Select("select tll.logintime lastLogintime,tll.ip loginIp,tu.id userid,tu.username username,tu.createtime ,tu.state userState from t_login_log tll ,t_user tu where userid = #{userid} order by logintime desc limit 0,1")
    UserVo findAllUserVo(Integer id);

}
