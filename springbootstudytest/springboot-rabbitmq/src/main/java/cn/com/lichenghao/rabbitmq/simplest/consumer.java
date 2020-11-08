package cn.com.lichenghao.rabbitmq.simplest;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 最佳单的队列消费者，监听队列"hello"
 */
@Component
public class consumer {
    
    @RabbitListener(queuesToDeclare = @Queue("hello"))
    @RabbitHandler
    public void receive(String message) {
        System.out.println("simplest receive:" + message);
    }
}
