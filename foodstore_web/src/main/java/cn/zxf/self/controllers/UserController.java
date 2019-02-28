package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.UserInfoBussiness;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.entry.dto.StateInfo;
import cn.zxf.self.entry.vo.PageMsg;
import cn.zxf.self.entry.vo.PagerModel;
import cn.zxf.self.entry.vo.UserCondition;
import cn.zxf.self.utils.MD5Utils;
import com.alibaba.fastjson.JSON;
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

    @Autowired
    private UserInfoBussiness  userInfoBussiness;

    @RequestMapping("/htm/userinfo.htm")
    public String  userInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return "userMain";
    }

    @RequestMapping(value = "/htm/userinfo_data.htm")
    @ResponseBody
    public PageMsg userInfoData(HttpServletRequest request, UserCondition userCondition){
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

        return pageMsg;
    }





    @RequestMapping("/htm/modifyUserinfo.htm")
    public  String  modifyUserInfo(HttpServletRequest request,UserInfo  requestUserInfo){
        if (null != requestUserInfo.getAccountPassword() && !requestUserInfo.getAccountPassword().isEmpty()) {
            try {
                requestUserInfo.setAccountPassword(MD5Utils.getMD5Str(requestUserInfo.getAccountPassword()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        StateInfo stateInfo = userInfoBussiness.modifyUser(requestUserInfo);
        if(stateInfo.isState()){
            return "/htm/userinfo_data.htm";
        }
        return "";
    }


}
