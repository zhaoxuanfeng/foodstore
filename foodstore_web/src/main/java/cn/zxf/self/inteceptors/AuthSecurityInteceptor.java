package cn.zxf.self.inteceptors;

import cn.zxf.self.entry.ManageFunc;
import cn.zxf.self.security.PrivilegeManager;
import cn.zxf.self.dto.JsonModel;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName AuthSecurityInteceptors
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/25
 */
@Component
public class AuthSecurityInteceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        System.out.println("111"+requestUri);
        if (request.getSession().getAttribute("rolefunc") != null) {
            List<ManageFunc> funcList = (List<ManageFunc>) request.getSession().getAttribute("rolefunc");
            boolean permit = PrivilegeManager.permit(requestUri, funcList);
            if (permit) {
//                System.out.println("权限认证");
                return true;
            } else {
                JsonModel jsonModel = new JsonModel();
                jsonModel.setMessage("角色访问权限不足");
                jsonModel.setStatus(false);
                request.setAttribute("result", jsonModel);
                response.sendRedirect("/htm/error.htm");

            }

        }
        return false;
    }
}
