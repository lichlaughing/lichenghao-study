package com.example.licheurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LichEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LichEurekaServerApplication.class, args);
    }
}
