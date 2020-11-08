package cn.com.lichenghao.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 广播队列消费者
 */
@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs", type = "fanout"))
    })
    public void receive1(String message) {
        System.out.println("fanout receiver1 :" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs", type = "fanout"))
    })
    public void receive2(String message) {
        System.out.println("fanout receiver2 :" + message);
    }
}
