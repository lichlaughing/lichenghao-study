package cn.com.lichenghao.rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 路由队列的消费者
 */
@Component
public class RouteConsumer {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "direct_logs", type = "direct"),
            key = {"info", "warn", "error"}
    )})
    public void receive1(String message) {
        System.out.println("route receive1 :" + message);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "direct_logs", type = "direct"),
            key = {"error"}
    )})
    public void receive2(String message) {
        System.out.println("route receive1 :" + message);
    }
}
