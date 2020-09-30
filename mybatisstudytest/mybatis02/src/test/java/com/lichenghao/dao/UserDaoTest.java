package com.lichenghao.dao;

import com.lichenghao.dao.utils.sqlSessionUtil;
import com.lichenghao.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {
    @Test
    public void userListByName() throws SQLException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);
        List<User> users = mapper.userListByName2("%李%");
        System.out.println(users);
        sqlSession.close();
    }
}
