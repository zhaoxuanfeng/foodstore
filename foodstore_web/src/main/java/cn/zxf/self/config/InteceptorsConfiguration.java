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


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问

        List<String> urlList = new ArrayList<>();
        urlList.add("/htm/login.htm");
        urlList.add("/htm/register.htm");
        urlList.add("/htm/main.htm");
        urlList.add("/htm/error.htm");
        urlList.add("/htm/verifyCode.htm");
        urlList.add("/htm/menu.htm");
        urlList.add("/htm/loginManagerAccount.htm");
        urlList.add("/htm/self.htm");
        urlList.add("/htm/logout.htm");
//        urlList.add("classpath:/static/**");
        registry.addInterceptor(loginInteceptor).addPathPatterns("/**").excludePathPatterns(urlList);
        registry.addInterceptor(authSecurityInteceptor).addPathPatterns("/**").excludePathPatterns(urlList);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static");

    }
}
