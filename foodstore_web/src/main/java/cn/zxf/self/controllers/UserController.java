package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.UserInfoBussiness;
import cn.zxf.self.entry.ManageRole;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.entry.dto.RestModel;
import cn.zxf.self.entry.dto.StateInfo;
import cn.zxf.self.entry.vo.PageMsg;
import cn.zxf.self.entry.vo.PagerModel;
import cn.zxf.self.entry.vo.UserCondition;
import cn.zxf.self.utils.MD5Utils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/25
 */
@Controller
public class UserController extends BaseController {
    private static  final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfoBussiness  userInfoBussiness;


    @RequestMapping("/htm/manageRoleUsers.htm")
    @ResponseBody
    public RestModel userAllInfo(HttpServletRequest request, Long  manageRoleId){
        logger.info("url:"+request.getRequestURI());

        RestModel restModel = new RestModel();
        List<UserInfo> userInfoList = userInfoBussiness.findUserInfoListByRoleId(manageRoleId);
        if(null != userInfoList && !userInfoList.isEmpty()){
            restModel.setData(userInfoList);
            restModel.setMessage("获取用户信息");
            restModel.setCode("200");
        }
        logger.info("返回用户信息条数:"+userInfoList.size());
        return restModel;
    }


    @RequestMapping("/htm/userinfo.htm")
    public String  userInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return "userMain";
    }


    @RequestMapping(value = "/htm/userinfo_data.htm")
    @ResponseBody
    public PageMsg userInfoData(HttpServletRequest request, UserCondition userCondition){
        logger.info("url:"+request.getRequestURI());
        logger.info("条件："+userCondition.toString());
        PageMsg pageMsg = new PageMsg();
        StateInfo stateInfo = userInfoBussiness.getUserInfoByCondition(userCondition);
        List<UserInfo>  userInfoList = new ArrayList<>();
        if(stateInfo.isState()){
            userInfoList = (List<UserInfo>) stateInfo.getData();
        }
        pageMsg.setRows(userInfoList);
        pageMsg.setTotal((long) userInfoList.size());
        pageMsg.setPageSize(20);
        pageMsg.setPageNumber(userCondition.getPage_number());
        logger.info("返回数据条数："+userInfoList.size());
        logger.info("返回状态"+stateInfo.isState());
        return pageMsg;
    }







    @RequestMapping("/htm/modifyUserinfo.htm")
    public  String  modifyUserInfo(HttpServletRequest request,UserInfo  requestUserInfo){
        logger.info("url:"+request.getRequestURI());
        logger.info("入参："+requestUserInfo.toString());
        if (null != requestUserInfo.getAccountPassword() && !requestUserInfo.getAccountPassword().isEmpty()) {
            try {
                requestUserInfo.setAccountPassword(MD5Utils.getMD5Str(requestUserInfo.getAccountPassword()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        StateInfo stateInfo = userInfoBussiness.modifyUser(requestUserInfo);
        logger.info("返回状态:"+stateInfo.isState());
        if(stateInfo.isState()){
            return "/htm/userinfo_data.htm";
        }
        return "";
    }


}
