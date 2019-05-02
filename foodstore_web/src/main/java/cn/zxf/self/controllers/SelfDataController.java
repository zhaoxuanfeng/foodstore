package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.vo.PageMsg;
import cn.zxf.self.vo.UserOrder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName SeleDataController
 * @Description TODO 主要进行用户个人数据的操作
 * @Author zxf
 * @DATE 2019/4/4
 */
@Controller
public class SelfDataController {

    private static  final Logger logger = LoggerFactory.getLogger(SelfDataController.class);

    @Autowired
    private OrderInfoBussiness orderInfoBussiness;


    /**
        *@Description  //TODO  查询购物车的信息
        *@Param [request, session]
        *@Return  cn.zxf.self.vo.PageMsg
     **/
    @RequestMapping("/self/tolleyData.action")
    @ResponseBody
    public PageMsg selfTolleyData(HttpServletRequest request,HttpSession session){
        logger.info("url : " + request.getRequestURI());
        PageMsg pageMsg = new PageMsg();
        UserInfo currUser = (UserInfo) session.getAttribute("userInfo");
        if(ObjectUtils.allNotNull(currUser)){
            logger.info(currUser.getUserId().toString());
            List<String> statusList = new ArrayList<>();
            statusList.add("3");
            StateInfo stateInfo = orderInfoBussiness.findRecipesByUser(currUser.getUserId(),null,null,statusList);
            logger.info(stateInfo.getData().toString());
            if(ObjectUtils.allNotNull(stateInfo)){
                List<UserOrder> sumRows =new  ArrayList<>();

                //                int length = ((List<Map<String,List<Recipes>>>)stateInfo.getData()).size();
                Integer orderPrice = 0;
                for (int i = 0;i<((List<Map<String,Object>>)stateInfo.getData()).size();i++ ){
//                    List<Recipes rowData  =  ((List<Map<String,List<Recipes>>>)stateInfo.getData()).get(i).get("recipes");
                    Recipes rowData = ((List<Map<String,Recipes>>)stateInfo.getData()).get(i).get("recipes");
                    UserOrder userOrder = new UserOrder();
                    userOrder.setOrderId((Long) ((List<Map<String,Object>>)stateInfo.getData()).get(i).get("orderId"));

                    /*for (Recipes recipes:rowData){
                        userOrder.setRecipesId(recipes.getId().longValue());
                        userOrder.setRecipesName(recipes.getFoodName());
                        userOrder.setRecipesPrice(recipes.getFoodPrice());
                        orderPrice += recipes.getFoodPrice();
                    }*/
                    userOrder.setRecipesId(rowData.getId().longValue());
                    userOrder.setRecipesName(rowData.getFoodName());
                    userOrder.setRecipesPrice(rowData.getFoodPrice());
                    orderPrice += rowData.getFoodPrice();
                    userOrder.setOrderPrice(orderPrice.intValue());
                    sumRows.add(userOrder);
                }
                logger.info("sumData --:"+sumRows);
                pageMsg.setRows(sumRows);
                pageMsg.setTotal(sumRows.size());
                pageMsg.setPageNumber(10);
                pageMsg.setPageSize(5);
            }else {
                pageMsg.setRows(null);
            }
        }else{
            pageMsg.setRows(null);
        }
        logger.info(pageMsg.toString());
        return pageMsg;
    }


    @RequestMapping("/self/payDirect.action")
    @ResponseBody
//    public ModelAndView doPayAction(@RequestParam("userOrderList") List<UserOrder> userOrderList, @RequestParam("sumPrice") Integer sumPrice, HttpSession session){
    public String doPayAction(HttpServletRequest request, HttpSession session){
        String tmpId = UUID.randomUUID().toString();
        logger.info("uri:"+request.getRequestURI());
        String userOrderStr = request.getParameter("userOrderList");
        logger.info("userOrderStr: "+userOrderStr);
        List<UserOrder> userOrderList = JSONArray.parseArray(userOrderStr,UserOrder.class);
        Integer sumPrice = Integer.parseInt(request.getParameter("sumPrice")) ;
        logger.info("sumPrice:"+sumPrice+" ,userOrderList: "+userOrderList.toString());
/*

        for (UserOrder userOrder:userOrderList){
            orderInfoBussiness.updateOrderFlag(userOrder.getOrderId(),"支付");

        }
*/

        session.setAttribute("tmpUserOders",userOrderList);
//        mv.addObject("userOrderList",userOrderList);
        request.setAttribute("sumPrice",sumPrice);
        request.setAttribute("tmpId",tmpId);
        return  "/reception/payMoney.html";
    }
}
