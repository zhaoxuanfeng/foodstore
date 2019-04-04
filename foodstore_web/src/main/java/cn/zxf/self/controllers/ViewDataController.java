package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.FoodInfoBussiness;
import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.utils.DateUtils;
import cn.zxf.self.vo.PageMsg;
import cn.zxf.self.vo.PagerModel;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(value = "/")
    public String indexView(){
        return "index";
    }

    @RequestMapping(value = "/view/searchInedxData.htm")
    @ResponseBody
    public PageMsg mainViewData(HttpServletRequest request, HttpSession session){
        logger.info(request.getRequestURI());
        PageMsg pageMsg = new PageMsg();
        Map<String ,Object> map = new HashMap<>();
       /* Long startTime = DateUtils.getCurrMonth();
        Long endTime = DateUtils.getCurrMilli();
        List<Recipes>  hotRecipesList = orderInfoBussiness.findHotRecipesIds(startTime ,endTime);*/
        List<Recipes>  hotRecipesList = orderInfoBussiness.findHotRecipesIds(null ,null);
        map.put("hotRecipesList",hotRecipesList);
        return pageMsg;
    }

}
