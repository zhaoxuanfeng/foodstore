package cn.zxf.self.listeners;

import cn.zxf.self.entry.Orders;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.vo.UserOrder;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderListener
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/20
 */
@Deprecated
//@Component
public class OrderListener {

    private Logger logger = LoggerFactory.getLogger(OrderListener.class);

  /*  @RabbitListener(queues = "basicOrderQueue",containerFactory = "multiListenerContainer")
    public void transmitWaiterOrCookerOrder(@Payload byte[] message){
        UserOrder userOrder  =JSONObject.parseObject(message,UserOrder.class);


    }*/
    @RabbitListener(queues = "basicOrderQueue",containerFactory = "multiListenerContainer")
    public void  transmitBasicOrderQueue(UserOrder userOrder){
        
    }

    @RabbitListener(queues = "${express.info.mq.queue.name}",containerFactory = "singleListenerContainer")
    public void transmitExpressOrder(Orders orders){

    }
}