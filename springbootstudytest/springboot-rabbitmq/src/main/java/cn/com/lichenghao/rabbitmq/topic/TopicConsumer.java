package cn.com.lichenghao.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 订阅队列，消费者
 * xx.*:表示配置一个字符
 * xx.#:表示匹配一个或者多个字符
 */
@Component
public class TopicConsumer {

    /**
     * 监听用户操作
     *
     * @param message 消息内容
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topic", type = "topic"),
                    key = {"user.*"}
            )
    })
    public void receive1(String message) {
        System.out.println("topic receive1 :" + message);
    }

    /**
     * 监听用户操作和定点操作
     *
     * @param message 消息内容
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topic", type = "topic"),
                    key = {"user.*", "order.#"}
            )
    })
    public void receive2(String message) {
        System.out.println("topic receive2 :" + message);
    }
}
