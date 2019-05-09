package cn.zxf.self.config;

import cn.zxf.self.inteceptors.AuthSecurityInteceptor;
import cn.zxf.self.inteceptors.LoginInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName InteceptorsConfiguration
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/25
 */
//@Configuration
public class InteceptorsConfiguration implements WebMvcConfigurer {


    @Autowired
    private LoginInteceptor loginInteceptor;

    @Autowired
    private AuthSecurityInteceptor authSecurityInteceptor;

  /*  private String[] excludeUrl = {"/htm/login.htm","/htm/register.htm","/htm/error.htm",
                                    "/htm/verifyCode.htm","/htm/loginManagerAccount.htm",
            "/htm/logout.htm","/htm/registerMail.htm","/htm/registerPage.htm","/htm/userlogin.action",
            "/","/js/**","/img/**","/easyui/**","/css/**"};*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问

     /*   List<String> excludeUrlList = new ArrayList<>();
        excludeUrlList.add("/htm/login.htm");
        excludeUrlList.add("/htm/register.htm");
        excludeUrlList.add("/htm/error.htm");
        excludeUrlList.add("/htm/verifyCode.htm");
        excludeUrlList.add("/htm/loginManagerAccount.htm");
        excludeUrlList.add("/htm/logout.htm");
        excludeUrlList.add("/htm/registerMail.htm");
        excludeUrlList.add("/htm/registerPage.htm");
        excludeUrlList.add("/htm/userlogin.action");
        excludeUrlList.add("/");*/
      /*  excludeUrlList.add("/js/**");
        excludeUrlList.add("/easyui/**");
        excludeUrlList.add("/img/**");
        excludeUrlList.add("/css/**");
        excludeUrlList.add("/static/**");

//        List<String> excludeUrlList = Arrays.asList(excludeUrl);
//        urlList.add("classpath:/static/**");
        registry.addInterceptor(loginInteceptor).addPathPatterns("/**.htm","/**.action").excludePathPatterns(excludeUrlList);
        excludeUrlList.add("/htm/menu.htm");
        excludeUrlList.add("/htm/main.htm");
        excludeUrlList.add("/htm/self.htm");
        registry.addInterceptor(authSecurityInteceptor).addPathPatterns("/**.htm").excludePathPatterns(excludeUrlList);
    */
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static");

    }
}
