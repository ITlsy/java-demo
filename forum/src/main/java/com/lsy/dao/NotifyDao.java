package com.lsy.dao;

import com.lsy.entitiy.Notify;
import com.lsy.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class NotifyDao {

    public void save(Notify notify) {
        String sql="insert into t_notify(userid,content,state) values(?,?,?)";
        DbHelp.update(sql,notify.getUserid(),notify.getContent(),notify.getState());
    }


    public List<Notify> findByUserId(Integer id) {
        String sql="select * from t_notify where userid=? order by readtime,createtime";
        return DbHelp.query(sql,new BeanListHandler<>(Notify.class),id);
    }

    public Notify findById(String id) {
        String sql="select * from t_notify where id=?";
        return DbHelp.query(sql,new BeanHandler<>(Notify.class),id);
    }

    public void update(Notify notify) {
        String sql = "update t_notify set state = ?,readtime = ? where id = ?";
        DbHelp.update(sql,notify.getState(),notify.getReadtime(),notify.getId());
    }
}
