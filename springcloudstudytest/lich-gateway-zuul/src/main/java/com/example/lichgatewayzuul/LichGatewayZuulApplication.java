package com.example.lichgatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关
 */
@EnableZuulProxy
@SpringBootApplication
public class LichGatewayZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(LichGatewayZuulApplication.class, args);
    }
}
