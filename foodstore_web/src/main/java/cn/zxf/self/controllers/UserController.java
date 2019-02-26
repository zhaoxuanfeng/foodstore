package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.UserInfoBussiness;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.entry.vo.PagerModel;
import cn.zxf.self.entry.vo.UserCondition;
import cn.zxf.self.utils.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/25
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserInfoBussiness  userInfoBussiness;

    @RequestMapping("/htm/userinfo.htm")
    public String  userInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return "userMain";
    }

    @RequestMapping("/htm/userinfo_data.htm")
    @ResponseBody
    public PagerModel<List<UserInfo>> userInfoData(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserCondition userCondition){

        PagerModel<List<UserInfo>>  pagerModel = new PagerModel<>();
        List<UserInfo>   userInfoList = userInfoBussiness.getUserInfoByCondition(userCondition);
        pagerModel.setPageData(userInfoList);
        request.setAttribute("pager",pagerModel);
        return pagerModel;
    }



}
