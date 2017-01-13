package com.lsy.service.impl;

import com.lsy.mapper.RoleMapper;
import com.lsy.mapper.UserMapper;
import com.lsy.pojo.Role;
import com.lsy.pojo.User;
import com.lsy.service.UserService;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Value("${password.salt}")
    private String salt;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void save(User user) {
        logger.debug("SALT"+salt);
      user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
      userMapper.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //删除用户的角色
        roleMapper.delRoleByUserId(id);
        //删除用户
        userMapper.delete(id);
    }

    @Override
    public void editUser(User user, Integer[] roleIds) {
        //删除原有角色
        roleMapper.delRoleByUserId(user.getId());
        //添加新角色
        addUserRole(user,roleIds);
        //更新用户
        userMapper.update(user);
    }

//公用方法
    private void addUserRole(User user, Integer[] roleIds) {
        if (roleIds!=null){
            for (Integer roleId:roleIds){
                Role role=roleMapper.findById(roleId);
                if (role!=null){
                    //创建关系表记录
                    roleMapper.saveNewUserRole(user.getId(),roleId);
                }
            }
        }
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }

    @Override
    @Transactional
    public void save(User user, Integer[] roleIds) {
        //1.保存用户
        userMapper.save(user);
        //2.保存用户和角色的关系
        addUserRole(user,roleIds);

    }
}
