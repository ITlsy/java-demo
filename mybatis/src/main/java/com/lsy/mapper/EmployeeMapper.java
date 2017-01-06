package com.lsy.mapper;

import com.lsy.pojo.Employee;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public interface EmployeeMapper {
    Employee findById(Integer id);
    List<Employee> findByDid(Integer did);
    List<Employee> findAll();
}
