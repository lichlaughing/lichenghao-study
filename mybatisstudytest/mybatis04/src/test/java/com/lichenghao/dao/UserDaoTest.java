package com.lichenghao.dao;

import com.lichenghao.dao.utils.sqlSessionUtil;
import com.lichenghao.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    static Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void userListByName() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper4 mapper = sqlSession.getMapper(UserMapper4.class);
        User userById = mapper.getUserById(1);
        logger.info("user:" + userById);
        sqlSession.close();
    }

    @Test
    public void userList() {
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper4 mapper = sqlSession.getMapper(UserMapper4.class);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("start", 0);
        map.put("pageSize", 2);
        List<User> users = mapper.userList(map);
        logger.info("userList:" + users);
        sqlSession.close();
    }
}
