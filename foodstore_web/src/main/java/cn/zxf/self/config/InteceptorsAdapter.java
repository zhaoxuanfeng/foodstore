package cn.zxf.self.config;

import cn.zxf.self.inteceptors.AuthSecurityInteceptor;
import cn.zxf.self.inteceptors.LoginInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName InteceptorsAdapter
 * @Description TODO
 * @Author zxf
 * @DATE 2019/5/9
 */
@Configuration
public class InteceptorsAdapter implements WebMvcConfigurer {

    @Autowired
    private LoginInteceptor loginInteceptor;

    @Autowired
    private AuthSecurityInteceptor authSecurityInteceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeUrlList = new ArrayList<>();
        excludeUrlList.add("/htm/login.htm");
        excludeUrlList.add("/htm/register.htm");
        excludeUrlList.add("/htm/error.htm");
        excludeUrlList.add("/htm/verifyCode.htm");
        excludeUrlList.add("/htm/loginManagerAccount.htm");
        excludeUrlList.add("/htm/logout.htm");
        excludeUrlList.add("/htm/registerMail.htm");
        excludeUrlList.add("/htm/registerPage.htm");
        excludeUrlList.add("/htm/userlogin.action");
        excludeUrlList.add("/");
      /*  excludeUrlList.add("/js/**");
        excludeUrlList.add("/easyui/**");
        excludeUrlList.add("/img/**");
        excludeUrlList.add("/css/**");*/
        excludeUrlList.add("/static/**");

//        List<String> excludeUrlList = Arrays.asList(excludeUrl);
//        urlList.add("classpath:/static/**");
        registry.addInterceptor(loginInteceptor).addPathPatterns("/**.htm","/**.action").excludePathPatterns(excludeUrlList);
        excludeUrlList.add("/htm/menu.htm");
        excludeUrlList.add("/htm/main.htm");
        excludeUrlList.add("/htm/self.htm");
        registry.addInterceptor(authSecurityInteceptor).addPathPatterns("/**.htm").excludePathPatterns(excludeUrlList);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("**").addResourceLocations("classpath:/static");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/tempaltes/");

    }
}
