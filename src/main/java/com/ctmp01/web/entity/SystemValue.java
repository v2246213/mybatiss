package com.ctmp01.web.entity;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.ctmp01.web.util.RandomUtil;

import java.math.BigDecimal;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/3/31 0031.
 */
public class SystemValue {
    /**
     * 支付宝公钥
     */
    public static String  ALIPAY_PUBLIC_KEY ="";
    /**
     *
     *  支付宝应用私钥
     */
    public static String  ALIPAY_PRIVATE_KEY ="";
    /**
     * 支付宝APPID
     */
    public static String  ALIPAY_APPID ="";
    /**
     * 支付宝网关
     */
    public static String  ALIPAY_WEBWAY ="https://openapi.alipaydev.com/gateway.do";
    /**
     * 支付宝编码
     */
    public static String  ALIPAY_CHARSET ="utf-8";
    /**
     * 支付宝回调地址
     */
    public static String  ALIPAY_NOTIFY_URL ="";
    /**
     * 加签参数
     *
     * @param orders
     * @return String
     */
    public static String aliPaySignPrams(Orders orders) {

        // 实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(SystemValue.ALIPAY_WEBWAY, SystemValue.ALIPAY_APPID,
                SystemValue.ALIPAY_PRIVATE_KEY, "json", SystemValue.ALIPAY_CHARSET, SystemValue.ALIPAY_PUBLIC_KEY,
                "RSA2");
        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

        model.setSubject(orders.getTitle()); // 商品标题
        model.setOutTradeNo(orders.getOrderid()); // 商家订单编号
        // model.setTimeoutExpress("525600m"); //超时关闭该订单时间，默认15天
        model.setTotalAmount(orders.getMoney().toString()); // 订单总金额
        model.setProductCode("QUICK_MSECURITY_PAY"); // 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        request.setBizModel(model);
        request.setNotifyUrl(SystemValue.ALIPAY_NOTIFY_URL); // 回调地址
        String orderStr = "";
        try {
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            orderStr = response.getBody();
            System.out.println("订单str：" + orderStr);
            //logger.info("订单str：" + orderStr);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return orderStr;
    }
}
