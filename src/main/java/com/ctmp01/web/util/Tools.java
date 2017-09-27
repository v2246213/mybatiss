package com.ctmp01.web.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	public static HashMap<String, Object> hashMap() {
		return new HashMap<String, Object>();
	}

	public static ArrayList<Object> arrayList() {
		return new ArrayList<Object>();
	}

	public static <T> ArrayList<T> arrayList(Class<T> t) {
		return new ArrayList<T>();
	}

	/**
	 * 生产区间随机数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static Integer getRandomInterval(int MinNum, int MaxNum) {
		int result = MinNum + (int) (Math.random() * ((MaxNum - MinNum) + 1));
		return result;

	}

	/**
	 * 获取两个List的不同元素
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<String> getDiffrent(List<String> list1,
			List<String> list2) {
		List<String> result = new ArrayList<String>();
		for (String str : list1) {
			if (!list2.contains(str)) {
				result.add(str);
			}
		}
		return result;
	}

	/**
	 * 获取客户端请求数据 .
	 *
	 * @param request
	 * @return
	 */
	public static String getOrgMessageStrUtf8(
			HttpServletRequest request) {/*
											 *
											 * String message = "" ; try {
											 * BufferedReader br = new
											 * BufferedReader ( new
											 * InputStreamReader ( request .
											 * getInputStream ( ) , "utf-8" ) )
											 * ;
											 *
											 * String tempStr = "" ; while ( (
											 * tempStr = br . readLine ( ) ) !=
											 * null ) { message += tempStr ; }
											 *
											 * } catch ( Exception ex ) { ex .
											 * printStackTrace ( ) ; }
											 *
											 * return message ;
											 */
		try {

			request.setCharacterEncoding("UTF-8");
			Enumeration<String> enumeration = request.getParameterNames();
			JSONObject jsonObject = new JSONObject();
			while (enumeration.hasMoreElements()) {
				String key = enumeration.nextElement();
				String value = request.getParameter(key);
				jsonObject.put(key, value);
			}
			return jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 获取一定长度的随机字符串
	 * @param length 指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 将long型转为date
	 */
	public static Date getDateTime(Long time) {
		if (Tools.isEmpty(time)) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return c.getTime();
	}

	/**
	 * 讲date类型转为long类型
	 * 
	 * @param time
	 * @return
	 */
	public static Long getLongTime(Date date) {
		if (isEmpty(date)) {
			return null;
		} else {
			return date.getTime();
		}
	}

	/**
	 * 获取系统当前long
	 */
	public static Long getNowTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取系统当前Date
	 */
	public static Date getNowDate() {
		return getDateTime(getNowTime());
	}
	/**
	 * 验证时间段参数是否正确
	 *@param startTime
	 *@param endTime
	 *@return false:参数有误，true：参数正确
	 */
	public static Boolean validateDateTime(Long startTime,Long endTime){
		if(Tools.notEmpty(startTime)&&startTime.toString().length()!=13){
			return false;
		}
		if(Tools.notEmpty(endTime)&&endTime.toString().length()!=13){
			return false;
		}
		if(Tools.notEmpty(startTime,endTime)){
			if(startTime>endTime){
				return false;
			}
		}
		return true;
	}
	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}

	/**
	 * 获取客户端IP地址
	 * 
	 * @param request
	 * @return
	 */

	public static String getIp(HttpServletRequest request) throws IOException {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 如果是多级代理，那么取第一个ip为客户ip,但是为了防止伪造,取最后一个由Azure封装的真实IP
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		if (ip != null && ip.contains(":")) {
			ip = ip.substring(0, ip.indexOf(":"));
		}
		return ip;
	}

	public static boolean isIp(String ip) {
		String ipPattern = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(ipPattern);
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	/**
	 * 查找字符串是否存在数组中
	 * 
	 * @param strs
	 * @param s
	 * @return
	 */
	public static boolean isHave(String[] strs, String s) {
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 随机生成字母
	 * 
	 * @param count
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getRandomAZ(int count) {
		ArrayList list = new ArrayList();
		for (char c = 'a'; c <= 'z'; c++) {
			list.add(c);
		}
		String str = "";
		for (int i = 0; i < count; i++) {
			int num = (int) (Math.random() * 26);
			str = str + list.get(num);
		}
		return str;
	}

	/**
	 * 随机生成Count位数验证码
	 * 
	 * @return
	 */
	public static String getRandomNum(int count) {
		return RandomStringUtils.randomNumeric(count);
	}

	/**
	 * 全都不为空(String为""和Collection.size为0也判定为空)
	 */
	public static boolean notEmpty(Object... os) {
		for (Object o : os) {
			if (o == null) {
				return false;
			} else if (o instanceof String) {
				String s = (String) o;
				if (s.equals("")) {
					return false;
				}
			} else if (o instanceof Collection) {
				Collection<?> c = (Collection<?>) o;
				if (c.size() == 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 是否有空(String为""和Collection.size为0也判定为空)
	 */
	public static boolean isEmpty(Object... os) {
		for (Object o : os) {
			if (o == null) {
				return true;
			} else if (o instanceof String) {
				String s = (String) o;
				if (s.equals("")) {
					return true;
				}
			} else if (o instanceof Collection) {
				Collection<?> c = (Collection<?>) o;
				if (c.size() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 利用正则表达式判断字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 字符串转换为Long
	 * 
	 * @param str
	 * @return
	 */
	public static Long str2Long(String str) {
		try {
			return Long.valueOf(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 友好的显示日期
	 */
	public static String getIndexDate(Date pubDate) {
		int year = Tools.Str2Int(Tools.date2Str(pubDate, "yyyy"));
		int month = Tools.Str2Int(Tools.date2Str(pubDate, "MM"));
		int day = Tools.Str2Int(Tools.date2Str(pubDate, "dd"));

		Date nowDate = new Date();
		int nowYear = Tools.Str2Int(Tools.date2Str(nowDate, "yyyy"));
		int nowMonth = Tools.Str2Int(Tools.date2Str(nowDate, "MM"));
		int nowDay = Tools.Str2Int(Tools.date2Str(nowDate, "dd"));
		String date;
		if (year == nowYear && month == nowMonth && nowDay - day <= 2) {
			if (nowDay - day == 2) {
				date = "前天";
			} else if (nowDay - day == 1) {
				date = "昨天";
			} else {
				date = "今天";
			}
		} else {
			date = month + "." + day;
		}
		return date;
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		if (Tools.isEmpty(date)) {
			return "";
		}
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 把时间根据时、分、秒转换为时间段
	 * 
	 * @param StrDate
	 */
	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now;

		try {
			now = new Date();
			Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			StringBuffer sb = new StringBuffer();
			// sb.append("发表于：");
			if (hour > 0) {
				sb.append(hour + "小时前");
			} else if (min > 0) {
				sb.append(min + "分钟前");
			} else {
				sb.append(sec + "秒前");
			}

			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultTimes;
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static boolean checkPhone(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^1([3-5]|[7-9])\\d{9}$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证码验证
	 * 
	 * @param code
	 * @param date
	 * @return
	 */
	public static String validateCode(String code, Date date) {
		return "";
	}

	/**
	 * String转Integer
	 * 
	 * @param s
	 * @return Integer or NULL 2016年5月9日下午2:38:13 李安 创建
	 */
	public static Integer Str2Int(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return null;
		}
	}

	public static Boolean str2Bool(String s) {
		try {
			return Boolean.parseBoolean(s);
		} catch (Exception e) {
			return null;
		}
	}

	public static String transfer(String keyword) {
		if (keyword.contains("%") || keyword.contains("_")) {
			keyword = keyword.replaceAll("\\\\", "\\\\\\\\")
					.replaceAll("\\%", "\\\\%").replaceAll("\\_", "\\\\_");
		}
		return keyword;
	}

	/**
	 * 检查性别[0:未知性别,1:男,2女]
	 * 
	 * @param sex
	 * @return 2016年5月9日下午2:37:19 李安 创建
	 */
	public static boolean isSex(Short sex) {
		if (sex != 0 && sex != 1 & sex != 2) {
			return false;
		}
		return true;
	}

	/**
	 * 判断账号，密码字符长度
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLength(String str) {

		if (str.trim().length() < 6 || str.trim().length() > 14) {
			return false;
		}
		return true;
	}

	public static boolean isContains(String key, String[] values) {
		if (null == values || values.length == 0) {
			return false;
		}
		for (String value : values) {
			if (value.equals(key)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static Object getOneByCollection(List colls) {
		if (colls != null && colls.size() > 0) {
			Iterator iterator = colls.iterator();
			return iterator.hasNext() ? iterator.next() : null;
		}
		return null;
	}

	/**
	 * 用三反转等效左移字符串
	 * 
	 * @param from
	 *            需要移动的字符串
	 * @param index
	 *            需要移动的位数
	 */
	public static String getDisplacemenString(String from, int index) {
		from = reChange(from); // 循环左移 （先整体，再部分）
		String first = reChange(from.substring(0, from.length() - index));
		String second = reChange(from.substring(from.length() - index));
		from = first + second;
		return from;
	}

	/**
	 * 反转字符串,把from字符串反转过来（很常见牢记） 字符串求长度用length(),数组求长度用length
	 * 
	 * @param from
	 * @return
	 */
	public static String reChange(String from) {
		char[] froms = from.toCharArray();
		for (int i = 0; i < from.length() / 2; i++) {
			char temp = froms[i];
			froms[i] = froms[from.length() - 1 - i];
			froms[froms.length - 1 - i] = temp;
		}
		return String.valueOf(froms);
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            s 需要得到长度的字符串
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 得到一个制定长度的字符串,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            s 字符串
	 * @return length 得到的字符串长度
	 */
	public static String lengthString(String s, int length) {

		String returnString = "";
		if (s == null)
			return returnString;

		char[] c = s.toCharArray();
		char[] b = new char[length];
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				if (len + 1 > length) {
					return new String(b);
				}
				len++;
				b[i] = c[i];
			}
			if (len >= length) {
				return new String(b);
			}
			b[i] = c[i];

		}
		return returnString;
	}

	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}



	/**
	 * 获取二维码code值
	 * 
	 * @return
	 */
	public static String getQRcode() {
		StringBuffer qrcode = new StringBuffer();
		String date = date2Str(getNowDate());
		String dayAndTime = date.split("-")[2];
		String day = dayAndTime.split(" ")[0];
		String time = dayAndTime.split(" ")[1].replace(":", "");
		qrcode.append(day);
		qrcode.append(time);
		qrcode.append(getRandomNum(4));
		return qrcode.toString();
	}
	/**
	 * 验证字符串长度是否符合规则
	 *@param str 字符串
	 *@param length 限制长度
	 *@return true:符合规则；false:不符合
	 */
	public static Boolean validateStrLength(String str ,Integer length){
		if(isEmpty(str)){
			return false;
		}
		if(str.getBytes().length>length){
			return false;
		}
		return true;
	}
	/**
	 *  Java app端
	 */
	public static final String JAVA_APP = "javaapp";

	/**
	 * 外部调用自己系统接口加密规则
	 * 1.对参数进行a-z排序(ASCII码从小到大排序（字典序))
	 * 2.取出随机数，并且拼接到两端
	 * 3.语言(javaapp)进行md5加密，并且拼接两端
	 * 4.字符串进行md5加密
	 * @param request
	 * @return
	 */
	public static String getAppSignStr(HttpServletRequest request, String type) {
		try {
			request.setCharacterEncoding("UTF-8");
			Set<String> keySet = request.getParameterMap().keySet();
			String[] parms = keySet.toArray(new String[keySet.size()]);
			Arrays.sort(parms, String.CASE_INSENSITIVE_ORDER);
			// 获取系统加密字符串
			String typeMd5 = "";
			if(JAVA_APP.equals(type)){
				typeMd5 = md5(JAVA_APP);
			}
			String creditCode = request.getParameter("nonceStr"); // 获取授信码
			StringBuffer bf = new StringBuffer(typeMd5);
			bf.append("&");
			bf.append(creditCode);
			bf.append("&");
			for (String param : parms) {
				if (param.equals("signature")) {
					continue;
				}
				bf.append(param).append("=").append(request.getParameter(param))
						.append("&");
			}
			bf.append(creditCode);
			bf.append("&");
			bf.append(typeMd5);
			// 加密后字符串
			String md5_bf = md5(bf.toString()).toString();
			return md5_bf;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String md5(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	/**
	 * string 转成 JsonObjct
	 * @param str
	 * @return
	 */
	public static JsonObject strConvertJson(String str) {
		if (Tools.isEmpty(str)) {
			return null;
		}
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(str);
		JsonObject jsonObj = element.getAsJsonObject();
		return jsonObj;
	}
	public static void main(String[] args) {
		System.out.println(Tools.getDateTime(1497008245414l));
		System.out.println(Tools.getDateTime(1497064836821l));
	}
}
