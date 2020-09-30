package cn.com.lichenghao.jpa;

import cn.com.lichenghao.jpa.model.TbUserTestEntity;
import cn.com.lichenghao.jpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootSpringDataJpaApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        TbUserTestEntity tbUserTestEntity = new TbUserTestEntity();
        tbUserTestEntity.setId("001");
        tbUserTestEntity.setAg(100);
        tbUserTestEntity.setName("Tom");
        TbUserTestEntity tbUserTestEntity1 = userService.addUser(tbUserTestEntity);
        System.out.println(tbUserTestEntity1);
    }

    @Test
    public void test2() {
        TbUserTestEntity user = userService.getUser("001");
        System.out.println(user);
    }
}
