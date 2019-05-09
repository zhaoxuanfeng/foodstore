package cn.zxf.self.filters;

import cn.zxf.self.entry.ManageFunc;
import cn.zxf.self.security.PrivilegeManager;
import cn.zxf.self.dto.JsonModel;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SecurityFilter
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/25
 */
//@Component
//@Order(1)
//@WebFilter(filterName = "roleFilter", urlPatterns = "/*.htm")
public class SecurityFilter  implements Filter {

    private String[] notFilterUri = {"/htm/login.htm", "/htm/register.htm", "/htm/main.htm",
            "/htm/error.htm", "/htm/verifyCode.htm", "/htm/menu.htm",
            "/htm/loginManagerAccount.htm", "/htm/self.htm", "/htm/logout.htm"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = request.getRequestURI();
         /*System.out.println(requestUri);

        for (String uri : notFilterUri) {
            if (uri.equals(requestUri)) {
                filterChain.doFilter(request, response);
            }
        }*/
        if (request.getSession().getAttribute("rolefunc") != null) {
            List<ManageFunc> funcList = (List<ManageFunc>) request.getSession().getAttribute("rolefunc");
            boolean permit = PrivilegeManager.permit(requestUri, funcList);
            if (permit) {
                filterChain.doFilter(request, response);
            } else {
                JsonModel jsonModel = new JsonModel();
                jsonModel.setMessage("权限不足");
                jsonModel.setStatus(false);
                request.setAttribute("result", jsonModel);
                response.sendRedirect("/htm/error.htm");
            }
        }


    }

    @Override
    public void destroy() {

    }
}
