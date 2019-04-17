package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.OrderInfoBussiness;
import cn.zxf.self.config.AlipayConfig;
import cn.zxf.self.entry.Orders;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.vo.PagerModel;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @ClassName OrderController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/20
 */
@Controller
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String Prefix="rabbit";

    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderInfoBussiness orderInfoBussiness;

    private  PagerModel pagerModel = new PagerModel();

    @RequestMapping(value="/user/addOrder.htm")
    public PagerModel addOrder(Long userId, List<Recipes> recipesList){
        /*
            //逻辑未完成
            1.添加订单信息到表
            2.返回订单信息到队列
        */
        //消息
        byte[] message = "message".getBytes();

        logger.info("订单加入未完成队列");
        rabbitTemplate.setExchange(env.getProperty("basic.info.mq.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("basic.info.mq.routing.key.name"));
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        Message msg=MessageBuilder.withBody(message).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
        rabbitTemplate.convertAndSend(msg);
        logger.info("订单加入队列");
        return pagerModel;
    }


    @RequestMapping("/htm/download.htm")
    public void downloadProduct(String year , String month,Long customerId, HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException {

//        List<ProductList> productList =   adminProductService.findProductList(year,month);
        List<Orders>  ordersList = orderInfoBussiness.findOrderByList(year,month,customerId);
        String monthName,yearName ;
        if("0".equals(month)) {
            monthName = "总订单信息" ;
        }else {
            monthName =  month + "月订单信息";
        }
        if("".equals(year)) {
            yearName = "2018年";
        }else {
            yearName = year + "年";
        }


        String fileName = yearName + "_"+monthName+".lsx";
        String sheetName = monthName ;
        String titleName = yearName +"年"+month ;
        String[] tableHead = {"订单ID","用户id","菜品数量","订单日期","价格","地址","创建时间","结束时间","是否删除"};

        int columnNum = 2;
        String[][] dataList = new String[ordersList.size()][columnNum];
        for(int i = 0;i < ordersList.size();i++){
            Orders pl = ordersList.get(i);

            dataList[i][0] = pl.getOrderId().toString();
            dataList[i][1] = pl.getUserId().toString();
            dataList[i][3] = pl.getCount().toString();
            dataList[i][4] = pl.getOrderDate().toString();
            dataList[i][5] = pl.getRealPrice().toString();
            dataList[i][6] = pl.getOrderAddress();
            dataList[i][7] = pl.getCreateTime().toString();
            dataList[i][8] = pl.getEndTime().toString();
            dataList[i][9] = pl.getIsDelete().toString();
        }

        //对应的excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //在wb中创建一个sheet
        HSSFSheet sheet =  wb.createSheet(sheetName);
        //创建第一行
        HSSFRow row1 = sheet.createRow(0);
        //创建第一行的第一个单元格
        HSSFCell cell1 = row1.createCell(0);
        //合并第一行的单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        //给第一行第一个单元格添加内容
        cell1.setCellValue(titleName);

        //创建第二行
        HSSFRow row = sheet.createRow(1);
        for(int i = 0; i < columnNum; i++){
            row.createCell(i).setCellValue(tableHead[i]);
        }

        //创建内容行
        for(int i = 0;i < ordersList.size();i++){
            row = sheet.createRow(i+2);
            for (int j = 0; j < columnNum; j++) {
                row.createCell(j).setCellValue(dataList[i][j]);
            }

        }

        //wb写入字节流
        OutputStream out = response.getOutputStream();
        wb.write(out);
        //excel文件下载
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+processFileName(request, fileName));


		/*
		 *
		 * CSV 文件存储
		List<ProductList> productList =  adminProductService.findProductList(year,month);
		String filename = year +"_"+month+"_"+"销售表单.csv";
		filename = processFileName(request, filename);
		response.setContentType(session.getServletContext().getMimeType(filename));
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		response.setCharacterEncoding("gbk");

		PrintWriter out= response.getWriter();
		out.println("商品名称"+","+"商品销量");
		for(ProductList p :productList){
			out.println(p.getName()+","+p.getSalnum());
		}
		//刷新缓存区，以便缓存区不满时不会显示里边的内容
		out.flush();
		//关闭缓存区
		out.close();*/
    }

    static String processFileName(HttpServletRequest request,String fileName){

        String codeFileName = null;
        try{

            if (request.getHeader("user-agent").toLowerCase().contains("msie")) {
                // IE
                codeFileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                // 非IE
                codeFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return codeFileName;

    }

    @RequestMapping("/self/pay.action")
    public void payHandler(HttpServletRequest request,HttpServletResponse response,String orderId,String money,String name) throws AlipayApiException, Exception{
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("orderId").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("money").getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("note").getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");

		/*String out_trade_no = id;
		String total_amount = money;
		String subject = name;
		String body = name;*/



        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        response.getWriter().println(result);

    }

    @RequestMapping("/self/pay_success.action")
    public String paySuccessHandler(HttpServletRequest request,Model model) throws Exception{
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("order_id").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            Boolean flag = orderInfoBussiness.updateOrderFlag(out_trade_no);
//            cartService.paySuccess(out_trade_no);
            model.addAttribute("money", total_amount);
            if(flag){
                return "/reception/self";
            }else {
                return "/reception/error";
            }
        }else {
            return "/reception/error";
        }

    }



}
