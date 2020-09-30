package com.lichenghao.dao;

import com.lichenghao.entity.Blog;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper {
    Blog blogById(@Param("id") String id);
    int updateBlog(Blog blog);
}
