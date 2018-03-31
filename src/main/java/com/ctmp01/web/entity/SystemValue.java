package com.ctmp01.web.entity;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
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
    public static String  ALIPAY_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxjXJPkX62XmAZUKt1YkpQnh1eZ0B6JdbIGxQwqCdro+NDSI43emODO9xj4VgMfmlszslgmPzEoSA4QT4rmw467aBQTBujwqJnT4whj4mR+C5PkXEnkoWYfVTOW2rVhJ4z9fBGkZnIZB9A4Xz78rrlWe4RDMDCzK5Gw+7i3+iSZ1nbH+dCP7JaWAZyVrWp7F7bh0p96tyEYEa9jD6Z6I1LhBBhlxVN8N3CXm9XhzGtMdNPm56xxgZY3jZmMPQvqmgFAfCbikmtyjw0+5jCqRUb8waXJnOSC+f7IKX8pWt4phMtc0HZ6+BOD+JN6vemoP81ZcWZjbFSjV3Qqcpjia7dQIDAQAB";
    /**
     *
     *  商户私钥
     */
    public static String  ALIPAY_PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCH+30jswuPREMkmVfUMu1YzBMmQ8JJK4dU6Jug2KnuUPklDqpV+DXwPhcJHrINDJEix1mnvDlQ8nMZ7aoaeVSFcFl5dCRGdJ2ypAc47W7ecs/f6C/wPjGkr9BFDteLGHfDLxxyvpAm3FuYFgj7VqgAc9xOkwZNQhT9WfQTj50MXAwQ3i9v5Zy7EplAMvMc/69ruvAPHw3RI5HC7ko5zOf8nOMTTcmq5LQPP1SBIZDsY3vYb/bQLUbIXPRDXS80sz9B29/L+pJPhmvEewAAhj8/upoQMz6vbM7O0mGdwyLn1CiPd8KlO5e+haaVlVAg+6EKk1o+OS+g3iBZW7bJPbHFAgMBAAECggEAIX66CWQd8dE/tLEs6DEPM7M4+FDyd3GIaUrOouUbnn9Fj3I4VOrJ5n500CNGel5hFa8DD4UWnTDLV1SqdND0F9Zfw8Hu+YfaHFm5CbW6fILNX3ri7At10PoZlBke0L1bKrHdEQqNmXTn/uNWnQpwYYQofRZMFBW1894HeBYE+jkKGhAPsywhZ6cJksygzB5N2rnScqH5VKIdKjgSxa7ibOOkKNrTIOOzrRSc/tWqbRA4SbLs8C1ylPLYAxJOM8mvbre7UDmbDDuFI5TypTw2phAEu4TGwygfEm5F4b2TQ9RrjWvsbtcRy7Z0rAXxEU0f378RdNrSwa1epMEQvb174QKBgQDEaD7ALnTEA8SSZ2rkEHFKs80R3zr8ZuZ0ESPZZRRslH1Y+L56tnoGNzrgJlHzWgQub8yDgSoQKqlFEWtoeHOZUd9EZBsoy9dH4VoUBGEnrfnStSDFNNlO/qScUZsBy8KCtDExWyBxpFsXkQ/AGKM8pWHEV4XYE1D3dBjNckH+TQKBgQCxPcyF/iAKHjO664XTQGUxr6PzJP1UsSZc0itqCBdMAozJSoP0RA9DV9inHCT+BqqjseJp2wDSk2czV+dyv5zDsnAufeq/GqFGL+GYnXkomMSGR+Am27nLGXhg/mV8ITI29lKC2Kn1U1zvLLJke2DLVVYamrgbC7OwtCs2HNvtWQKBgQCSN+FvhaZcP0najK9hNnSBXrYutffd7GVox1HfCIdaBnoDALwd8zeI5Rkpwp2EA+HVCFpDFKfR/VCjIMLLtdCpte/3pGcrygVtd/FtsYz602SHX10UM7IMQzsdPQufHKikUa3Fxseykt9V/iKxqiXsglr0pwv3vvmqH35PhN5oiQKBgQCvh4xthuckHhUNIQM/255cPp4XxqI8pdRgSKRGNg0cccoL74gO+PhEsVMW7ICWMvP7ecfJHeMKqICkEIJe2fTeunDvGjTJXg4p/HFnO00qL2Exuyz6TtEJmwfkxwyqihW46Ol+PCTVmOGI3LMvYCDUPocvgdqirkwey8+fn6UOwQKBgC1DtvdSCNhGSzHlwc1qg1frXPQiE9SN6LkCjDuyrMk7gOoa71CjIrKDSEvWNq6qJ6AGpDqISmJwDy7J0RzzrvlqUoUXIblynuHuko0jvLr7h4RgXDBbH4HwfizuErJhoNY6UtvWv17QqeEKfge+7AG1I5HL7fl0/+gLZSp0Od0q";
    /**
     * 支付宝APPID
     */
    public static String  ALIPAY_APPID ="2016091100484192";
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
