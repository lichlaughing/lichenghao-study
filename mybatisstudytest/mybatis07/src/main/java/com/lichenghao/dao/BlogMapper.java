package com.lichenghao.dao;

import com.lichenghao.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    int addBlog(Blog blog);
    List<Blog> queryByIf(Map<String, Object> param);
    List<Blog> queryByChoose(Map<String, Object> param);
    int updateBlog(Blog blog);
    List<Blog> queryByForeach(Map<String, Object> param);
}
