package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.FoodInfoBussiness;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.entry.dto.StateInfo;
import cn.zxf.self.entry.vo.PageMsg;
import cn.zxf.self.entry.vo.PagerModel;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    @Autowired
    private FoodInfoBussiness foodInfoBussiness;

    private PagerModel pageModel = new PagerModel();

    private StateInfo stateInfo = new StateInfo();

    @RequestMapping(value = "/htm/modifyFood.htm")
    @ResponseBody
    public PagerModel modifyFoodInfo(HttpServletRequest request,Recipes recipes){
        logger.info("url:"+request.getRequestURI());
        logger.info("请求参数为："+recipes.toString());
        pageModel.setStatus(false);

        if(null == recipes || !ObjectUtils.allNotNull(recipes)){
            pageModel.setMessage("提交数据为空！");
            return pageModel;
        }
        stateInfo = foodInfoBussiness.modifyFoodInfo(recipes);
        if(stateInfo.isState()){
            pageModel.setMessage("更新数据成功");
            pageModel.setStatus(true);
            pageModel.setData(recipes);
        }else {
            pageModel.setMessage("更新数据失败");
        }
        logger.info("访问情况："+pageModel.toString());
        return pageModel;
    }


    @RequestMapping(value="/htm/addFoodInfo.htm")
    @ResponseBody
    public PagerModel addFoodInfo(HttpServletRequest request, Recipes recipes){
        logger.info("url:"+request.getRequestURI());
        logger.info("请求参数为："+recipes.toString());
        if(null == recipes){
            pageModel.setMessage("添加菜品信息读取失败！");
            pageModel.setStatus(false);
            pageModel.setData(recipes);
            return pageModel;
        }
        stateInfo = foodInfoBussiness.addFoodInfo(recipes);
        if( null!= stateInfo){
            pageModel.setStatus(stateInfo.isState());
            pageModel.setMessage(stateInfo.isState()?"添加菜品成功":"添加菜品失败");
            logger.info(stateInfo.getMessage());
        }
        return  pageModel;
    }
    @RequestMapping("/htm/foodInfo.htm")
    public String foodMain(HttpServletRequest request){
        return "foodMain";
    }

    @RequestMapping("/htm/foodInfoData.htm")
    @ResponseBody
    public PageMsg foodAllInfo(HttpServletRequest request){
        logger.info("url:"+request.getRequestURI());

        PageMsg pageMsg = new PageMsg();
        Map<String,Object>  params = resolveParams(request);
        logger.info(params.toString());
        pageMsg.setPageSize((Integer) request.getAttribute("pageSize"));
        pageMsg.setPageNumber((Integer) request.getAttribute("pageNumber"));

        stateInfo = foodInfoBussiness.findFoodInfo(params);
        if(stateInfo.isState()){
            pageMsg.setRows((List<Recipes>) stateInfo.getData());
            pageMsg.setTotal(((List<Recipes>) stateInfo.getData()).size());
        }else {
            pageMsg.setTotal(0L);
        }
        logger.info("访问状态："+pageMsg.toString());
        return  pageMsg;
    }

    private Map<String,Object> resolveParams(HttpServletRequest request) {
        logger.info("解析参数--resolveParams");
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
        if(null != request.getAttribute("lowPrice")){
            Integer lowPrice = Integer.parseInt(new java.text.DecimalFormat("0").format((double)request.getAttribute("lowPrice")));

            if(null != lowPrice ){
                map.put("lowPrice",lowPrice);
            }
        }
        if(null != request.getAttribute("highPrice")) {

            Integer highPrice = Integer.parseInt(new java.text.DecimalFormat("0").format((double) request.getAttribute("highPrice")));
            if (null != highPrice) {
                map.put("highPrice", highPrice);
            }
        }
        return map;
    }

}
