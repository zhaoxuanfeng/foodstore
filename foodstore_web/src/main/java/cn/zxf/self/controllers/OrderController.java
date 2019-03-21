package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.vo.PagerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @ClassName OrderController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/20
 */
@Controller
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String Prefix="rabbit";

    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderInfoBussiness orderInfoBussiness;

    private  PagerModel pagerModel = new PagerModel();

    @RequestMapping(value="/user/addOrder.htm")
    public PagerModel addOrder(Long userId, List<Recipes> recipesList){

        /*
            //逻辑未完成
            1.添加订单信息到表
            2.返回订单信息到队列
        */
        //消息
        byte[] message = "message".getBytes();

        logger.info("订单加入未完成队列");
        rabbitTemplate.setExchange(env.getProperty("basic.info.mq.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("basic.info.mq.routing.key.name"));
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        Message msg=MessageBuilder.withBody(message).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
        rabbitTemplate.convertAndSend(msg);
        return pagerModel;
    }




}
