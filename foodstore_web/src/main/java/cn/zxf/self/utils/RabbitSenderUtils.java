package cn.zxf.self.utils;

import cn.zxf.self.entry.Orders;
import cn.zxf.self.enums.RabbitConstant;
import cn.zxf.self.vo.OrderRecipes;
import cn.zxf.self.vo.UserOrder;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName RabbitSenderUtils
 * @Description TODO
 * @Author zxf
 * @DATE 2019/4/12
 */
@Deprecated
//@Component
public class RabbitSenderUtils implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
    private static final Logger logger  = LoggerFactory.getLogger(RabbitSenderUtils.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
    }

    //消息发送确认回调方法
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("消息发送成功:" + correlationData);
    }

    //消息发送失败回调方法
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.info("消息发送失败:" + new String(message.getBody()));
    }

    /**
     * 发送消息，不需要实现任何接口，供外部调用
     *
     * @param
     */
    public void send(Orders order, String exchangeName) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchangeName,RabbitConstant.RK_TRANSACTION, order.getOrderId().toString(), correlationId);
    }

    public void send(Orders orders){
        send(orders,RabbitConstant.EXCHANGE);
    }

    public void send(UserOrder userOrder){
        send(userOrder,RabbitConstant.EXCHANGE);
    }

    public void send(UserOrder userOrder, String exchangeName) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchangeName,RabbitConstant.RK_TRANSACTION, JSONObject.toJSON(userOrder), correlationId);
    }



    private static <T> T  get(Class<T> clazz,Object object){
        if(clazz.isInstance(object)){
            return clazz.cast(object);
        }
        return null;
    }

}
