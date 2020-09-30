package com.example.licheurekaprovider.service;

import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        logger.info("provider-2 userService");
        if (user.getUserName().equals("tom") && user.getPassword().equals("123456")) {
            return "success";
        }
        return "false";
    }
}
