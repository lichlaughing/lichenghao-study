package com.example.licheurekacomsumer.service;

import org.example.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "lich-eureka-provider", fallback = UserClientFallBack.class)
public interface UserClient {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String login(@RequestBody User user);
}
