package cn.zxf.self.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.xml.stream.util.StreamReaderDelegate;
import java.util.Properties;

/**
 * @ClassName RabbitmqConfiguration
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/20
 */
@Configuration
public class RabbitmqConfiguration {

    //TODO: 通过该变量获取配置文件属性
    @Resource
    private Environment env;

    /*    @Bean(name="message")
            public Queue queueMessage() {
                return new Queue("topic.message");
            }
    
            @Bean(name="messages")
            public Queue queueMessages() {
                return new Queue("topic.messages");
            }
    
            @Bean
            public TopicExchange exchange() {
                return new TopicExchange("exchange");
            }
    
             @Bean
            Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
                return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
            }
    
            @Bean
            Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
                return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
            }
    */
    @Bean
    public DirectExchange basicExchange() {

        return new DirectExchange(env.getProperty("basic.info.mq.exchange.name"), true, false);
    }

    @Bean(name = "basicQueue")
    public Queue basicQueue() {
        return new Queue(env.getProperty("basic.info.mq.queue.name"), true);
    }

    @Bean
    public Binding basicBinding() {
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(env.getProperty("basic.info.mq.routing.key.name"));
    }
}