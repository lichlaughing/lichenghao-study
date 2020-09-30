package com.lichenghao.dao;

import com.lichenghao.dao.utils.sqlSessionUtil;
import com.lichenghao.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoTest {
    @Test
    public void userListByName() throws SQLException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper3 mapper = sqlSession.getMapper(UserMapper3.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);
        sqlSession.close();
    }
}
