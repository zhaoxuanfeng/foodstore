package cn.zxf.self.listeners;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserOrderListener
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/21
 */
@Deprecated
//@Component("userOrderListener")
public class UserOrderListener  implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(UserOrderListener.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

    }
}
