package com.lsy.test;

import com.lsy.pojo.Dept;
import com.lsy.pojo.Employee;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class OneToManyTest {
    @Test
    public void save(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Dept dept=new Dept();
        dept.setDeptname("开发部");

        Employee employee=new Employee();
        employee.setEmpname("张三");
        employee.setDept(dept);

        Employee employee2=new Employee();
        employee2.setEmpname("李四");
        employee2.setDept(dept);

       /* Set<Employee> employees=new HashSet<>();
        employees.add(employee);
        employees.add(employee2);

        dept.setEmployeeSet(employees);*/

        //1.先存一，再存多
        //2.让一端放弃关系维护（一）
        session.save(dept);
        session.save(employee);
        session.save(employee2);


        session.getTransaction().commit();
    }

    @Test
    public void findOneToMany(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Dept dept= (Dept) session.get(Dept.class,1);
        //懒加载
        Set<Employee> employeeSet=dept.getEmployeeSet();
        for (Employee employee:employeeSet){
            System.out.println(employee.getEmpname());
        }
        session.getTransaction().commit();
    }

    @Test
    public void findManyToOne(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Employee employee= (Employee) session.get(Employee.class,2);
        System.out.println(employee.getEmpname());
        //懒加载
        System.out.println(employee.getDept().getDeptname());

        //N+1
       /* Criteria criteria=session.createCriteria(Employee.class);
        List<Employee> employees=criteria.list();
        for (Employee employee:employees){
            System.out.println(employee.getEmpname()+"->"+employee.getDept().getDeptname());
        }*/
        session.getTransaction().commit();
    }


    @Test
    public void delete(){
        Session session=SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Dept dept= (Dept) session.get(Dept.class,4);
        session.delete(dept);
        session.getTransaction().commit();
    }
}
