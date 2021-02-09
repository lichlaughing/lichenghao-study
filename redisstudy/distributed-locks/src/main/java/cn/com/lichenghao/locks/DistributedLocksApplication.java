package cn.com.lichenghao.locks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DistributedLocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedLocksApplication.class, args);
    }
}
