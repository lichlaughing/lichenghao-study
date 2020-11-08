package cn.com.lichenghao.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringbootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 订阅队列，生产者
     */
    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("topic", "user.save", "hello topic".getBytes());
    }

    /**
     * 路由队列，生产者
     */
    @Test
    public void testRoute() {
        rabbitTemplate.convertAndSend("direct_logs", "info", "hello route".getBytes());
    }

    /**
     * 广播队列，生产者
     */
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "hello fanout".getBytes());
    }

    /**
     * 工作队列，生产者
     */
    @Test
    public void testWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", ("hello work" + i).getBytes());
        }
    }

    /**
     * 最简单的队列方式，生产者
     */
    @Test
    public void testSimplest() {
        rabbitTemplate.convertAndSend("hello", "hello simplest".getBytes());
    }
}
