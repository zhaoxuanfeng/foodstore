package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.FoodInfoBussiness;
import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.bussiness.UserDataBussiness;
import cn.zxf.self.entry.Orders;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.entry.UserAddressRel;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.utils.DateUtils;
import cn.zxf.self.vo.PageMsg;
import cn.zxf.self.vo.PagerModel;
import cn.zxf.self.vo.UserOrder;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/view/searchInedxData.action")
    @ResponseBody
    public PageMsg mainViewData(HttpServletRequest request, HttpSession session){
        logger.info(request.getRequestURI());
        PageMsg pageMsg = new PageMsg();
       /* Long startTime = DateUtils.getCurrMonth();
        Long endTime = DateUtils.getCurrMilli();
        List<Recipes>  hotRecipesList = orderInfoBussiness.findHotRecipesIds(startTime ,endTime);*/
        List<Recipes>  hotRecipesList = orderInfoBussiness.findHotRecipesIds(null ,null);
        pageMsg.setRows(hotRecipesList);
        return pageMsg;
    }


    @RequestMapping(value = "/view/detailData.action")
    public String  dataDetial(@RequestParam(value = "id") Long id, HttpServletRequest request, HttpServletResponse response){
        logger.info(request.getRequestURI());
        logger.info(id.toString());
        UserInfo currUser = (UserInfo) request.getSession().getAttribute("userInfo");
        if(!ObjectUtils.allNotNull(id)){
            return null;
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


    @RequestMapping("/self/addTolleyData.action")
    public PagerModel addTolleyData(UserOrder  userOrder ,HttpServletRequest request){
        logger.info(request.getRequestURI());
        System.out.println(userOrder);
        Orders orders = new Orders();
        orders.setUserId();
        return pagerModel;
    }
}
