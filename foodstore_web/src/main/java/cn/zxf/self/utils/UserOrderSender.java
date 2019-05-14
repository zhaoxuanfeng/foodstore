package cn.zxf.self.utils;

import cn.zxf.self.enums.RabbitConstant;
import cn.zxf.self.vo.UserOrder;
import org.apache.catalina.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserOrderSender
 * @Description TODO
 * @Author zxf
 * @DATE 2019/5/14
 */
@Component
public class UserOrderSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendOrderQueue(UserOrder userOrder){

        this.amqpTemplate.convertAndSend(RabbitConstant.QUEUE_BASE_ORDER,"zxf.order");
    }

    public void sendWaiterQueue(UserOrder userOrder){
        this.amqpTemplate.convertAndSend(RabbitConstant.QUEUE_WAITER,"zxf.waiter");

    }
}
