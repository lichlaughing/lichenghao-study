package com.lichenghao.dao;

import com.lichenghao.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper5 {

    @Select("select * from tb_user")
    List<User> userList();

    @Select("select * from tb_user where id=#{id}")
    User getUserById(@Param("id") Integer userId);

    @Insert("insert into tb_user (id,username,age,birthday) values " +
            "(#{id},#{username},#{age},#{birthday})")
    int addUser(User user);

    @Update("update tb_user set username=#{username} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from tb_user where id = #{id}")
    int deleteUser(@Param("id") Integer id);
}
