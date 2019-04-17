package cn.zxf.self.config;
import java.io.FileWriter;
import java.io.IOException;
/**
 * @ClassName AlipayConfig
 * @Description TODO
 * @Author zxf
 * @DATE 2019/4/16
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091400513252";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "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";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "支付宝公钥\r\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnWDRBMU6mKCxuH+RV9nYqjiZamTG7bjKDVjmSlMV0SNBdUWw2J8sYFvbrrOw1h6HbxKKU4Y0FaGncFvrbVlZjw0R1kDcW1LHTaB4RNzBFqSWzq32TPAUnF4XE9SFAImr7V1wU3fUS7cMOltWBTp3v34LVAkqFQK3A2b47nqArKBcg1HJfBNLVoARhsYotXpcaoqi5z6gMmsDIvSn7UjDN5lmIRI8GkB/9vz3UEHb8zZ4xLKHaF4e168Ou1oxtwkLGA+Zg9CVWDNycuDuamwHFPANTvMVNoPkNAhKVgxsfdw/vRV07graTqupBZMx8+I1ehijWN9yfyoXQluiGCWmiQIDAQAB/QQARkHQpb2gmaBHAwpik15nc4AzvL7DY6Lyi0pF+BRCmzmzIQT7ujC6bYh+IQnfZYc75pZg/h77cDPVKAjtLdXeyAKRkzlApLc18DBpXYAE6nDb6irF/2mviHWXq8dlBA3I7lAgCz/09bhA+q3dMSpiiwwYnOywSBXT83GTAMWb4VVtmse3fXErmvxo4WR6l+N9RwliMAB0Hs3RM59cE5PQIDAQAB/QQARkHQpb2gmaBHAwpik15nc4AzvL7DY6Lyi0pF+BRCmzmzIQT7ujC6bYh+IQnfZYc75pZg/h77cDPVKAjtLdXeyAKRkzlApLc18DBpXYAE6nDb6irF/2mviHWXq8dlBA3I7lAgCz/09bhA+q3dMSpiiwwYnOywSBXT83GTAMWb4VVtmse3fXErmvxo4WR6l+N9RwliMAB0Hs3RM59cE5PQIDAQAB+EGNVKEkRWxJ2wfMU2MkrpxTYgF2Kb2wF+pAJB0JoRvMuR2q4atZ1S59A2m7ccCEsHA4IYAIDgW3/wg+7Ma2Hspjv6ei4J0JUy4A/lXuCz6xeZ56dkC0l3xQU8jFTG39tcQndNjrg32GBtKF5zluej0IUk3mW0zMIIVTrsLxleXvCAytVzDpoRNLfzsg5IBvIOGscfOPX/OrpHgtnWb7yyZWOOxQM2sJThE89fse70JNSJ5iITfmesalZzV2NSNVTAIDCDhHOfsU/OWKYhMwBcne/QI0lBakxZmHD4YLzu7W79HvBBaTDF/QwwXwiXatUi6KDSkqdJwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8888/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8888/BookStore/client/cart/pay_success.do";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
