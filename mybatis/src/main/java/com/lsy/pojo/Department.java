package com.lsy.pojo;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class Department {
    private Integer id;
    private String dname;
    private List<Employee> employeeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", dname='" + dname + '\'' +
                '}';
    }
}
