package com.example.demo.utils;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

public class StringUtils {
	/**
	 * md5加密
	 *
	 * @param plainText
	 * @return
	 */
	public static String getMd5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
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
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 防止sql注入
	 *
	 * @param sql
	 * @return
	 */
	public static String TransactSQLInjection(String sql) {
		return sql.replaceAll(".*([';]+|(--)+).*", " ");
	}

	public static double StringToDouble(String str) {
		return StringToDouble(str, (double) 0);
	}

	/**
	 * 保留num位小数
	 *
	 * @param dounum
	 * @param num
	 * @return
	 */
	public static String getNumPoint(double dounum, int num) {
		try {
			if (num > 0) {
				String numstr = "0.";
				for (int i = 0; i < num; i++) {
					numstr += "0";
				}
				DecimalFormat df = new DecimalFormat(numstr);
				return df.format(dounum);
			} else {
				return ObjectToString(dounum);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @param str
	 * @param def
	 */
	public static double StringToDouble(String str, double def) {
		double dRet = (double) def;
		try {
			if (str == null || str.trim().equals(""))
				str = "0";
			dRet = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return dRet;
	}

	/**
	 * 去除NULL 将NULL转换成空字符串
	 *
	 * @param obj
	 * @return
	 */
	public static String removeNull(Object obj) {
		return removeNull(obj, "");
	}

	/**
	 * 去除NULL 将NULL转换成默认字符串
	 *
	 * @param obj
	 * @param defStr
	 * @return
	 */
	public static String removeNull(Object obj, String defStr) {
		if (obj == null || "".equals(obj))
			return defStr;
		else
			return obj.toString();
	}

	/**
	 * 过滤特殊字符
	 *
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]\"";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * @param val
	 *            将Object类型转换为Integer类型
	 */
	public static Integer ObjectToInteger(Object val) {
		try {
			if (val != null) {
				String str = val.toString();
				if (!"".equals(str.trim()))
					return Integer.parseInt(str);
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 将Object类型转换为Integer类型，若val为null 或 “”，则return Integer类型默认值def
	 *
	 * @param val
	 * @param def
	 * @return
	 */
	public static Integer ObjectToInteger(Object val, Integer def) {
		try {
			if (val != null) {
				String str = val.toString();
				if (!"".equals(str.trim()))
					return Integer.parseInt(str);
			}
			return def;
		} catch (Exception e) {
			return def;
		}
	}

	/**
	 * 将Object类型 转换为String类型，若val为null 或 “”，则return ""
	 *
	 * @param val
	 * @return
	 */
	public static String ObjectToString(Object val) {
		try {
			if (val != null) {
				String str = val.toString();
				if (!"".equals(str.trim()))
					return str;
			}
			return "";
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 将Object类型 转换为String类型，若val为null 或 “”，则return String类型默认值def
	 *
	 * @param val
	 * @param def
	 * @return
	 */
	public static String ObjectToString(Object val, String def) {
		try {
			if (val != null) {
				String str = val.toString();
				if (!"".equals(str.trim()))
					return str;
			}
			return def;
		} catch (Exception e) {
			return def;
		}
	}

	/**
	 * 将Object类型转换为Long类型，若val为null 或 “”，则return 0L
	 *
	 * @param val
	 * @return
	 */
	public static Long ObjectToLong(Object val) {
		try {
			if (val != null) {
				String str = val.toString();
				if (!"".equals(str.trim())) {
					return Long.parseLong(str);
				}
			}
			return 0L;
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * 将Object类型转换为Long类型，若val为null 或 “”，则return Long类型默认值defaultVal
	 *
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static long ObjectToLong(Object val, long defaultVal) {
		try {
			if (val != null) {
				String str = val.toString();
				if (!"".equals(str.trim())) {
					return Long.parseLong(val.toString());
				}
			}
			return defaultVal;
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/************************ 转码 ******************************/
	/**
	 * 转编码到UTF-8
	 *
	 * @param s
	 * @return
	 */
	public static String toUTF8(String s) {
		if (s != null && !"".equals(s)) {
			try {
				s = new String(s.getBytes("iso-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return removeNull(s);
	}

	/**
	 * 返回前一天日期
	 *
	 * @param date
	 * @return
	 */
	public static String getPreviousDate(String date) {
		DateFormat df = DateFormat.getDateInstance();
		Calendar c = Calendar.getInstance();
		try {
			Date d = df.parse(date);
			c.setTimeInMillis(d.getTime() - 1 * 24 * 3600 * 1000);
			return formatTime("yyyy-MM-dd", c.getTimeInMillis());
		} catch (Exception err) {
			System.err.println("error in getPreviousDate : " + err);
		}
		return "";
	}

	/**
	 * format time as HH:mm:ss
	 *
	 * @param pattern
	 * @return
	 */
	public static String formatTime(String pattern, long t) {
		if (t <= 0)
			return "";
		String r = "";
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		try {
			Date date = new Date(t);
			r = sdf.format(date);
		} catch (Exception err) {
			System.err.println("error in formatTime(String,long) " + err);
			r = "";
		}
		return r;
	}

	/**
	 *
	 * 匹配中文
	 * 
	 * @param str
	 * @return true \ false
	 */
	public static boolean matcherChinese(String str) {
		String pstr = "[\u4E00-\u9FA5]";
		Pattern p = Pattern.compile(pstr);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/************************ 日期处理 ******************************/

	/**
	 * 得到当前时间
	 *
	 * @return
	 */
	public static String getNowTime() {
		return formatDateTime(new Date());
	}

	/**
	 * 得到yyyy-MM-dd HH:mm:ss格式的时间显示
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return outFormat.format(date);
	}

	/**
	 * 得到yyyy-MM-dd格式的日期显示
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
		return outFormat.format(date);
	}

	/**
	 * String类型转换为Date类型数据 若date为“”或null，则return null；反之得到yyyy-MM-dd格式的Date类型日期
	 *
	 * @param date
	 * @return
	 */
	public static Date formatStrDate(String date) {
		if ("".equals(removeNull(date)))
			return null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return (Date) df.parseObject(date);
		} catch (ParseException e) {
			// System.out.print("[SYS] " + e.getMessage());
			return null;
		}
	}

	/**
	 * String类型转换为Date类型数据 若date为“”或null，则return null；反之得到yyyy-MM-dd
	 * HH:mm:ss格式的Date类型日期
	 *
	 * @param date
	 * @return
	 */
	public static Date formatStrDatetime(String date) {
		if ("".equals(removeNull(date)))
			return null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return (Date) df.parseObject(date);
		} catch (ParseException e) {
			// System.out.print("[SYS] " + e.getMessage());
			return null;
		}
	}

	/**
	 * 得到日期的年数值
	 *
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.YEAR);
	}

	/**
	 * 得到日期的月数值
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MONTH);
	}

	/**
	 * 补充图书条形码 津南区图书馆 阿里夫
	 * 
	 * @param bibnumber
	 * @return
	 */
	public static String getStanrdBibNum(String bibnumber) {
		String result = "";
		int length = bibnumber.length();
		int size = 0;
		if (length < 9) {
			size = 9 - length;
			for (int i = 0; i < size; i++) {
				result = "0" + result;
			}
		}
		result = result + bibnumber;
		return result;
	}

	public static Date formatStrDate2(String date) {
		if ("".equals(removeNull(date)))
			return null;
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			return (Date) df.parseObject(date);
		} catch (ParseException e) {
			// System.out.print("[SYS] " + e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(decode("MDAxMTAwMQ=="));
	}

	/**
	 * 判断为空
	 * 
	 * @param args
	 * @return
	 */
	public static boolean isBlank(String args) {
		if (args == null || args.length() == 0 || "".equals(args) || " ".equals(args) || "null".equals(args)
				|| "undefined".equals(args)) {
			return true;
		}
		return false;
	}

	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}

	public static String encode(String s) {
		Base64 base64 = new Base64();
		byte[] enbytes = null;
		String encodeStr = null;
		try {
			enbytes = base64.encode(s.getBytes());
			encodeStr = new String(enbytes);
			return encodeStr;
		} catch (Exception err) {
			System.err.println("error in encode: \n" + err);
		}
		return "";
	}

	public static String decode(String s) {
		Base64 base64 = new Base64();
		byte[] debytes = null;
		String decodeStr = null;
		try {
			byte[] enbytes = s.getBytes();
			debytes = base64.decode(enbytes);
			decodeStr = new String(debytes);
			return decodeStr;
		} catch (Exception err) {
			System.err.println("error in decode: \n" + err);
		}
		return "";
	}

	public static Object getAgeByBirthday(String birthday) {
		int year = ObjectToInteger(birthday.substring(0, 4));
		int age = 0;
		if (year > 1900 && year < 2050) {
			age = getYear(new Date()) - year;
			age = age + 1;
		}
		return age;
	}

	public static String getParameter(HttpServletRequest request, String param) {
		return removeNull(request.getParameter(param));
	}

	public static int countTotalPage(int paramInt1, int paramInt2) {
		int i = paramInt2 % paramInt1 == 0 ? paramInt2 / paramInt1 : paramInt2 / paramInt1 + 1;
		return i;
	}

	/**
	 * 得到yyyyMMdd格式的日期显示
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate_yyyyMMdd(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMdd");
		return outFormat.format(date);
	}

	/**
	 * 得到yyyyMMddHHmmss格式的日期显示
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime_yyyyMMddHHmmss(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return outFormat.format(date);
	}
	
	/**
	 * 
	 * 返回Json 处理串
	 * 
	 * @param response
	 *            响应
	 * @param request
	 *            请求
	 * @param flag
	 *            行为状态
	 * @param msg
	 *            
	 */
	public static void getJsonMsg2(HttpServletResponse response, HttpServletRequest request, boolean flag, String msg) {
		StringBuffer sb = new StringBuffer("");
		sb.append("{\"success\":" + flag + ",\"message\":\"" + msg + "\"}");
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(sb.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算指定日期前后多少天的日期
	 * @author ysq
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static String getTimesAfterDays(Object date, int days) {
		if (null == date) {
			return "";
		} else {
			java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
			if(date instanceof Date){
				calendar.setTime((Date)date);
			}else if(date instanceof String){
				calendar.setTime(formatStrDatetime(date.toString()));
			}else{
				return "";
			}
			calendar.add(Calendar.DATE, days);
			String v = formatDateTime(calendar.getTime());
			if (v.lastIndexOf(":") > 0) {
				return v.substring(0, v.lastIndexOf(":")) + ":00";
			}
			return v;
		}
	}

	/**
	 * 根据日期获取当前的星期
	 * @author ysq
	 *
	 * @param date
	 * @return
	 */
	public static String getWeekByDate(Object date){
		String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar calendar = Calendar.getInstance();
		if(date != null){
			if(date instanceof Date){
				calendar.setTime((Date)date);
			}else if(date instanceof String){
				calendar.setTime(formatStrDatetime(date.toString()));
			}else {
				return "";
			}
		}

		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
			w = 0;
		}
		return weekOfDays[w];
	}

	/**
	 * 将日期时间转换成对应的格式返回
	 * @author ysq
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDateByPattern(Date date,String pattern) {
		try {
			SimpleDateFormat outFormat = new SimpleDateFormat(pattern);
			return outFormat.format(date);
		} catch (Exception e) {
			System.out.println("日期格式转换异常.....");
			e.printStackTrace();
		}
		return formatDateTime_yyyyMMddHHmm(date);
	}

	/**
	 * 得到yyyy-MM-dd HH:mm格式的日期显示
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTime_yyyyMMddHHmm(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return outFormat.format(date);
	}

	public static boolean sendValidateCode(String phone,String msg){
		try {
			StringBuffer sb = new StringBuffer("http://m.5c.com.cn/api/send/?");
			sb.append("apikey=784bc18b3f9c2dcc262a1c4488233440");
			sb.append("&username=hbyzm").append("&password=hbsx888888").append("&mobile="+phone);
			sb.append("&content="+ URLEncoder.encode(msg,"GBK"));

			// 创建url对象
			URL url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");

			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			// 返回发送结果
			String inputline = in.readLine();

			// 输出结果
			System.out.println("11111111111111111111111111++++++++++++++++++++:"+inputline);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据城市名称获取该地区天气
	 * @param cityName
	 * @return
	 */
	public static String  getWeatherInform(String cityName){
		//百度天气API
		String baiduUrl = "http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=W69oaDTCfuGwzNwmtVvgWfGH";
		StringBuffer strBuf;

		try {
			//通过浏览器直接访问http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ
			//5slgyqGDENN7Sy7pw29IUvrZ 是我自己申请的一个AK(许可码)，如果访问不了，可以自己去申请一个新的ak
			//百度ak申请地址：http://lbsyun.baidu.com/apiconsole/key
			//要访问的地址URL，通过URLEncoder.encode()函数对于中文进行转码
			baiduUrl = "http://api.map.baidu.com/telematics/v3/weather?location="+URLEncoder.encode(cityName, "utf-8")+"&output=json&ak=GiiuZ4It4n0yEdh0wOO1MrCu";
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		strBuf = new StringBuffer();

		try{
			URL url = new URL(baiduUrl);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//转码。
			String line = null;
			while ((line = reader.readLine()) != null)
				strBuf.append(line + " ");
			reader.close();
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

		return strBuf.toString();
	}
}
