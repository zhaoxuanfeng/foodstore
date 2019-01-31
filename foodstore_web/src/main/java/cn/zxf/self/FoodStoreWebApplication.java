package cn.zxf.self;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName FoodStoreWebApplication
 * @Description TODO
 * @Author zxf
 * @DATE 2019/1/31
 */
@SpringBootApplication
@MapperScan("cn.zxf.self.mapper")
@ComponentScan(basePackages = "cn.zxf.self")
public class FoodStoreWebApplication {
    public static void main(String[] args){

        SpringApplication.run(FoodStoreWebApplication.class,args);
    }
}
