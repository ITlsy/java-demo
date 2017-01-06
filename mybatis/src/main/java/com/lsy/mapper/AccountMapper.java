package com.lsy.mapper;

import com.lsy.pojo.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
public interface AccountMapper {
    @Insert("insert into t_account (name,address) values(#{name},#{address})")
    @Options(useGeneratedKeys = true,keyProperty = "id",flushCache =Options.FlushCachePolicy.FALSE)
    void save(Account account);

    @Select("select * from t_account where id=#{id}")
    @Options(useCache = false)
    Account findById(Integer id);

    @Select("select * from t_account")
    List<Account> findAll();

    @Update("update t_account set name=#{name},address=#{address} where id=#{id}")
    void update(Account account);

    @Delete("delete from t_account where id=#{id}")
    void delete(Integer id);

    List<Account> findByParam(Map<String,Object> param);

    @Select("select * from t_account limit #{start},#{size}")
    List<Account> findByPage(@Param("start") int start,@Param("size") int size);

}
