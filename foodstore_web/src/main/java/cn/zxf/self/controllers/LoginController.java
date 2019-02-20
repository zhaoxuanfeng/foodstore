package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.AccountInfoBussiness;
import cn.zxf.self.bussiness.UserInfoBussiness;
import cn.zxf.self.entry.AccountInfo;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.security.VerifyCode;
import cn.zxf.self.utils.JsonModel;
import cn.zxf.self.utils.MD5Untils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        AccountInfo accountInfo = accountInfoBussiness.getAccountInfoByAccountName(accountName);
        String searchClientId = request.getParameter("searchClientId");
        if(accountInfo != null){
            session.setAttribute("accountInfo",accountInfo);
            session.setAttribute("searchClientId",searchClientId);
            UserInfo userInfo = userInfoBussiness.getUserInfoByAccountInfo(accountInfo.getAccountId().toString());
            logger.info("登录用户："+userInfo.getUserName()+",账户："+accountInfo.getAccountName());
            session.setAttribute("userInfo",userInfo);
            jsonModel.setStatus(true);
            jsonModel.setMessage("成功");
            jsonModel.setResult(accountInfo);
        }else{
            jsonModel.setStatus(false);
            jsonModel.setMessage("账号密码错误！");
        }

        return jsonModel;
    }
    
    /***
        *@Description  //TODO  跳转到主页面
        *@Param [request]
        *@Return  java.lang.String
     **/
    @RequestMapping(value="/htm/main.htm")
    public String userMain(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        return  "main";
    }
    
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
    public String registerAccount(AccountInfo accountInfo, UserInfo userInfo){
        boolean flag = false;
        JsonModel jsonModel = new JsonModel();

        accountInfo.setUseFlag(0);

        try {
            accountInfo.setAccountPassword(MD5Untils.getMD5Str(accountInfo.getAccountPassword()));
            userInfo.setUserAccount(accountInfo.getAccountId().toString());
            userInfo.setUserFlag(1);
            accountInfoBussiness.setAccountInfoAndUserInfo(accountInfo,userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(flag){
            jsonModel.setStatus(true);
            jsonModel.setMessage("注册账户"+accountInfo.getAccountName()+"成功");
            logger.info("注册："+accountInfo.getAccountName());
            jsonModel.setResult(accountInfo);
            return "login";
        }else {
            jsonModel.setStatus(false);
            jsonModel.setMessage("注册账户"+accountInfo.getAccountName()+"失败");
            logger.info("注册失败："+accountInfo.getAccountName());
            jsonModel.setResult(accountInfo);
            return "register";

        }


    }

}
