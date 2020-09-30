package com.lichenghao.dao;

import com.lichenghao.dao.utils.sqlSessionUtil;
import com.lichenghao.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BlogMapperTest {

    static BlogMapper mapper = null;

    @Before
    public void test() {
        mapper = sqlSessionUtil.getSqlSessionFactory().getMapper(BlogMapper.class);
    }

    @Test
    public void test1() {
        SqlSession sqlSessionFactory = sqlSessionUtil.getSqlSessionFactory();
        BlogMapper mapper = sqlSessionFactory.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(sqlSessionUtil.getRandomId());
        blog.setTitle("mybatis入门");
        blog.setAuthor("李白");
        blog.setCreateTime(new Date());
        blog.setViews(37);
        int i = mapper.addBlog(blog);
        System.out.println(i);

        Blog blog2 = new Blog();
        blog2.setId(sqlSessionUtil.getRandomId());
        blog2.setTitle("spring入门");
        blog2.setAuthor("李白");
        blog2.setCreateTime(new Date());
        blog2.setViews(130);
        i = mapper.addBlog(blog2);
        System.out.println(i);

        Blog blog3 = new Blog();
        blog3.setId(sqlSessionUtil.getRandomId());
        blog3.setTitle("docker入门");
        blog3.setAuthor("杜甫");
        blog3.setCreateTime(new Date());
        blog3.setViews(100);
        i = mapper.addBlog(blog3);
        System.out.println(i);
    }

    @Test
    public void queryByIf() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("title", "spring");
        List<Blog> blogs = mapper.queryByIf(map);
        System.out.println(blogs);
    }

    @Test
    public void queryByChoose() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("title", "入门");
        map.put("author", "李白");
        List<Blog> blogs = mapper.queryByChoose(map);
        System.out.println(blogs);
    }

    @Test
    public void updateBlog() {
        Blog blog = new Blog();
        blog.setId("674a2e17838147b4921c4521bdd2eb60");
        blog.setTitle("mybatis入门02");
        int i = mapper.updateBlog(blog);
        System.out.println(i);
    }

    @Test
    public void queryByForeach() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<String> ids = new ArrayList<String>();
        ids.add("674a2e17838147b4921c4521bdd2eb60");
        ids.add("135b9d334bb1409cba5e6c2ccba986db");
        map.put("ids", ids);
        List<Blog> blogs = mapper.queryByForeach(map);
        System.out.println(blogs);
    }
}
