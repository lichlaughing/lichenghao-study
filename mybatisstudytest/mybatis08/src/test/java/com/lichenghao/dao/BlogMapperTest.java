package com.lichenghao.dao;

import com.lichenghao.dao.utils.sqlSessionUtil;
import com.lichenghao.entity.Blog;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlogMapperTest {

    static BlogMapper mapper = null;

    @Before
    public void test() {
        mapper = sqlSessionUtil.getSqlSessionFactory().getMapper(BlogMapper.class);
    }

    @After
    public void testAfter() {

    }

    @Test
    public void test1() {
        Blog blog1 = mapper.blogById("674a2e17838147b4921c4521bdd2eb60");
        System.out.println(blog1);
        System.out.println("===================================");
        //更新另外一条数据
        Blog blog = new Blog();
        blog.setId("135b9d334bb1409cba5e6c2ccba986db");
        blog.setTitle("spring入门02");
        int i = mapper.updateBlog(blog);
        System.out.println(i);
        System.out.println("===================================");
        Blog blog2 = mapper.blogById("674a2e17838147b4921c4521bdd2eb60");
        System.out.println(blog2);
        System.out.println(blog1 == blog2);
    }

    @Test
    public void test2() {
        SqlSession sqlSessionFactory1 = sqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSessionFactory2 = sqlSessionUtil.getSqlSessionFactory();

        BlogMapper mapper1 = sqlSessionFactory1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.blogById("674a2e17838147b4921c4521bdd2eb60");
        System.out.println(blog1);
        sqlSessionFactory1.close();
        System.out.println("===================================");

        BlogMapper mapper2 = sqlSessionFactory2.getMapper(BlogMapper.class);
        Blog blog2 = mapper2.blogById("674a2e17838147b4921c4521bdd2eb60");
        System.out.println(blog2);
        sqlSessionFactory2.close();
    }
}
