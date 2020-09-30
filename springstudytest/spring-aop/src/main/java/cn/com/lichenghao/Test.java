package cn.com.lichenghao;

import cn.com.lichenghao.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        /**
         * 配置文件方式
         */
        /*ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = (UserService)context.getBean("userService");
        userService.get();*/

        /**
         * 注解方式
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("application-annotation.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.save(1);
    }
}
