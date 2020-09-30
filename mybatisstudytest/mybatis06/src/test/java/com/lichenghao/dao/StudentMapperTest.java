package com.lichenghao.dao;

import com.lichenghao.dao.utils.sqlSessionUtil;
import com.lichenghao.entity.Student;
import com.lichenghao.entity.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class StudentMapperTest {

    /**
     * test1~test2 N-1
     */

    @Test
    public void test1_studentList() {
        SqlSession sqlSessionFactory = sqlSessionUtil.getSqlSessionFactory();
        StudentMapper mapper = sqlSessionFactory.getMapper(StudentMapper.class);
        List<Student> students = mapper.studentList();
        for (Student s : students) {
            System.out.println(s);
        }
        sqlSessionFactory.close();
    }

    @Test
    public void test2_studentList2() {
        SqlSession sqlSessionFactory = sqlSessionUtil.getSqlSessionFactory();
        StudentMapper mapper = sqlSessionFactory.getMapper(StudentMapper.class);
        List<Student> students = mapper.studentList2();
        for (Student s : students) {
            System.out.println(s);
        }
        sqlSessionFactory.close();
    }

    /**
     * test3~test4 1-N
     */

    @Test
    public void test3_getTeacherById() {
        SqlSession sqlSessionFactory = sqlSessionUtil.getSqlSessionFactory();
        TeacherMapper mapper = sqlSessionFactory.getMapper(TeacherMapper.class);
        Teacher teacherById = mapper.getTeacherById(1);
        System.out.println(teacherById);
        sqlSessionFactory.close();
    }

    @Test
    public void test4_getTeacherById2() {
        SqlSession sqlSessionFactory = sqlSessionUtil.getSqlSessionFactory();
        TeacherMapper mapper = sqlSessionFactory.getMapper(TeacherMapper.class);
        Teacher teacherById = mapper.getTeacherById2(1);
        System.out.println(teacherById);
        sqlSessionFactory.close();
    }
}
