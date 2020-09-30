package cn.com.lichenghao;

import cn.com.lichenghao.model.User;
import cn.com.lichenghao.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    public static void main(String[] args) {

        //读取磁盘上的配置文件
       /*
       FileSystemXmlApplicationContext fileSystemXmlApplicationContext =
                new FileSystemXmlApplicationContext("");
        UserService userService1 = (UserService) fileSystemXmlApplicationContext.getBean("userService");
        userService1.sayHi("Tom1");
        */

        //扫描带注解的
        /*
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("cn.com.lichenghao.service");
        UserService userService2 = (UserService) annotationConfigApplicationContext.getBean("userService");
        */

        //读取类路径下配置文件
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("beans.xml");
        //UserService userService = (UserService) ac.getBean("userService2");
        //userService.sayHi("Tom");

        User user = (User)ac.getBean("user");
        System.out.println(user);
    }
}
