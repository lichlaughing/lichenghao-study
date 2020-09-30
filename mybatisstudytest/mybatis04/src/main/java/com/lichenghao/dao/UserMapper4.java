package com.lichenghao.dao;

import com.lichenghao.entity.User;
import java.util.List;
import java.util.Map;

public interface UserMapper4 {
    User getUserById(Integer userId);
    List<User> userList(Map<String, Integer> page);
}
