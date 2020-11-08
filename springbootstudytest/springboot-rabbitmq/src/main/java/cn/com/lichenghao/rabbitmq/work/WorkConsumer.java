package cn.com.lichenghao.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 工作队列消费者，默认是轮训的，一个一个工作;可以修改配置让消费者"能者多劳"
 */
@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    @RabbitHandler
    public void receive1(String message) {
        System.out.println("workConsumer1 receive:" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    @RabbitHandler
    public void receive2(String message) {
        System.out.println("workConsumer1 receive:" + message);
    }
}
