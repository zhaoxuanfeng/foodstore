package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.ManageFunBusiness;
import cn.zxf.self.bussiness.UserInfoBussiness;
import cn.zxf.self.entry.ManageFunc;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.utils.DateUtils;
import cn.zxf.self.utils.MailUtils;
import cn.zxf.self.vo.RegisterUser;
import cn.zxf.self.security.VerifyCode;
import cn.zxf.self.dto.JsonModel;
import cn.zxf.self.utils.MD5Utils;
import cn.zxf.self.dto.RestModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/13
 */
@Controller
public class LoginController extends  BaseController{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static  JsonModel jsonModel = new JsonModel();
    @Autowired
    private UserInfoBussiness userInfoBussiness;

    @Autowired
    private ManageFunBusiness manageFunBusiness;
    /***
        *@Description  //TODO  跳转至登录页面
        *@Param [session, request, response]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/login.htm")
    public String  login(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        return "backstage/login";
    }
    @RequestMapping(value="/htm/register.htm")
    public String registerUser(HttpSession session,HttpServletRequest request,HttpServletResponse response){return "backstage/register";}

    
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
        *@Return  cn.zxf.self.dto.JsonModel
     **/
    @RequestMapping(value="/htm/loginManagerAccount.htm")
    @ResponseBody
    public JsonModel loginManagerAccount(HttpServletRequest request ,HttpSession session,HttpServletResponse response){
        String verifyCode = request.getParameter("verifyCode");
        logger.info("url:/htm/loginManagerAccount.htm");
        logger.info("验证码："+verifyCode);
        if(null !=  verifyCode && verifyCode.equals((String)session.getAttribute("verifyCode")) ){
            String accountName = request.getParameter("accountName");
            String accountPassword = request.getParameter("accountPassword");
//            Integer userFlag = (Integer) request.getAttribute("userFlag");
            try {
                String password = MD5Utils.getMD5Str(accountPassword);
                UserInfo userInfo = userInfoBussiness.getUserInfoByAccountInfo(accountName, password);
                //        String searchClientId = request.getParameter("searchClientId");
                if (userInfo != null) {
                    session.setAttribute("userInfo", userInfo);
                    response.addCookie(new Cookie("userId",(userInfo.getUserId()).toString()));
                    response.addCookie(new Cookie("userFlag",(userInfo.getUserFlag()).toString()));
                    response.addCookie(new Cookie("accountName",userInfo.getAccountName()));
                    Cookie cookie = new Cookie("loginFlag","1");
                    cookie.setMaxAge(60*24*60*7);
                    cookie.setVersion(DateUtils.getCurrMilli().intValue());
                    response.addCookie(cookie);
                    logger.info("登录用户：" + userInfo.getUserName() + ",账户：" + userInfo.getAccountName());
                    jsonModel.setStatus(true);
                    jsonModel.setMessage("成功");
                    jsonModel.setResult(userInfo);
                } else {
                    jsonModel.setStatus(false);
                    jsonModel.setMessage("账号密码错误！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            jsonModel.setStatus(false);
            jsonModel.setMessage("验证码错误！");
        }
        logger.info("返回信息:"+jsonModel.toString());
        return jsonModel;
    }

    @RequestMapping("/htm/userlogin.action")
    public String userLogin(HttpServletRequest request){
       return "login";
    }

    /***
        *@Description  //TODO  跳转到主页面
        *@Param [request]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/main.htm")
    public String userMain(HttpServletRequest request){
        return  "backstage/main";
    }

    //动态加载
//    @RequestMapping(value="/htm/menu.htm", method=RequestMethod.GET)
    @GetMapping("/htm/menu.htm")
    @ResponseBody
    public RestModel menu(HttpSession session, HttpServletRequest request, HttpServletResponse response)
    {
        UserInfo user = (UserInfo)session.getAttribute("userInfo");

//        String clientId = request.getParameter("clientId");
        Long manageSystemId = Long.valueOf("100100");//100100

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
        session.setAttribute("rolefunc",listFunc);
        return new RestModel(menu);
    }

    /***
        *@Description  //TODO  跳转个人页面
        *@Param [session]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/self.htm")
    public String userSelf(HttpSession session){
        logger.info("url:/htm/self.htm");
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");

        if(ObjectUtils.allNotNull(userInfo)){
            jsonModel.setStatus(true);
            jsonModel.setResult(userInfo);
            jsonModel.setMessage("跳转个人页面");
            return "/backstage/self";
        }else{
            jsonModel.setStatus(false);
            jsonModel.setMessage("未找到个人信息");
            logger.info("返回信息："+jsonModel.toString());

        }
        return "backstage/main";
    }

    /***
        *@Description  //TODO  退出登录
        *@Param [session, request, response]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/logout.htm")
    public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        logger.info("url:"+request.getRequestURI());
        logger.info("登出用户："+session.getAttribute("userInfo").toString());
        session.removeAttribute("userInfo");
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
                if("autologin".equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
        }
        return "/";
    }

    /***
        *@Description  //TODO  注册功能的实现
        *@Param [accountInfo, userInfo]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/registerUser.htm")
    @ResponseBody
    public JsonModel registerAccount(RegisterUser registerUser,HttpServletRequest request){
        logger.info("url:"+request.getRequestURI());
        StateInfo stateInfo = new StateInfo();

        try {
            UserInfo userInfo = userInfoBussiness.getUserInfoByAccountName(registerUser.getAccountName());
            if(!ObjectUtils.allNotNull(userInfo)) {
                String password = MD5Utils.getMD5Str(registerUser.getAccountPassword());
                registerUser.setAccountPassword(password);
                long time=-1800;
                Date create_time = new Date(time);
                userInfo = new UserInfo();
                userInfo.setUserFlag(0);
                userInfo.setUserName(registerUser.getName());
                userInfo.setUserAddress(registerUser.getAddress());
                userInfo.setUserBirthday(registerUser.getBirthday());
                userInfo.setUserPhone(registerUser.getPhone());
                userInfo.setUserRealname(registerUser.getRealname());
                userInfo.setUserSex(registerUser.getSex());
                userInfo.setUserEmail(registerUser.getEmail());
                userInfo.setCreateTime(create_time);
                userInfo.setAccountName(registerUser.getAccountName());
                userInfo.setAccountPassword(password);
                stateInfo = userInfoBussiness.setUserInfo(userInfo);
            }else{
                jsonModel.setStatus(false);
                jsonModel.setMessage("用户已注册");

            }
            String emialMsg = "您好：<br/> 您已注册笑脸餐饮管理系统！激活<a href='http://localhost:8888/htm/registerMail.htm?useId="+
                    stateInfo.getData()
                    +"'>点击http://localhost:8888/htm/registerMail.htm</a>";
            MailUtils.sendMail(registerUser.getEmail(),emialMsg);
            if(stateInfo.isState()){
                jsonModel.setStatus(true);
                jsonModel.setMessage("注册账户"+registerUser.getAccountName()+"成功");
                logger.info("注册："+registerUser.getAccountName());
                jsonModel.setResult(registerUser);

            }else {
                jsonModel.setStatus(false);
                jsonModel.setMessage("注册账户"+registerUser.getAccountName()+"失败");
                logger.info("注册失败："+registerUser.getAccountName());
                jsonModel.setResult(registerUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonModel;
    }

    @RequestMapping(value="/htm/registerPage.htm")
    public String redirect(HttpServletRequest request){
        return "backstage/register";
    }

    @RequestMapping("/htm/registerMail.htm")
    public String registerMail(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        Boolean flag = userInfoBussiness.updateUserStatusFlag(userId);
        if(flag){
            return "login";
        }else {
            return "error";
        }
    }
}
