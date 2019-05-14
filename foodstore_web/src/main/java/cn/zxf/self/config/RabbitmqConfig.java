package cn.zxf.self.config;

import cn.zxf.self.enums.RabbitConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName RabbitmqConfig
 * @Description TODO
 * @Author zxf
 * @DATE 2019/5/14
 */
@Component
public class RabbitmqConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Bean
    public Queue  orderQueue(){
        return new Queue(RabbitConstant.QUEUE_BASE_ORDER);
    }

    @Bean
    public Queue waiterQueue(){
        return new Queue(RabbitConstant.QUEUE_WAITER);
    }

    @Bean
    public TopicExchange topicExchange(){
        return  new TopicExchange(RabbitConstant.EXCHANGE);
    }

    @Bean
    public Binding topicBindingOrderQueue(){
        return BindingBuilder.bind(orderQueue()).to(topicExchange()).with("zxf.order");
    }

    @Bean
    public Binding topicBindingWaiterQueue(){
        return BindingBuilder.bind(waiterQueue()).to(topicExchange()).with("zxf.waiter");
    }
}
