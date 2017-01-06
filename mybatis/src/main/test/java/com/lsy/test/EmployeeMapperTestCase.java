package com.lsy.test;

import com.lsy.mapper.EmployeeMapper;
import com.lsy.pojo.Department;
import com.lsy.pojo.Employee;
import com.lsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class EmployeeMapperTestCase {
    @Test
    public void findById(){
        SqlSession sqlSession= SqlSessionFactoryUtil.getSqlSession();
        EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
        Employee employee=employeeMapper.findById(1);
        Department department=employee.getDepartment();
        System.out.println(employee);
        System.out.println(department);
        sqlSession.close();
    }
    @Test
    public void findAll(){
        SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSession();
        EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employeeList=employeeMapper.findAll();
        for (Employee employee:employeeList){
            System.out.println(employee);
            Department department=employee.getDepartment();
            System.out.println(department);
            System.out.println("..................");
        }
        sqlSession.close();
    }
}
