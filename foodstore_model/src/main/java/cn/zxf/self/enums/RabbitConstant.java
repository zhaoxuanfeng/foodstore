package cn.zxf.self.enums;

/**
 * @ClassName RabbitConstant
 * @Description TODO
 * @Author zxf
 * @DATE 2019/4/15
 */
public class RabbitConstant {

    //交换机名称
    public final static  String EXCHANGE  = "exchange_test";
    public final static String EXCHANGE_EXPRESS = "exchange_express";
    public final static String EXCHANGE_WAITER = "exchange_waiter";
    public final static String EXCHANGE_ORDER = "exchange_order";

    //队列名称
    public final static String QUEUE_BASE_ORDER = "basicOrderQueue";
    public final static String QUEUE_EXPRESS  ="expressOrderQueue";
    public final static String QUEUE_WAITER = "waiterOrderQueue";

    //路由key
    public final static String RK_TRANSACTION = "transaction";
    public final static String RK_CONTRACT = "contract";
    public final static String RK_QUALIFICATION = "qualification";



    public final static String RK_ORDER = "routeKey_order";
}
