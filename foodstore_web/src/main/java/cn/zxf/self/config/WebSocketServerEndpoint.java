package cn.zxf.self.config;

import cn.zxf.self.entry.Orders;
import cn.zxf.self.vo.OrderRecipes;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName WebSocketServerEndpoint
 * @Description TODO
 * @Author zxf
 * @DATE 2019/4/15
 * ServerEndpoint
 * <p>
 * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，但在springboot中连容器都是spring管理的。
 * <p>
 * 虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
 */
@Deprecated
//@ServerEndpoint("/ws/yxd/{userId}") //WebSocket客户端建立连接的地址
//@Component
public class WebSocketServerEndpoint {

    private  static final Logger logger = LoggerFactory.getLogger(WebSocketServerEndpoint.class);
    /**
     * 存活的session集合（使用线程安全的map保存）
     */
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();

    /**
     * 建立连接的回调方法
     *
     * @param session 与客户端的WebSocket连接会话
     * @param userId  用户名，WebSocket支持路径参数
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        livingSessions.put(session.getId(), session);
        logger.info(userId + "进入连接");
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId) {
        logger.info(userId + " : " + message);
        sendMessageToAll(userId + " : " + message);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        logger.error(error.getStackTrace() + "");
    }


    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        livingSessions.remove(session.getId());
        logger.info(userId + " 关闭连接");
    }

    /**
     * 单独发送消息
     *
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        Orders orders = JSONObject.parseObject(message, Orders.class);
        livingSessions.forEach((sessionId, session) -> {
            //发给指定的接收用户
//            if (userId.equals(messageVo.getReceiveUserId())) {
            sendMessage(session, message);
//            }
        });
    }


}
