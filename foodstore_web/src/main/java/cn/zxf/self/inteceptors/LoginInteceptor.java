package cn.zxf.self.inteceptors;

import cn.zxf.self.entry.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInteceptors
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/25
 */

@Component
public class LoginInteceptor implements HandlerInterceptor {

    private String[] uris = {"/htm/login.htm", "/htm/register.htm", "/htm/main.htm",
            "/htm/error.htm", "/htm/verifyCode.htm", "/htm/menu.htm",
            "/htm/loginManagerAccount.htm", "/htm/self.htm", "/htm/logout.htm"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*String requestUri = request.getRequestURI();
        System.out.println(requestUri);
        for (String uri : uris) {
            if (uri.equals(requestUri)) {
                return true;
            }
        }*/
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
        if( userInfo != null && !userInfo.getUserId().toString().isEmpty()){
            return true;
        }

        response.sendRedirect("/htm/login.htm");
        return false;
    }

}
