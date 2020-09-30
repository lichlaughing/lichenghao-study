package com.example.licheurekacomsumer.service;

import org.example.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallBack implements UserClient {
    @Override
    public String login(User user) {
        return "出现错误";
    }
}
