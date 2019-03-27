package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.FoodInfoBussiness;
import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.vo.PagerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "/view/")
    public PagerModel mainViewData(HttpServletRequest request, HttpSession session){
        logger.info(request.getRequestURI());
        

        return pagerModel;
    }

}
