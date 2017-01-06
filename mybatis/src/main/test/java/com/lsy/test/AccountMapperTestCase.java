package com.lsy.test;

import com.lsy.mapper.AccountMapper;
import com.lsy.pojo.Account;
import com.lsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
public class AccountMapperTestCase {
    @Test
    public void test(){
        //增
       /* SqlSession sqlSession= SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
        Account account=new Account();
        account.setName("tom");
        account.setAddress("china");
        accountMapper.save(account);
        sqlSession.commit();
        sqlSession.close();*/
//查
        /*SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
        Account account=accountMapper.findById(1);
        System.out.println(account);
        sqlSession.close();*/
//全查
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList=accountMapper.findAll();
        for (Account account:accountList){
            System.out.println(account);
        }


//改
       /* SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
        Account account=accountMapper.findById(1);
        account.setName("jack");
        accountMapper.update(account);
        System.out.println(account);
        sqlSession.close();*/


       /* //    删
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
        accountMapper.delete(1);
        sqlSession.commit();
        sqlSession.close();*/
    }

    @Test
    public void findByPage(){
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList=accountMapper.findByPage(0,1);
        for (Account account:accountList){
            System.out.println(account.getName());
        }
        sqlSession.close();
    }

}
