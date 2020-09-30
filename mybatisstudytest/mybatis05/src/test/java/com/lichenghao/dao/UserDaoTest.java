package com.lichenghao.dao;

import com.lichenghao.dao.utils.sqlSessionUtil;
import com.lichenghao.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

public class UserDaoTest {

    @Test
    public void userList() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper5 mapper = sqlSession.getMapper(UserMapper5.class);
        List<User> users = mapper.userList();
        System.out.println(users);
        sqlSession.close();
    }

    @Test
    public void getUserById() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper5 mapper = sqlSession.getMapper(UserMapper5.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);
        sqlSession.close();
    }

    @Test
    public void addUser() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper5 mapper = sqlSession.getMapper(UserMapper5.class);
        User abc = new User(101, "abc", 20, new Timestamp(System.currentTimeMillis()));
        int i = mapper.addUser(abc);
        System.out.println(i);
        sqlSession.close();
    }

    @Test
    public void updateUser() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper5 mapper = sqlSession.getMapper(UserMapper5.class);
        User abc = new User(101, "abc2", 20, new Timestamp(System.currentTimeMillis()));
        int i = mapper.updateUser(abc);
        System.out.println(i);
        sqlSession.close();
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper5 mapper = sqlSession.getMapper(UserMapper5.class);
        int i = mapper.deleteUser(101);
        System.out.println(i);
        sqlSession.close();
    }
}
