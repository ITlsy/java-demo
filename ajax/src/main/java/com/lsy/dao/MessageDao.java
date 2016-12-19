package com.lsy.dao;

import com.lsy.entity.Message;
import com.lsy.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class MessageDao {
    public List<Message> findAll() {
        String sql="select * from t_message order by id desc";
        return DbHelp.query(sql,new BeanListHandler<>(Message.class));
    }

    public List<Message> findByMaxId(int maxId) {
        String sql="select * from t_message where id > ? order by id desc";
        return DbHelp.query(sql, new BeanListHandler<>(Message.class),maxId);

    }


}
