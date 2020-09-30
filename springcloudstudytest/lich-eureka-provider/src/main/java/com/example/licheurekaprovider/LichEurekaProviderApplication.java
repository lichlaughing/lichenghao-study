package com.example.licheurekaprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LichEurekaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(LichEurekaProviderApplication.class, args);
    }
}
