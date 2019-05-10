package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.FoodInfoBussiness;
import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.bussiness.UserDataBussiness;
import cn.zxf.self.config.RabbitmqConfiguration;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.entry.*;
import cn.zxf.self.utils.DateUtils;
import cn.zxf.self.utils.RabbitSenderUtils;
import cn.zxf.self.vo.PageMsg;
import cn.zxf.self.vo.PagerModel;
import cn.zxf.self.vo.UserOrder;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ViewDataController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/27
 */
@Controller
public class ViewDataController {

    private final static Logger logger  = LoggerFactory.getLogger(ViewDataController.class);

    @Autowired
    private AmqpTemplate  amqpTemplate;

    private PagerModel pagerModel = new PagerModel();

    @Autowired
    private OrderInfoBussiness orderInfoBussiness ;

    @Autowired
    private FoodInfoBussiness foodInfoBussiness ;

    @Autowired
    private UserDataBussiness userDataBussiness;


    @RequestMapping(value = "/")
    public String indexView(){
        return "index";
    }

    /**
        *@Description  //TODO  主页默认数据
        *@Param [request, session]
        *@Return  cn.zxf.self.vo.PageMsg
     **/
    @RequestMapping(value = "/view/searchInedxData.action")
    @ResponseBody
    public PageMsg mainViewData(HttpServletRequest request, HttpSession session){
        logger.info(request.getRequestURI());
        PageMsg pageMsg = new PageMsg();
       /*
        Long startTime = DateUtils.getCurrMonth();
        Long endTime = DateUtils.getCurrMilli();
        List<Recipes>  hotRecipesList = orderInfoBussiness.findHotRecipesIds(startTime ,endTime);
        */
        List<Recipes>  hotRecipesList = orderInfoBussiness.findHotRecipesIds(null ,null);
        pageMsg.setRows(hotRecipesList);
        return pageMsg;
    }


    /**
        *@Description  //TODO  对食品信息进行数据展示
        *@Param [id, request, response]
        *@Return  java.lang.String
     **/
    @RequestMapping(value = "/view/detailData.action")
    public String  dataDetial(@RequestParam(value = "id") Long id, HttpServletRequest request, HttpServletResponse response){
        logger.info(request.getRequestURI());
        logger.info(id.toString());
        UserInfo currUser = (UserInfo) request.getSession().getAttribute("userInfo");
        if(null == currUser){
            return "login";
        }
        if(!ObjectUtils.allNotNull(id)){
            return "reception/error";
        }
        Recipes recipes = foodInfoBussiness.findFoodInfoById(id);
        if(ObjectUtils.allNotNull(recipes)){
            request.setAttribute("commodity",recipes);
        }
        List<UserAddressRel> userAddressRelList = userDataBussiness.findAddressListById(currUser.getUserId());
        if(ObjectUtils.allNotNull(userAddressRelList) && userAddressRelList.size() != 0 ){
            request.setAttribute("addressList",userAddressRelList);
        }
        logger.info("end");
        return "reception/foodDetail";
    }

    /**
        *@Description  //TODO  往购物车添加数据
        *@Param [userOrder, request]
        *@Return  cn.zxf.self.vo.PagerModel
     **/
    @RequestMapping("/self/addTolleyData.action")
    @ResponseBody
    public PagerModel addTolleyData(UserOrder  userOrder ,HttpServletRequest request){
        logger.info(request.getRequestURI());

        Orders orders = new Orders();
        orders.setUserId(userOrder.getUserId());
        orders.setCount(userOrder.getCount());
        orders.setNotes(userOrder.getOrderNote());
        orders.setOrderRecipesId(userOrder.getRecipesId().intValue());
        orders.setRealPrice(userOrder.getOrderPrice());
        orders.setCreateTime(DateUtils.getCurrMilli());
        orders.setIsDelete(0);
        orders.setOrderStatus("未支付");
        orders.setOrderAddress(userOrder.getOrderAddress());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        orders.setOrderDate(Integer.parseInt(df.format(LocalDateTime.now())));

        StateInfo stateInfo = orderInfoBussiness.addNewOrder(orders);
        Orders return_orders = (Orders) stateInfo.getData();

        orders.setOrderId(return_orders.getOrderId());
        userOrder.setOrderId(return_orders.getOrderId());
        //插入订单菜单关系
        OrderRecipesRel orderRecipesRel = new OrderRecipesRel();
        orderRecipesRel.setRecipesId(userOrder.getRecipesId().intValue());
        orderRecipesRel.setStartTime(DateUtils.getCurrMilli());
        orderRecipesRel.setOrderId(orders.getOrderId());
        orderRecipesRel.setRelStatus("1");

        stateInfo = orderInfoBussiness.addNewOrderRecipesRel(orderRecipesRel);
        pagerModel.setMessage(stateInfo.getMessage());
        if(!stateInfo.isState()){
            pagerModel.setData(orders);
        }
//        logger.info("向queue中添加消息");
//        RabbitSenderUtils rabbitSenderUtils = new RabbitSenderUtils();s
//        rabbitSenderUtils.send(userOrder);
        pagerModel.setData(stateInfo.getData());
        return pagerModel;
    }

    /**
        *@Description  //TODO  主页其他栏目数据展示
        *@Param [recipes]
        *@Return  cn.zxf.self.vo.PagerModel
     **/
    @RequestMapping("/view/selectRecipesInfo.action")
    public String selectRecipesInfo(@RequestParam("foodKey") String foodKey,@RequestParam("foodCuisine") String foodCuisine,HttpServletRequest request){
        Recipes recipes = new Recipes();
        recipes.setFoodCuisine(foodCuisine);
        recipes.setFoodKey(foodKey);
        StateInfo stateInfo = foodInfoBussiness.findFoodInfoByRecepies(recipes);

        if (ObjectUtils.allNotNull(stateInfo) ){
            request.setAttribute("flag",stateInfo.isState());
            request.setAttribute("message",stateInfo.getMessage());
            if(stateInfo.isState()){
                request.setAttribute("foodListInfo",(List<Recipes>)stateInfo.getData());
            }
        }
        return "reception/foodList";
    }



    @RequestMapping("/view/redirectTrolley.action")
    public String redirectTrolley(){
        return "reception/trolley";
    }
}
