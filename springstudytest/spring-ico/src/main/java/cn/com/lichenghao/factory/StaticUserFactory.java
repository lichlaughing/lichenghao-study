package cn.com.lichenghao.factory;

import cn.com.lichenghao.service.impl.UserServieImpl;

public class StaticUserFactory {
    public static UserServieImpl instance() {
        return new UserServieImpl();
    }
}
