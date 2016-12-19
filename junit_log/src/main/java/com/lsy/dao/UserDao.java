package com.lsy.dao;

import com.lsy.entity.User;
import com.lsy.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;


/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class UserDao {

    public void save(User user){
        String sql="insert into t_user (username,age,address,tel)values(?,?,?,?)";
        DbHelp.update(sql,user.getUsername(),user.getAge(),user.getAddress(),user.getTel());
    }
    public User findById(Integer id){
       /* User user=cache.get("user:"+id);//user:1*/
            String sql = "select * from t_user where id=?";
          return DbHelp.query(sql, new BeanHandler<>(User.class), id);

        }


    public List<User> findAll(){
        String sql="select * from t_user";
        return  DbHelp.query(sql,new BeanListHandler<>(User.class));

    }
    public void del(Integer id){

            String sql = "delete from t_user where id=?";
            DbHelp.update(sql, id);

    }

}
