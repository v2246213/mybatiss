package com.ctmp01.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.ctmp01.web.entity.Orders;
import com.ctmp01.web.entity.SystemValue;
import com.ctmp01.web.entity.User;
import com.ctmp01.web.service.UserService;
import com.ctmp01.web.util.RandomUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String user() {
        String a = "wo cao";
        return a;
    }

    @PostMapping("/getUser")
    public User getUser(Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/addOrders")
    public String addOrders(Integer id) {
        Orders orders = new Orders(2, RandomUtil.getRandomFileName(), "wocao", new BigDecimal(15.0));
        return SystemValue.aliPaySignPrams(orders);
    }

    @RequestMapping(value="/getAliPayNotify",produces="application/json;charset=utf-8")
        public String notify (HttpServletRequest request){
            Map requestParams = request.getParameterMap();
            System.out.println("支付宝支付结果通知" + requestParams.toString());
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();

            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com  对应应用下查看。

            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            try {
                //验证签名
                boolean flag = AlipaySignature.rsaCheckV1(params, SystemValue.ALIPAY_PUBLIC_KEY, SystemValue.ALIPAY_CHARSET, "RSA2");
                if (flag) {
                    if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
//                    //付款金额
//                    String amount = params.get("buyer_pay_amount");
//                    //支付宝交易号
//                    String trade_no = params.get("trade_no");
//                    //附加数据
//                    String passback_params = URLDecoder.decode(params.get("passback_params"));

                        //商户订单号
                        String out_trade_no = params.get("out_trade_no");
                       // updateOrderInfo(out_trade_no, MD5Encode.encode(out_trade_no));
                    }
                }
            } catch (AlipayApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "success";
    }
}