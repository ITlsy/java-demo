package com.lsy.mapper;

import com.lsy.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
public interface RoleMapper {
    List<Role> findAllRole();

    void saveNewUserRole(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

    Role findById(Integer roleId);
}
