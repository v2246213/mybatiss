package com.ctmp01.web.controller;

import com.ctmp01.web.entity.Orders;
import com.ctmp01.web.entity.SystemValue;
import com.ctmp01.web.entity.User;
import com.ctmp01.web.service.UserService;
import com.ctmp01.web.util.RandomUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/22 0022.
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/haha")
    public  String  user(){
        String a="wo cao";return  a;
    }

    @PostMapping("/getUser")
    public User getUser(Integer id){
        return userService.getUser(id);
    }

    @PostMapping("/addOrders")
    public String addOrders(Integer id){
        Orders orders=new Orders(2, RandomUtil.getRandomFileName(),"看电影",new BigDecimal(15.0));
        return SystemValue.aliPaySignPrams(orders)                ;
    }


    @PostMapping(value = "/getAliPayNotify")
    public void getnotify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入支付宝回调");
        // 获取支付宝GET过来反馈信息
        String reqWay = "";
        if ("GET".equals(request.getMethod())) {
            reqWay = "GET";
        }
        Map<String, String> params = new HashMap<String, String>();
        Map<?, ?> requestParams = request.getParameterMap();
        for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            if ("GET".equals(reqWay)) {
                try {
                    valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    System.out.println("不支持的编码：" + e.getMessage());
                    e.printStackTrace();
                }
            }
            params.put(name, valueStr);
        }
        String trade_no = request.getParameter("trade_no"); // 支付宝交易号
        String trade_status = request.getParameter("trade_status"); // 支付状态
        String out_trade_no = request.getParameter("out_trade_no"); // 系统订单号
        System.out.println("支付宝交易号：" + trade_no + ", 返回状态:" + trade_status + ",订单号  :" + out_trade_no);
        Map<String, Object> map = new HashMap<String, Object>();
        String result = "";
        if ("TRADE_SUCCESS".equals(trade_status)) {
            synchronized (this) {
                try {//保存订单信息
                   // orderService.modifyOrdersInfo(orders);
                    result = "success";
                } catch (Exception e) {
                    result = "fail";
                    e.printStackTrace();
                }

            }
        } else {

            result = "fail";
        }
        map.put(result, result);
        //ResponseUtils.renderText(response, result);
    }
}
