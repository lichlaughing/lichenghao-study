package com.example.licheurekacomsumer.web;

import com.example.licheurekacomsumer.service.UserClient;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "/user/userLogin", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        return userClient.login(user);
    }
}
