package com.lsy.pojo;

/**
 * Created by Administrator on 2017/1/4 0004.
 */
public class Employee {
    private Integer id;
    private String ename;
    private Integer did;
    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", ename='" + ename + '\'' +
                ", did=" + did +
                '}';
    }
}
