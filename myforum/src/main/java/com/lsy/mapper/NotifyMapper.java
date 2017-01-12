package com.lsy.mapper;

import com.lsy.entitiy.Notify;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@CacheNamespace
public interface NotifyMapper {

    @Insert("insert into t_notify(userid,content,state) values(#{userid},#{content},#{state})")
    void save(Notify notify);

    @Select("select * from t_notify where userid=#{userid} order by readtime,createtime")
    List<Notify> findByUserId(Integer id);

    @Select("select * from t_notify where id=#{id}")
    Notify findById(String id);

    @Update("update t_notify set state = #{state},readtime = #{readtime} where id = #{id}")
    void update(Notify notify);
}
