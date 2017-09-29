package com.ctmp01.web.util;


import org.springframework.beans.factory.annotation.Value;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017/6/27.
 */
public class PropertiesUtils {
    private static final String FILEPATH = "api_code.properties";

    public static int bundleToInt(String key) {
        return Integer.parseInt(ResourceBundle.getBundle(FILEPATH).getString(
                key));
    }

    public static double bundleToDouble(String key) {
        return Double.parseDouble(ResourceBundle.getBundle(FILEPATH).getString(
                key));
    }

    /**
     * 获取属性文件数据
     *
     * @param key
     *            文件内容kay
     * @param filePath
     *            属性文件路径
     * @return
     */
    public static String getValueByKey(String key, String filePath) {
        String str = StringUtils.getStr(ResourceBundle.getBundle(filePath)
                .getString(key));
		/*try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
        return str;
    }

    /**
     * <font color="red">无占位符的字符资源</font>
     *
     * @Title bundle
     * @param key
     * @return {@link String}
     * @since 1.0
     */

    public static String bundle(String key) {
        return ResourceBundle.getBundle(FILEPATH).getString(key);
    }

    /**
     * <font color="red">有占位符的字符资源</font>
     *
     * @Title bundle
     * @param key
     * @param arguments
     * @return {@link String}
     * @since 1.0
     */
    public static String bundle(String key, Object... arguments) {
        String str = StringUtils.getStr(ResourceBundle.getBundle(FILEPATH)
                .getString(key));
		/*try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        return MessageFormat.format(str, arguments);
    }
    public static void main(String[] args) {
        System.out.println(getValueByKey("api_code_msg_1", FILEPATH));
    }
}
