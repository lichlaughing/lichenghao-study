package cn.com.lichenghao.factory;

import cn.com.lichenghao.service.impl.UserServieImpl;

public class UserFactory {
    public UserServieImpl instance() {
        return new UserServieImpl();
    }
}
