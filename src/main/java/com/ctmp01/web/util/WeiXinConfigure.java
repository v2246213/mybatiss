package com.ctmp01.web.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class WeiXinConfigure {
    //这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
    // 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
    // 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

    private static String key = "9c2d48ae4bb648999ec2230d1ac294ea";

    //微信分配的公众号ID（开通公众号之后可以获取到）
    private static String appID = "wxb6c49c8240e7d9f2";

    //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
    private static String mchID = "1482256092";

    //受理模式下给子商户分配的子商户号
    private static String subMchID = "";

    //HTTPS证书的本地路径
    private static String certLocalPath = "";

    //HTTPS证书密码，默认密码等于商户号MCHID
    private static String certPassword = "";

    //是否使用异步线程的方式来上报API测速，默认为异步模式
    private static boolean useThreadToDoReport = true;

    //机器IP
    private static String ip = "";

    //支付类型
    private static String tradeType = "";

    //商品描述
    private static String body = "";

    //商户内部订单编号
    private static String outTradeNo = "";

    //商品价格
    private static String totalFee = "";

    //以下是请求方法
    public static String requestPOST = "POST";

    public static String requestGET = "GET";

    //以下是几个API的路径：

    //1）被扫支付API
    public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

    //2）被扫支付查询API
    public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

    //3）退款API
    public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    //4）退款查询API
    public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

    //5）撤销API
    public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

    //6）下载对账单API
    public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

    //7) 统计上报API
    public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

    //7) 统计上报API
    public static String ORDER_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";

//    public static String WEIXIN_URL = "http://wang.app.test.ittun.com/orderPay/weixinCallback";
//    public static String WEIXIN_URL = "http://www.wangsocial.com:8080/orderPay/weixinCallback";
    public static String WEIXIN_URL = "http://www.wangsocial.com:8081/orderPay/weixinCallback";

    public static boolean isUseThreadToDoReport() {
        return useThreadToDoReport;
    }

    public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
        WeiXinConfigure.useThreadToDoReport = useThreadToDoReport;
    }

    public static String HttpsRequestClassName = "com.tencent.com.ctmp02.common.HttpsRequest";

    public static void setKey(String key) {
        WeiXinConfigure.key = key;
    }

    public static void setAppID(String appID) {
        WeiXinConfigure.appID = appID;
    }

    public static void setMchID(String mchID) {
        WeiXinConfigure.mchID = mchID;
    }

    public static void setSubMchID(String subMchID) {
        WeiXinConfigure.subMchID = subMchID;
    }

    public static void setCertLocalPath(String certLocalPath) {
        WeiXinConfigure.certLocalPath = certLocalPath;
    }

    public static void setCertPassword(String certPassword) {
        WeiXinConfigure.certPassword = certPassword;
    }

    public static void setIp(String ip) {
        WeiXinConfigure.ip = ip;
    }

    public static String getKey(){
        return key;
    }

    public static String getAppid(){
        return appID;
    }

    public static String getMchid(){
        return mchID;
    }

    public static String getSubMchid(){
        return subMchID;
    }

    public static String getCertLocalPath(){
        return certLocalPath;
    }

    public static String getCertPassword(){
        return certPassword;
    }

    public static String getIP(){
        return ip;
    }

    public static void setHttpsRequestClassName(String name){
        HttpsRequestClassName = name;
    }

    public static String getTradeType() {
        return tradeType;
    }

    public static void setTradeType(String tradeType) {
        WeiXinConfigure.tradeType = tradeType;
    }


    public static String getBody() {
        return body;
    }

    public static void setBody(String body) {
        WeiXinConfigure.body = body;
    }


    public static String getTotalFee() {
        return totalFee;
    }

    public static void setTotalFee(String totalFee) {
        WeiXinConfigure.totalFee = totalFee;
    }

    public static String getOutTradeNo() {
        return outTradeNo;
    }

    public static void setOutTradeNo(String outTradeNo) {
        WeiXinConfigure.outTradeNo = outTradeNo;
    }

//    /**
//     * 签名算法
//     * @param o 要参与签名的数据对象
//     * @return 签名
//     * @throws IllegalAccessException
//     */
//    @SuppressWarnings("rawtypes")
//    public static String getSign(Object o) throws IllegalAccessException {
//        ArrayList<String> list = new ArrayList<String>();
//        Class cls = o.getClass();
//        Field[] fields = cls.getDeclaredFields();
//        for (Field f : fields) {
//            f.setAccessible(true);
//            if (f.get(o) != null && f.get(o) != "") {
//                list.add(f.getName() + "=" + f.get(o) + "&");
//            }
//        }
//        int size = list.size();
//        String [] arrayToSort = list.toArray(new String[size]);
//        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < size; i ++) {
//            sb.append(arrayToSort[i]);
//        }
//        String result = sb.toString();
//        result = result.substring(0, result.length()-1);
//        result += "&key=" + WeiXinConfigure.getKey();
////		result = new String(result.getBytes(),"gbk");
//        result = MD5Util.MD5Encode(result,"UTF-8").toUpperCase();
//        return result;
//    }
    /**
     * 签名算法
     * @param map
     * @return
     */
    public static String getSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result = result.substring(0, result.length()-1);
        result += "&key=" + WeiXinConfigure.getKey();
//		result = new String(result.getBytes(),"gbk");
        result = MD5Util.MD5Encode(result,"UTF-8").toUpperCase();
        return result;
    }

    /**
     * 生成微信统一下单请求参数
     * @param payOrderNo
     * @param price
     * @param body
     * @return
     */
    public static String createSig(String payOrderNo, BigDecimal price,String body) {
        if(Tools.notEmpty(payOrderNo, price, body)) {
            BigDecimal num = new BigDecimal("100");
            DecimalFormat df = new DecimalFormat("######0");
            WeiXinConfigure.setOutTradeNo(payOrderNo.trim());
            WeiXinConfigure.setTotalFee(df.format(price.multiply(num)).toString());
            WeiXinConfigure.setTradeType("APP");
            WeiXinConfigure.setBody(body);
        }
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("appid", WeiXinConfigure.getAppid());
        parameters.put("mch_id", WeiXinConfigure.getMchid());
        parameters.put("nonce_str",
                Tools.getRandomStringByLength(32).toUpperCase());
        parameters.put("body", WeiXinConfigure.getBody());
        parameters.put("out_trade_no", WeiXinConfigure.getOutTradeNo());
        parameters.put("total_fee", WeiXinConfigure.getTotalFee());
        parameters.put("spbill_create_ip", WeiXinConfigure.getIP());
//		parameters.put("notify_url", "http://mminstapp.chinacloudsites.cn/v1/paySuccess");
        parameters.put("notify_url", WeiXinConfigure.WEIXIN_URL);
        parameters.put("trade_type", WeiXinConfigure.getTradeType());
        String sign = getSign(parameters);
        parameters.put("sign", sign);
        String requestXML = getRequestXml(parameters);
        return requestXML;
    }

    /**
     * 组装统一下单返回参数给app 进行请求付款API
     * @param xmlMap
     * @return
     */
    public static HashMap<String, Object> returnWxPayParameter(HashMap<String, Object> xmlMap){
        if(null != xmlMap){
            HashMap<String, Object> returnParameter = new HashMap<String, Object>();
            returnParameter.put("appid", xmlMap.get("appid"));
            returnParameter.put("partnerid", xmlMap.get("mch_id"));
            returnParameter.put("prepayid", xmlMap.get("prepay_id"));
            returnParameter.put("noncestr", xmlMap.get("nonce_str"));
            returnParameter.put("timestamp", new Date().getTime() / 1000);
            returnParameter.put("package", "Sign=WXPay");
            String sign = WeiXinConfigure.getSign(returnParameter);
            returnParameter.put("sign", sign);
            return returnParameter;
        }
        return null;
    }

    /**
     * @author
     * @Description：将请求参数转换为xml格式的string
     * @param parameters
     *            请求参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String getRequestXml(HashMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
                    || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 发送微信后台
     * @param return_code
     * @param return_msg
     * @return
     */
    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

    /**
     * 发送https请求
     *
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outputStr
     *            提交的数据
     * @return 返回微信服务器响应的信息
     */
    public static String httpsRequest(String requestUrl, String requestMethod,
                               String outputStr) {
        try {
            // // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            // TrustManager[] tm = { new MyX509TrustManager() };
            // SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            // sslContext.init(null, tm, new java.security.SecureRandom());
            // // 从上述SSLContext对象中得到SSLSocketFactory对象
            // SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            // conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type",
                    "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            // log.error("连接超时：{}", ce);
        } catch (Exception e) {
            // log.error("https请求异常：{}", e);
        }
        return null;
    }
}
