按照官网的介绍使用完成增删改查~

(1)首先准备数据库，准备一张测试的表，新建maven工程

(2)增加mybatis配置文件：mybatis-config.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url"
                          value="jdbc:mysql://127.0.0.1:3306/mybatis_test?userSSL=false&amp;characterEncoding=utf-8"/>
                <property name="username" value="root"/>
                <property name="password" value="root1234"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/lichenghao/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```
(3)增加实体类

(4)增加一个获取sqlSession的工具类
```
package com.lichenghao.dao.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class sqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSqlSessionFactory() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}

```
(5)新建接口，和对应的mapper文件

(6)执行测试即可
```
 @Test
    public void test1(){
        SqlSession sqlSession = sqlSessionUtil.getSqlSessionFactory();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.userList();
        System.out.println(users);
    }
```

注意 maven build的的时候可能缺失配置文件，增加自定义build
```
<build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```