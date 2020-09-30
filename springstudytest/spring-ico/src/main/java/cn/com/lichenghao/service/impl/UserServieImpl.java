package cn.com.lichenghao.service.impl;

import cn.com.lichenghao.service.UserService;

public class UserServieImpl implements UserService {

    public UserServieImpl() {
        System.out.println("UserServieImpl,加载了");
    }

    public void sayHi(String name) {
        System.out.println("Hi," + name);
    }
}
