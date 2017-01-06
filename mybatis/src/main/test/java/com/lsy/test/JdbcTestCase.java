package com.lsy.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class JdbcTestCase {
    @Test
    public void test() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql:///mydb","root","root");
        PreparedStatement statement=null;
        //开启事务
        connection.setAutoCommit(false);
        //两个sql语句有一个错，皆被打回
        try {
            String sql="insert into t_user(username,password) values('mei','123')";
            statement=connection.prepareStatement(sql);
            statement.executeUpdate();

            String sql2="insert into t_user(username,password) values('me','123')";
            statement=connection.prepareStatement(sql2);
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            e.printStackTrace();
        }finally {
            statement.close();
            connection.close();

        }



    }
}
