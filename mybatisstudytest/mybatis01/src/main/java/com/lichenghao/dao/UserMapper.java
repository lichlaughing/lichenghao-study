package com.lichenghao.dao;

import com.lichenghao.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> userList();
    User getUserById(Integer userId);
    int updateUserById(User user);
    int addUser(User user);
    int addUserByMap(Map<String,Object> map);
    List<User> userListByName(String userName);
}
