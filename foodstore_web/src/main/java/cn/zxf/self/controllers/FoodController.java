package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.FoodInfoBussiness;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.entry.dto.StateInfo;
import cn.zxf.self.entry.vo.PageMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FoodController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/28
 */
@Controller
public class FoodController  extends  BaseController{

    @Autowired
    private FoodInfoBussiness foodInfoBussiness;

    @RequestMapping("/htm/foodInfo.htm")
    public String foodMain(HttpServletRequest request){
        return "foodMain";
    }

    @RequestMapping("/htm/foodInfoData.htm")
    @ResponseBody
    public PageMsg foodAllInfo(HttpServletRequest request){
        PageMsg pageMsg = new PageMsg();
        Map<String,Object>  params = resolveParams(request);

        pageMsg.setPageSize((Integer) request.getAttribute("pageSize"));

        pageMsg.setPageNumber((Integer) request.getAttribute("pageNumber"));

        StateInfo stateInfo = foodInfoBussiness.findFoodInfo(params);
        if(stateInfo.isState()){
            pageMsg.setRows((List<Recipes>) stateInfo.getData());
            pageMsg.setTotal(((List<Recipes>) stateInfo.getData()).size());
        }else {
            pageMsg.setTotal(0L);
        }
        return  pageMsg;
    }

    private Map<String,Object> resolveParams(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        String foodName = request.getParameter("foodName");
        if(null != foodName && StringUtils.isNotBlank(foodName)){
            map.put("foodName",foodName);
        }
        Integer foodType = (Integer) request.getAttribute("foodType");
        if(null != foodType && foodType != 0){
            map.put("foodType",foodType);
        }
        String  foodCuisine = request.getParameter("foodCuisine");
        if(null !=  foodCuisine && StringUtils.isNotBlank(foodName)){
            map.put("foodCuisine",foodCuisine);
        }
        String foodKey = request.getParameter("foodKey");
        if (null != foodKey && StringUtils.isNotBlank(foodKey)){
            map.put("foodKey",foodKey);
        }

        return map;
    }

}
