package com.ctmp01.web.util;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */
public class StringUtils {

    /**
     * 将以逗号分隔的字符串转换成字符串数组
     *
     * @param valStr
     * @return String[]
     */
    public static String[] StrList(String valStr) {
        int i = 0;
        String TempStr = valStr;
        String[] returnStr = new String[valStr.length() + 1
                - TempStr.replace(",", "").length()];
        valStr = valStr + ",";
        while (valStr.indexOf(',') > 0) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(','));
            valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

            i++;
        }
        return returnStr;
    }

    /**
     * 获取字符串编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    /**
     * 字符串编码转换函数，用于将指定编码的字符串转换为标准（Unicode）字符串
     *
     */
    public static String getStr(Object _strSrc) {
        if (isEmpty(_strSrc)) {
            return "";
        }
        return _strSrc.toString().trim();
    }

    /**
     * 获取字符串
     *
     * @param _strSrc
     *            当前字符串
     * @param defaultStr
     *            默认字符串
     * @return
     */
    public static String getStrByDefault(Object _strSrc, String defaultStr) {
        if (isEmpty(_strSrc)) {
            return defaultStr;
        }
        return getStr(_strSrc.toString());
    }

    /**
     * 获取int
     *
     * @param _strSrc
     * @return
     */
    public static int getInt(Object _strSrc) {
        if (isEmpty(_strSrc)) {
            return 0;
        }
        try {
            int num = Integer.parseInt(_strSrc.toString());
            return num;
        } catch (Exception e) {
        }
        return 0;
    }

    public static long getLong(Object _strSrc) {
        if (isEmpty(_strSrc)) {
            return 0;
        }
        try {
            long num = Long.parseLong(_strSrc.toString());
            return num;
        } catch (Exception e) {
        }
        return 0;
    }

    public static int getIntByDefault(Object _strSrc, int defaultInt) {
        if (isEmpty(_strSrc)) {
            return defaultInt;
        }
        try {
            int num = Integer.parseInt(_strSrc.toString());
            return num;
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    /**
     * 获取double
     *
     * @param _strSrc
     * @return
     */
    public static double getDouble(Object _strSrc) {
        if (isEmpty(_strSrc)) {
            return 0;
        }
        try {
            double num = Double.parseDouble(_strSrc.toString());
            return num;
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    /**
     * 判断指定字符串是否为空
     *
     * @param _string
     *            指定的字符串
     * @return 若字符串为空对象（_string==null）或空串（长度为0），则返回true；否则，返回false.
     */
    public static boolean isEmpty(Object _string) {
        return (("null".equals(_string)) || (_string == null) || (_string
                .toString().trim().length() == 0));
    }

    /**
     * 判定非空
     *
     * @param _string
     * @return
     */
    public static boolean isNotEmpty(Object _string) {
        return (!isEmpty(_string));
    }

    /**
     * 空白字符串判断
     *
     * @param _string
     * @return
     */
    public static boolean isNotBlank(Object _string) {
        if (_string == null) {
            return false;
        }
        return StringUtils.isNotBlank(_string.toString());
    }

    public static boolean isBlank(Object _string) {
        if (_string == null) {
            return true;
        }
        return StringUtils.isBlank(_string.toString());
    }

    /**
     * trim字符串
     *
     * @param _string
     * @return
     */
    public static String trim(Object _string) {
        return StringUtils.trim(_string.toString());
    }

    /**
     * 判断字符串相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    /**
     * list转换为逗号分隔的字符串
     *
     * @param list
     * @return
     */
    public static String listToStr(List<?> list) {
        String str = "";
        if (list != null && list.size() > 0) {
            for (Object ob : list) {
                str += "," + ob;
            }
            str = str.substring(1);
        }
        return str;
    }

    /**
     * 获取注册ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (!StringUtils.isEmpty(ip)) {
            String[] ips = ip.split(",");
            if (ips.length > 1)
                ip = ips[0];
        }
        return ip;
    }

    /**
     * 格式化double
     *
     * @param dividend
     *            被除数
     * @param total
     *            除数
     * @param decimalCount
     *            保留小数位数
     * @return
     */
    public static String formatDouble(double dividend, double total,
                                      int decimalCount) {
        double doubleNum = (dividend * 1.0) / total;
        String doubleNumStr = "";

        switch (decimalCount) {
            case 1:
                DecimalFormat df1 = new DecimalFormat("######0.0");
                return df1.format(doubleNum);
            case 2:
                DecimalFormat df2 = new DecimalFormat("######0.00");
                return df2.format(doubleNum);
            case 3:
                DecimalFormat df3 = new DecimalFormat("######0.000");
                return df3.format(doubleNum);
            case 4:
                DecimalFormat df4 = new DecimalFormat("######0.0000");
                return df4.format(doubleNum);
            default:
                break;
        }
        return doubleNumStr;
    }

    /**
     * 格式化数字
     *
     * @param
     * @param decimalCount
     * @return
     */
    public static String formatNumerical(Object numericalOb, int decimalCount) {
        double numerical = 0;
        if (!isEmpty(numericalOb)) {
            numerical = Double.parseDouble(numericalOb.toString()) * 100;
        }
        String doubleNumStr = "";

        switch (decimalCount) {
            case 1:
                DecimalFormat df1 = new DecimalFormat("######0.0");
                return df1.format(numerical);
            case 2:
                DecimalFormat df2 = new DecimalFormat("######0.00");
                return df2.format(numerical);
            case 3:
                DecimalFormat df3 = new DecimalFormat("######0.000");
                return df3.format(numerical);
            case 4:
                DecimalFormat df4 = new DecimalFormat("######0.0000");
                return df4.format(numerical);
            default:
                break;
        }
        return doubleNumStr;
    }

    /**
     * 格式化double
     *
     * @param doubleNum
     *            被格式化的数
     * @param decimalCount
     *            保留位数
     * @return
     */
    public static String formatDouble(double doubleNum, int decimalCount) {
        String doubleNumStr = "";

        switch (decimalCount) {
            case 0:
                DecimalFormat df0 = new DecimalFormat("######0");
                return df0.format(doubleNum);
            case 1:
                DecimalFormat df1 = new DecimalFormat("######0.0");
                return df1.format(doubleNum);
            case 2:
                DecimalFormat df2 = new DecimalFormat("######0.00");
                return df2.format(doubleNum);
            case 3:
                DecimalFormat df3 = new DecimalFormat("######0.000");
                return df3.format(doubleNum);
            default:
                break;
        }
        return doubleNumStr;
    }

    /**
     * 时间戳转换字符串
     *
     * @param mill
     * @return
     */
    public static String dateMillconvertStr(long mill) {
        Date date = new Date(mill);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    public static String getOrderCode() {
        String code = new SimpleDateFormat("yyyyMMddhhmmssSSS")
                .format(new Date()) + RandomStringUtil.random(5);
        return code;
    }

    /**
     * SQL语句特殊字符过滤处理函数
     * <p>
     * 用于：构造SQL语句时，填充字符串参数时使用
     * </p>
     * <p>
     * 如：
     * <code>String strSQL = "select * from tbName where Name='"+CMyString.filterForSQL("a'bc")+"'" </code>
     * </p>
     * <p>
     * 说明：需要处理的特殊字符及对应转化规则：如： <code> ' ---&gt;''</code>
     * </p>
     * <p>
     * 不允许使用的特殊字符： <code> !@#$%^&*()+|-=\\;:\",./&lt;&gt;? </code>
     * </p>
     *
     * @param _sContent
     *            需要处理的字符串
     * @return 过滤处理后的字符串
     */
    public static String filterForSQL(String _sContent) {
        if (_sContent == null)
            return "";

        int nLen = _sContent.length();
        if (nLen == 0)
            return "";

        char[] srcBuff = _sContent.toCharArray();
        StringBuffer retBuff = new StringBuffer((int) (nLen * 1.5));

        for (int i = 0; i < nLen; i++) {
            char cTemp = srcBuff[i];
            switch (cTemp) {
                case '\'': {
                    retBuff.append("''");
                    break;
                }
                case ';'://
                    boolean bSkip = false;
                    for (int j = (i + 1); j < nLen && !bSkip; j++) {
                        char cTemp2 = srcBuff[j];
                        if (cTemp2 == ' ')
                            continue;
                        if (cTemp2 == '&')
                            retBuff.append(';');
                        bSkip = true;
                    }
                    if (!bSkip)
                        retBuff.append(';');
                    break;
                default:
                    retBuff.append(cTemp);
            }// case
        }// end for
        return retBuff.toString();
    }

    /**
     * 判断字符串是否在指定字符串数组中
     *
     * @param str
     * @param array
     * @param isIgnoreCase
     * @return
     */
    public static boolean isInArray(String str, String[] array,
                                    boolean isIgnoreCase) {
        if (array == null || array.length == 0) {
            return false;
        }
        if (str == null || str.length() == 0) {
            return false;
        }

        String str0 = str.trim();
        for (int i = 0; i < array.length; i++) {
            String s1 = array[i];
            if (s1 != null) {
                s1 = s1.trim();
                boolean b0 = false;
                if (isIgnoreCase) {
                    b0 = s1.equalsIgnoreCase(str0);
                } else {
                    b0 = s1.equals(str0);
                }
                if (b0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 使用指定的字符分割字符串
     *
     * @param str
     * @param sp
     * @param isIgnoreNull
     * @return
     */
    public static String[] split(String str, char sp, boolean isIgnoreNull) {
        ArrayList<String> arrayBuffer = new ArrayList<String>();
        StringBuffer sb = new StringBuffer(2);
        for (int i = 0; i < str.length(); i++) {
            char c0 = str.charAt(i);
            if (c0 == sp) {
                String s0 = sb.toString().intern();
                sb.delete(0, sb.length());

                if (!isIgnoreNull) {
                    // 不忽略NULL串
                    if (s0 != null && s0.length() > 0) {
                        arrayBuffer.add(s0);
                    } else {
                        arrayBuffer.add("");
                    }
                } else {
                    // 忽略NULL串
                    if (s0 != null && s0.length() > 0) {
                        arrayBuffer.add(s0);
                    }
                }
            } else {
                sb.append(c0);
            }
        }
        if (sb.length() > 0) {
            // 最后一个字符串
            String s0 = sb.toString().intern();
            arrayBuffer.add(s0);
        } else if (str.charAt(str.length() - 1) == sp) {
            if (!isIgnoreNull) {
                arrayBuffer.add("");
            }
        }

        String[] retStrings = new String[arrayBuffer.size()];
        return arrayBuffer.toArray(retStrings);
    }

    public static String[] split(String _str, String _sDelim) {
        // String[] str
        if (_str == null || _sDelim == null) {
            return new String[0];
        }

        java.util.StringTokenizer stTemp = new java.util.StringTokenizer(_str,
                _sDelim);
        int nSize = stTemp.countTokens();
        if (nSize == 0) {
            return new String[0];
        }

        String[] str = new String[nSize];
        int i = 0;
        while (stTemp.hasMoreElements()) {
            str[i] = stTemp.nextToken().trim();
            i++;
        }// endwhile
        return str;
    }

    /**
     * 将一个数组按照给定的连接符联结起来
     *
     * @param _arColl
     *            进行操作的数组
     * @param _sSeparator
     *            连接符
     * @return 连接后的字符串
     */
    public static String join(List<Object> _arColl, String _sSeparator) {
        // check parameters
        if (_arColl == null)
            return null;

        // invoke reload-method and return
        return join(_arColl.toArray(), _sSeparator);
    }

    // ge add by gfc @2005-8-23 15:44:22
    /**
     * 将一个数组按照给定的连接符联结起来
     *
     * @param _arColl
     *            进行操作的数组
     * @param _sSeparator
     *            连接符
     * @return 连接后的字符串
     */
    public static String join(Object[] _arColl, String _sSeparator) {
        // check parameters
        if (_arColl == null || _arColl.length == 0 || _sSeparator == null)
            return null;

        if (_arColl.length == 1)
            return _arColl[0].toString();

        // resolve the demiter into the string
        StringBuffer result = new StringBuffer(_arColl[0].toString());
        for (int i = 1; i < _arColl.length; i++) {
            if (_arColl[i] == null) {
                continue;
            }
            result.append(_sSeparator);
            result.append(_arColl[i].toString());
        }

        // return the result
        return result.toString();
    }

    /**
     * 字符串显示处理函数：若为空对象，则返回指定的字符串
     *
     * @param _sValue
     *            指定的字符串
     * @param _sReplaceIfNull
     *            当_sValue==null时的替换显示字符串；可选参数，缺省值为空字符串（""）
     * @return 处理后的字符串
     */
    public static String showNull(String _sValue, String _sReplaceIfNull) {
        return (_sValue == null ? _sReplaceIfNull : _sValue);
    }

    /**
     * 字符串替换函数：用于将指定字符串中指定的字符串替换为新的字符串。
     *
     * @param _strSrc
     *            源字符串。
     * @param _strOld
     *            被替换的旧字符串
     * @param _strNew
     *            用来替换旧字符串的新字符串
     * @return 替换处理后的字符串
     */
    public static String replaceStr(String _strSrc, String _strOld,
                                    String _strNew) {
        if (_strSrc == null || _strNew == null || _strOld == null)
            return _strSrc;

        // 提取源字符串对应的字符数组
        char[] srcBuff = _strSrc.toCharArray();
        int nSrcLen = srcBuff.length;
        if (nSrcLen == 0)
            return "";

        // 提取旧字符串对应的字符数组
        char[] oldStrBuff = _strOld.toCharArray();
        int nOldStrLen = oldStrBuff.length;
        if (nOldStrLen == 0 || nOldStrLen > nSrcLen)
            return _strSrc;

        StringBuffer retBuff = new StringBuffer(
                (nSrcLen * (1 + _strNew.length() / nOldStrLen)));

        int i, j, nSkipTo;
        boolean bIsFound = false;

        i = 0;
        while (i < nSrcLen) {
            bIsFound = false;

            // 判断是否遇到要找的字符串
            if (srcBuff[i] == oldStrBuff[0]) {
                for (j = 1; j < nOldStrLen; j++) {
                    if (i + j >= nSrcLen)
                        break;
                    if (srcBuff[i + j] != oldStrBuff[j])
                        break;
                }
                bIsFound = (j == nOldStrLen);
            }

            // 若找到则替换，否则跳过
            if (bIsFound) { // 找到
                retBuff.append(_strNew);
                i += nOldStrLen;
            } else { // 没有找到
                if (i + nOldStrLen >= nSrcLen) {
                    nSkipTo = nSrcLen - 1;
                } else {
                    nSkipTo = i;
                }
                for (; i <= nSkipTo; i++) {
                    retBuff.append(srcBuff[i]);
                }
            }
        }// end while
        srcBuff = null;
        oldStrBuff = null;
        return retBuff.toString();
    }

    /**
     * 字符串替换函数：用于将指定字符串中指定的字符串替换为新的字符串。
     *
     * @param _strSrc
     *            源字符串。
     * @param _strOld
     *            被替换的旧字符串
     * @param _strNew
     *            用来替换旧字符串的新字符串
     * @return 替换处理后的字符串
     */
    public static String replaceStr(StringBuffer _strSrc, String _strOld,
                                    String _strNew) {
        if (_strSrc == null)
            return null;

        // 提取源字符串对应的字符数组
        int nSrcLen = _strSrc.length();
        if (nSrcLen == 0)
            return "";

        // 提取旧字符串对应的字符数组
        char[] oldStrBuff = _strOld.toCharArray();
        int nOldStrLen = oldStrBuff.length;
        if (nOldStrLen == 0 || nOldStrLen > nSrcLen)
            return _strSrc.toString();

        StringBuffer retBuff = new StringBuffer(
                (nSrcLen * (1 + _strNew.length() / nOldStrLen)));

        int i, j, nSkipTo;
        boolean bIsFound = false;

        i = 0;
        while (i < nSrcLen) {
            bIsFound = false;

            // 判断是否遇到要找的字符串
            if (_strSrc.charAt(i) == oldStrBuff[0]) {
                for (j = 1; j < nOldStrLen; j++) {
                    if (i + j >= nSrcLen)
                        break;
                    if (_strSrc.charAt(i + j) != oldStrBuff[j])
                        break;
                }
                bIsFound = (j == nOldStrLen);
            }

            // 若找到则替换，否则跳过
            if (bIsFound) { // 找到
                retBuff.append(_strNew);
                i += nOldStrLen;
            } else { // 没有找到
                if (i + nOldStrLen >= nSrcLen) {
                    nSkipTo = nSrcLen - 1;
                } else {
                    nSkipTo = i;
                }
                for (; i <= nSkipTo; i++) {
                    retBuff.append(_strSrc.charAt(i));
                }
            }
        }// end while
        oldStrBuff = null;
        return retBuff.toString();
    }

    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getPorjectPath() {
        String nowpath = "";
        nowpath = System.getProperty("user.dir") + "/";

        return nowpath;
    }
    /**
     * 获取运行类包名 方法名 行数
     * @return
     */
    public static String getTraceInfo(){
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
//        int stacksLen = stacks.length;
        sb.append("class:" ).append(stacks[1].getClassName()).append(";method:").append(stacks[1].getMethodName()).append(";number:").append(stacks[1].getLineNumber());
        return sb.toString();
    }

    public static void main(String[] args) {
        String content="<<哈哈哈>>";
     String result=   org.apache.commons.lang.StringUtils.substringBefore("<<",">>");
        System.out.println(result);
    }
}
