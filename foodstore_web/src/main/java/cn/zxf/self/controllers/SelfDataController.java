package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.vo.PageMsg;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    PageMsg pageMsg = new PageMsg();

    @RequestMapping(value = "/self/tolleyData.htm")
    @ResponseBody
    public PageMsg selfTolleyData(HttpServletRequest request , HttpSession session){


        return pageMsg;
    }
}
