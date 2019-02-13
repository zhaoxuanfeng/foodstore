package cn.zxf.self.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @ClassName cn.zxf.self.test.JDBCtest
 * @Description TODO
 * @Author zxf
 * @DATE 2019/1/30
 */
public class JDBCtest {


    private static final Logger logger =   LoggerFactory.getLogger(JDBCtest.class);
    public  void  select() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodstore?characterEncoding=utf8&useSSL=false"
                                                    ,"root"
                                                    ,"123");
        Statement preparedStatement = conn.createStatement();
        boolean result = preparedStatement.execute("select * from user_info ");
        System.out.println(result);
        conn.close();
        logger.info("连接数据库成功。");

    }

    public static void main(String[] args){
        JDBCtest  jdbd = new JDBCtest();
        try {
            jdbd.select();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
