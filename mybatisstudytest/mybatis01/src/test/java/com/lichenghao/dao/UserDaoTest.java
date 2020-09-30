package com.lichenghao.dao;


import com.lichenghao.dao.utils.sqlSessionUtil;
import com.lichenghao.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public class UserDaoTest {

    @Test
    public void test1_userList() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.userList();
        System.out.println(users);
        sqlSession.close();
    }

    @Test
    public void test2_getUserById() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);
        sqlSession.close();
    }

    @Test
    public void test3_updateUserById() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1);
        userById.setUsername("tom01");
        int i = mapper.updateUserById(userById);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test4_insertUser() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User xiaotaoqi = new User(10, "xiaotaoqi", 50, new Timestamp(System.currentTimeMillis()));
        int i = mapper.addUser(xiaotaoqi);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test5_addUserByMap() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", 12);
        map.put("n", "李白");
        map.put("a", 100);
        map.put("b", new Timestamp(System.currentTimeMillis()));
        int i = mapper.addUserByMap(map);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test6_userListByName() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.userListByName("%李%");
        System.out.println(users);
        sqlSession.close();
    }
}
