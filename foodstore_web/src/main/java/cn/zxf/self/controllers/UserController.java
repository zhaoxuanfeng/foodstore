package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.UserInfoBussiness;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.dto.RestModel;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.vo.PageMsg;
import cn.zxf.self.vo.PagerModel;
import cn.zxf.self.vo.UserCondition;
import cn.zxf.self.utils.MD5Utils;
import org.apache.catalina.User;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private  StateInfo stateInfo = new StateInfo();

    private   PagerModel pagerModel = new PagerModel();

    @Autowired
    private UserInfoBussiness  userInfoBussiness;

    @RequestMapping(value = "/htm/modifyUserStatus.htm")
    @ResponseBody
    public PagerModel modifyUserStatus(HttpServletRequest request, Long userId , Integer userFlag){
        UserInfo requestUserInfo = new UserInfo();
        requestUserInfo.setUserId(userId);
        requestUserInfo.setUserFlag(userFlag);
        logger.info("url:"+request.getRequestURI());
        if(!ObjectUtils.allNotNull(requestUserInfo)){
            pagerModel.setStatus(false);
            pagerModel.setMessage("传入参数为空");
            pagerModel.setData("400");
            return pagerModel;
        }
        logger.info("params:"+requestUserInfo.toString());

        stateInfo  = userInfoBussiness.modifyUser(requestUserInfo);
        if(stateInfo.isState()){
            pagerModel.setStatus(true);
            pagerModel.setMessage("更新成功");
            pagerModel.setData("200");
        }else{
            pagerModel.setStatus(false);
            pagerModel.setMessage("更新失败");
            pagerModel.setData("400");
        }
        logger.info("return :" + pagerModel.getMessage()+",status:"+pagerModel.isStatus());
        return pagerModel;
    }


    /***
        *@Description  //TODO  初始化密码
        *@Param [request, userIdList]
        *@Return  cn.zxf.self.vo.PagerModel
     **/
    @RequestMapping(value= "/htm/resetUserPassword.htm")
    @ResponseBody
    public PagerModel  resetUserPassword(HttpServletRequest request,@RequestBody List<Long>  userIdList){

        logger.info("url:"+request.getRequestURI());
        if(!ObjectUtils.allNotNull(userIdList)){
            pagerModel.setMessage("没有选中用户！");
            pagerModel.setStatus(false);
            return pagerModel;
        }
        logger.info("params:"+userIdList.toString());
        try {
            String initPassword = MD5Utils.getMD5Str("123456");
            stateInfo = userInfoBussiness.initUserPassword(userIdList,initPassword);
            pagerModel.setMessage(stateInfo.getMessage());
            pagerModel.setStatus(stateInfo.isState());
            if(stateInfo.isState()){
                pagerModel.setData("123456");
            }
        } catch (Exception e) {
            pagerModel.setMessage("初始化密码异常！");
            pagerModel.setStatus(false);
        }finally {
            logger.info("return :" + pagerModel.toString());
        }
            return pagerModel;
    }


    /***
        *@Description  //TODO  获取角色所属用户
        *@Param [request, manageRoleId]
        *@Return  cn.zxf.self.dto.RestModel
     **/
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

    /***
        *@Description  //TODO  跳转用户页面
        *@Param [request, response, session]
        *@Return  java.lang.String
     **/
    @RequestMapping("/htm/userinfo.htm")
    public String  userInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return "backstage/userMain";
    }

    /***
        *@Description  //TODO  条件查询用户信息
        *@Param [request, userCondition]
        *@Return  cn.zxf.self.vo.PageMsg
     **/
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

    /***
        *@Description  //TODO  修改用户信息
        *@Param [request, requestUserInfo]
        *@Return  java.lang.String
     **/
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
            return "htm/userinfo_data.htm";
        }
        return "";
    }


}
