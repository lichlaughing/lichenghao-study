package cn.com.lichenghao.service.impl;

import cn.com.lichenghao.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    public void save(int a) {
        System.out.println("save 方法执行");
    }

    public void get() {
        System.out.println("get 方法执行");
        int n = 1 / 0;
    }

    public void delete(int id) {
        System.out.println("delete 方法执行");
    }
}
