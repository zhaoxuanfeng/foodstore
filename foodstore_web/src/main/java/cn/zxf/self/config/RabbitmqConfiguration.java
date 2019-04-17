package cn.zxf.self.config;

import cn.zxf.self.enums.RabbitConstant;
import cn.zxf.self.listeners.UserOrderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;

/**
 * @ClassName RabbitmqConfiguration
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/20
 */
@Configuration
public class RabbitmqConfiguration  {


    private static final Logger log= LoggerFactory.getLogger(RabbitmqConfiguration.class);
    @Resource
    private Environment env;
    //TODO: 通过该变量获取配置文件属性

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 单一消费者
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(2);
        factory.setMaxConcurrentConsumers(50);
        factory.setPrefetchCount(1);
        factory.setTxSize(100);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * 多个消费者
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",int.class));
        return factory;
    }
    /**
        *@Description  //TODO  消息的发送组件
        *@Param []
        *@Return  org.springframework.amqp.rabbit.core.RabbitTemplate
     **/
    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        // rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message));
        return rabbitTemplate;
    }

    @Autowired
    private UserOrderListener userOrderListener;

    @Bean
    public SimpleMessageListenerContainer listenerContainerUserOrder(@Qualifier(RabbitConstant.QUEUE_EXPRESS) Queue userOrderQueue){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        //TODO: 并发设置
        container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",Integer.class));
        container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",Integer.class));
        container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",Integer.class));

        //TODO: 消息确认机制
        container.setQueues(userOrderQueue);
        container.setMessageListener(userOrderListener);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return container;

    }

    @Bean
    public DirectExchange basicExchange() {
        return new DirectExchange(RabbitConstant.EXCHANGE, true, false);
    }
    @Bean(name = RabbitConstant.QUEUE_BASE_ORDER)
    public Queue basicQueue() {
        return new Queue(RabbitConstant.QUEUE_BASE_ORDER, true);
    }

    @Bean
    public DirectExchange expressExchange(){
        return new DirectExchange(RabbitConstant.EXCHANGE_EXPRESS,true,false);
    }
    @Bean(name= RabbitConstant.QUEUE_EXPRESS)
    public Queue expressQueue(){
        return new Queue(RabbitConstant.QUEUE_EXPRESS,true);
    }

    @Bean
    public DirectExchange waiterExchange(){
        return new DirectExchange(RabbitConstant.EXCHANGE_WAITER,true,false);
    }

    @Bean(name = RabbitConstant.QUEUE_WAITER)
    public Queue waiterQueue() {
        return new Queue(RabbitConstant.QUEUE_WAITER, true);
    }

    @Bean
    public Binding basicBinding() {
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(RabbitConstant.RK_TRANSACTION);
    }
    @Bean
    public Binding expressBinding(){
        return BindingBuilder.bind(expressQueue()).to(expressExchange()).with(RabbitConstant.RK_CONTRACT);
    }

    @Bean Binding waiterBinding(){
        return BindingBuilder.bind(waiterQueue()).to(waiterExchange()).with(RabbitConstant.RK_QUALIFICATION);
    }
    /**
        *@Description  //TODO  配置websocket
        *@Param []
        *@Return  ServerEndpointExporter
     **/
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                return chain.filter(exchange);
            }
        };
    }
}