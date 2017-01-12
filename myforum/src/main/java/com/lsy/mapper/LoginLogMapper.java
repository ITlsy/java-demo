package com.lsy.mapper;

import com.lsy.entitiy.LoginLog;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@CacheNamespace
public interface LoginLogMapper {
    @Insert("insert into t_login_log(ip,userid) values(#{ip},#{userid})")
    void save(LoginLog log);
}
