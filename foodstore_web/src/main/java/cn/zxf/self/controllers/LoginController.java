package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.AccountInfoBussiness;
import cn.zxf.self.bussiness.UserInfoBussiness;
import cn.zxf.self.entry.AccountInfo;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.entry.dto.StateInfo;
import cn.zxf.self.entry.vo.RegisterUser;
import cn.zxf.self.security.VerifyCode;
import cn.zxf.self.utils.JsonModel;
import cn.zxf.self.utils.MD5Untils;
import cn.zxf.self.utils.RestModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/13
 */
@Controller
public class LoginController extends  BaseController{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AccountInfoBussiness accountInfoBussiness;

    @Autowired
    private UserInfoBussiness userInfoBussiness;

    /***
        *@Description  //TODO  跳转至登录页面
        *@Param [session, request, response]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/login.htm")
    public String  login(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
    @RequestMapping(value="/htm/register.htm")
    public String registerUser(HttpSession session,HttpServletRequest request,HttpServletResponse response){return "register";}

    
    /***
        *@Description  //TODO  获取验证码
        *@Param [session, request, response]
        *@Return  void
     **/
    @RequestMapping(value="/htm/verifyCode.htm")
    public void verifyCode(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        String verifyCode = VerifyCode.outputVerifyImage(65,25,response.getOutputStream());
        session.setAttribute("verifyCode",verifyCode);
    }

    /***
        *@Description  //TODO  账号登录信息
        *@Param [request, session]
        *@Return  cn.zxf.self.utils.JsonModel
     **/
    @RequestMapping(value="/htm/loginManagerAccount.htm")
    @ResponseBody
    public JsonModel loginManagerAccount(HttpServletRequest request ,HttpSession session,HttpServletResponse response){
        JsonModel jsonModel = new JsonModel();

        String accountName = request.getParameter("accountName");
        String accountPassword = request.getParameter("accountPassword");

        try {
            String password = MD5Untils.getMD5Str(accountPassword);

            AccountInfo accountInfo = accountInfoBussiness.getAccountInfoByAccountName(accountName,password);
    //        String searchClientId = request.getParameter("searchClientId");
            if(accountInfo != null){
                session.setAttribute("accountInfo",accountInfo);
    //            session.setAttribute("searchClientId",searchClientId);
                UserInfo userInfo = userInfoBussiness.getUserInfoByAccountInfo(accountInfo.getAccountId());
                logger.info("登录用户："+userInfo.getUserName()+",账户："+accountInfo.getAccountName());
                session.setAttribute("userInfo",userInfo);
                jsonModel.setStatus(true);
                jsonModel.setMessage("成功");
                jsonModel.setResult(accountInfo);
            }else{
                jsonModel.setStatus(false);
                jsonModel.setMessage("账号密码错误！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonModel;
    }
    
    /***
        *@Description  //TODO  跳转到主页面
        *@Param [request]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/main.htm")
    public String userMain(HttpServletRequest request){
        return  "main";
    }
/**
    //动态加载
    @RequestMapping(value="/htm/menu.htm", method=RequestMethod.GET)
    @ResponseBody
    public RestModel menu(HttpSession session, HttpServletRequest request, HttpServletResponse response)
    {
        UserInfo user = (UserInfo)session.getAttribute("userInfo");

        String clientId = request.getParameter("clientId");
        Long manageSystemId = Long.valueOf("100000000000");  //100000000000  105000000000

        // 生成菜单
        JSONArray menu = new JSONArray();
        List<ManageFunc> listFunc = manageFunBusiness.getManageFuncByManageUserId(user.getUserId());
//        ManageSystemModel manageSystemModel = manageSystemBusiness.getManageSystemByClientId(clientId);
//        ManageSystemModel manageSystemModel = manageSystemBusiness.getManageSystemByClientId(searchClientId);
        if (!listFunc.isEmpty()) {
            // 循环主菜单
            for (ManageFunc mainmenu : listFunc) {
                if (mainmenu.getParentId().equals(manageSystemId)) {
                    JSONObject menuObj = new JSONObject();
                    menuObj.put("funcName", mainmenu.getFuncName());
                    JSONArray subMenu = new JSONArray();
                    // 循环子菜单
                    for (ManageFunc submenu : listFunc) {
                        if (submenu.getParentId().equals(mainmenu.getManageFuncId())) {
                            JSONObject subMenuObj = new JSONObject();
                            subMenuObj.put("url", submenu.getUrl());
                            subMenuObj.put("funcName", submenu.getFuncName());
                            subMenu.add(subMenuObj);
                        }
                    }
                    if (!subMenu.isEmpty()) {
                        menuObj.put("subMenu", subMenu);
                    }
                    menu.add(menuObj);
                }
            }
        }
        return new RestModel(menu);
    }
    **/
    /***
        *@Description  //TODO  跳转个人页面
        *@Param [session]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/self.htm")
    public String userSelf(HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
        JsonModel jsonModel = new JsonModel();

        if(ObjectUtils.allNotNull(userInfo)){
            jsonModel.setStatus(true);
            jsonModel.setResult(userInfo);
            jsonModel.setMessage("跳转个人页面");
            return "self";
        }else{
            jsonModel.setStatus(false);
            jsonModel.setMessage("未找到个人信息");
            return "main";
        }


    }

    /***
        *@Description  //TODO  退出登录
        *@Param [session, request, response]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/logout.htm")
    public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        session.removeAttribute("accountInfo");
        session.removeAttribute("userInfo");
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
                if("autologin".equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
        }
        return "main";
    }

    /***
        *@Description  //TODO  注册功能的实现
        *@Param [accountInfo, userInfo]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/registerUser.htm")
    @ResponseBody
    public JsonModel registerAccount(RegisterUser registerUser){
        StateInfo stateInfo = new StateInfo();
        JsonModel jsonModel = new JsonModel();
        AccountInfo accountInfo = new AccountInfo();
        UserInfo userInfo = new UserInfo();

        accountInfo.setAccountName(registerUser.getAccountName());
        userInfo.setUserName(registerUser.getName());
        userInfo.setUserAddress(registerUser.getAddress());
        userInfo.setUserBirthday(registerUser.getBirthday());
        userInfo.setUserPhone(registerUser.getPhone());
        userInfo.setUserRealname(registerUser.getRealname());
        userInfo.setUserSex(registerUser.getSex());


        try {
            Date  date = new Date();
            String accountid = UUID.randomUUID().toString()+MD5Untils.getMD5Str(accountInfo.getAccountName());
            String userid = UUID.randomUUID().toString()+MD5Untils.getMD5Str(date.toString());
            accountInfo.setUserId(userid);
            userInfo.setUserId(userid);
            accountInfo.setAccountId(accountid);
            userInfo.setUserAccount(accountid);
            accountInfo.setUseFlag(0);
            accountInfo.setAccountPassword(MD5Untils.getMD5Str(registerUser.getAccountPassword()));
//            userInfo.setUserAccount(accountInfo.getAccountId().toString());
            userInfo.setUserFlag(1);
            stateInfo = accountInfoBussiness.setAccountInfoAndUserInfo(accountInfo,userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(stateInfo.isState()){
            jsonModel.setStatus(true);
            jsonModel.setMessage("注册账户"+accountInfo.getAccountName()+"成功");
            logger.info("注册："+accountInfo.getAccountName());
            jsonModel.setResult(accountInfo);
            return jsonModel;
        }else {
            jsonModel.setStatus(false);
            jsonModel.setMessage("注册账户"+accountInfo.getAccountName()+"失败");
            logger.info("注册失败："+accountInfo.getAccountName());
            jsonModel.setResult(accountInfo);
            return jsonModel;

        }


    }

}
