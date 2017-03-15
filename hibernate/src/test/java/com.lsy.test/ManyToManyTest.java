package com.lsy.test;

import com.lsy.pojo.Student;
import com.lsy.pojo.Teacher;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class ManyToManyTest {
    @Test
    public void save(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Student s1=new Student();
        s1.setStuname("S1");
        Student s2=new Student();
        s2.setStuname("S2");
        Teacher t1=new Teacher();
        t1.setTeaname("T1");
        Teacher t2=new Teacher();
        t2.setTeaname("T2");
        Set<Teacher> teacherSet=new HashSet<>();
        teacherSet.add(t1);
        teacherSet.add(t2);
        s1.setTeacherSet(teacherSet);
        s2.setTeacherSet(teacherSet);

        Set<Student> studentSet = new HashSet<>();
        studentSet.add(s1);
        studentSet.add(s2);

        t1.setStudentSet(studentSet);
        t2.setStudentSet(studentSet);

        session.save(s1);
        session.save(s2);
        session.save(t1);
        session.save(t2);
        session.getTransaction().commit();
    }

    @Test
    public void findAll(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Student student= (Student) session.get(Student.class,1);
        System.out.println(student.getStuname());

        //1.在映射文件中奖lazy属性配置为false
        //2.Hibernate.initialize(student.getTeacherSet());
        //3.student.getTeacherSet().size();
        //4.OpenSessionInView Filter(最好的做法)
        Set<Teacher> teacherSet=student.getTeacherSet();
        for (Teacher teacher:teacherSet){
            System.out.println(teacher.getTeaname());
        }
        session.getTransaction().commit();
    }
}
