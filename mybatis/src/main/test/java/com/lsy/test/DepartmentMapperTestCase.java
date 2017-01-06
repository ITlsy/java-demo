package com.lsy.test;

import com.lsy.mapper.DepartmentMapper;
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
public class DepartmentMapperTestCase {
    @Test
    public void findById(){
        SqlSession sqlSession= SqlSessionFactoryUtil.getSqlSession();

       /* DepartmentMapper departmentMapper=sqlSession.getMapper(DepartmentMapper.class);
        Department department=departmentMapper.findById(1);
        System.out.println(department);*/

        DepartmentMapper departmentMapper=sqlSession.getMapper(DepartmentMapper.class);
        Department department=departmentMapper.findById(1);
        System.out.println(department);
        List<Employee> employeeList=department.getEmployeeList();
        for (Employee employee:employeeList) {
            System.out.println(employee);
        }



       /* EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employeeList=employeeMapper.findByDid(3);
        for (Employee employee:employeeList){
            System.out.println(employee);
        }*/


    }
}
