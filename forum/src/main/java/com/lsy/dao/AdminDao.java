package com.lsy.dao;

import com.lsy.entitiy.Admin;
import com.lsy.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class AdminDao {
    public Admin findAdminByName(String adminName) {
        String sql="select * from t_admin where adminName=?";
        return DbHelp.query(sql,new BeanHandler<>(Admin.class),adminName);

    }
}
