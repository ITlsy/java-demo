package com.lsy.mapper;

import com.lsy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    List<User> findAll();

    void save(User user);

    User findById(Integer id);

    void delete(Integer id);

    void update(User user);


    List<User> findByPage(@Param("start") int start,@Param("pageSize") int pageSize);

    Long count();

    Long countByParam(@Param("queryName") String queryName,@Param("queryRole") String queryRole);

    List<User> findByPageAndParam(@Param("start") int start,
                                  @Param("pageSize") int pageSize,
                                  @Param("queryName") String queryName,
                                  @Param("queryRole") String queryRole);

    User findByUsername(String username);
}
