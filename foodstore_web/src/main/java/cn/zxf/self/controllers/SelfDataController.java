package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.vo.PageMsg;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SeleDataController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/4/4
 */
@Controller
public class SelfDataController {

    private static  final Logger logger = LoggerFactory.getLogger(SelfDataController.class);

    @Autowired
    private OrderInfoBussiness orderInfoBussiness;



    @RequestMapping("/self/tolleyData.action")
    @ResponseBody
    public PageMsg selfTolleyData(HttpServletRequest request,HttpSession session){
        System.out.println("1111111111111111");
        logger.info("url : " + request.getRequestURI());
        PageMsg pageMsg = new PageMsg();
        UserInfo currUser = (UserInfo) session.getAttribute("userInfo");
        if(ObjectUtils.allNotNull(currUser)){
            logger.info(currUser.getUserId().toString());
            StateInfo stateInfo = orderInfoBussiness.findRecipesByUser(currUser.getUserId(),null,null);
            logger.info(stateInfo.getData().toString());
            if(ObjectUtils.allNotNull(stateInfo)){
                List<Recipes> rowData  =  ((List<Map<String,List<Recipes>>>)stateInfo.getData()).get(0).get("recipes");
                logger.info("rowData --:"+rowData);
                pageMsg.setRows(rowData);
                pageMsg.setTotal(((List<Recipes>) stateInfo.getData()).size());
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
}
