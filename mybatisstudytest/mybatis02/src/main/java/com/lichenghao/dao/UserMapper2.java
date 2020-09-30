package com.lichenghao.dao;

import com.lichenghao.entity.User;

import java.util.List;

public interface UserMapper2 {
    List<User> userListByName2(String userName);
}
