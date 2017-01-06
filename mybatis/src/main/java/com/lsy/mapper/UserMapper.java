package com.lsy.mapper;

import com.lsy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public interface UserMapper {
    User findById(Integer id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(Integer id);
    User findByUsernameAndPassword(String username,String password);
    User findByUsernameAndPassword2(@Param("name") String username,@Param("pwd") String password);
    User findByUsernameAndPassword3(Map<String,Object> param);
    User findByParam(Map<String,Object> param);
    List<User> findByIds(List<Integer> ids);
    //批量增加
    void bacthSave(List<User> userList);
}
