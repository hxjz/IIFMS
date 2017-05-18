package com.iif.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.hxjz.common.utils.HttpTool;

/**
 * HTTP request工具类 LiuM
 * 
 */
public class HttpUtil {

	/**
	 * 获取HttpSession
	 * @return
	 */
	public static HttpSession getSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	/**
	 * 获取HttpServletRequest
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取HttpServletResponse
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		return  ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * 获取参数
	 * 
	 * @param name
	 * @return
	 */
	public static String getParameter(String name) {
		HttpServletRequest request = getRequest();

		if (StringUtils.isBlank(name)) {
			throw new NullPointerException("参数名不能为空！");
		}

		return request.getParameter(name);
	}
	
	/**
	 * 获取参数Map
	 * 
	 * @return
	 */
	public static Map<?,?> getParametersMap() {
		HttpServletRequest request = getRequest();
		Map<?,?> rtnMap = request.getParameterMap();
		return rtnMap;
	}

	/**
	 * 获取参数
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getParameter(String name, String defaultValue) {
		String value = HttpTool.getParameter(name);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static Integer getIntegerParameter(String name) {
		String value = HttpTool.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	public static Integer getIntegerParameter(String name, Integer defaultValue) {
		Integer value = HttpTool.getIntegerParameter(name);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static Long getLongParameter(String name) {
		String value = HttpTool.getParameter(name);
		if (StringUtils.isEmpty(value)) {
			return Long.parseLong(value);
		}
		return null;
	}

	public static Long getLongParameter(String name, Long defaultValue) {
		Long value = HttpTool.getLongParameter(name);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static Short getShortParameter(String name) {
		String value = HttpTool.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Short.parseShort(value);
		}
		return null;
	}

	public static Short getShortParameter(String name, Short defaultValue) {
		Short value = HttpTool.getShortParameter(name);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static Float getFloatParameter(String name) {
		String value = HttpTool.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Float.parseFloat(value);
		}
		return null;
	}

	public static Float getFloatParameter(String name, Float defaultValue) {
		Float value = HttpTool.getFloatParameter(name);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static Double getDoubleParameter(String name) {
		String value = HttpTool.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}

	public static Double getDoubleParameter(String name, Double defaultValue) {
		Double value = HttpTool.getDoubleParameter(name);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static Boolean getBooleanParameter(String name) {
		String value = HttpTool.getParameter(name);
		if (value != null) {
			Boolean reval = Boolean.valueOf(value);
			return reval;
		}
		return null;
	}

	public static Boolean getBooleanParameter(String name, Boolean defaultValue) {
		Boolean value = HttpTool.getBooleanParameter(name);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static String[] getParameterValues(String name) {
		HttpServletRequest request = null;//ServletActionContext.getRequest();
		if (StringUtils.isBlank(name)) {
			throw new NullPointerException("参数名不能为空！");
		}

		return request.getParameterValues(name);
	}

	public static String[] getParameterValues(String name,
			String[] defaultValues) {
		String[] values = HttpTool.getParameterValues(name);
		if (values == null) {
			return defaultValues;
		}
		return values;
	}

	public static Integer[] getIntegerParameterValues(String name) {
		String[] values = HttpTool.getParameterValues(name);
		if (values == null) {
			return null;
		}
		int len = values.length;
		Integer[] intValues = new Integer[len];
		for (int i = 0; i < len; i++) {
			intValues[i] = Integer.parseInt(values[i]);
		}
		return intValues;
	}

	public static Integer[] getIntegerParameterValues(String name,
			Integer[] defaultValues) {
		Integer[] values = HttpTool.getIntegerParameterValues(name);
		if (values == null) {
			return defaultValues;
		}
		return values;
	}

	public static Long[] getLongParameterValues(String name) {
		String[] values = HttpTool.getParameterValues(name);
		if (values == null) {
			return null;
		}
		int len = values.length;
		Long[] intValues = new Long[len];
		for (int i = 0; i < len; i++) {
			intValues[i] = Long.parseLong(values[i]);
		}
		return intValues;
	}

	public static Long[] getLongParameterValues(String name,
			Long[] defaultValues) {
		Long[] values = HttpTool.getLongParameterValues(name);
		if (values == null) {
			return defaultValues;
		}
		return values;
	}

	public static Short[] getShortParameterValues(String name) {
		String[] values = HttpTool.getParameterValues(name);
		if (values == null) {
			return null;
		}
		int len = values.length;
		Short[] intValues = new Short[len];
		for (int i = 0; i < len; i++) {
			intValues[i] = Short.parseShort(values[i]);
		}
		return intValues;
	}

	public static Short[] getShortParameterValues(String name,
			Short[] defaultValues) {
		Short[] values = HttpTool.getShortParameterValues(name);
		if (values == null) {
			return defaultValues;
		}
		return values;
	}

	public static Float[] getFloatParameterValues(String name) {
		String[] values = HttpTool.getParameterValues(name);
		if (values == null) {
			return null;
		}
		int len = values.length;
		Float[] intValues = new Float[len];
		for (int i = 0; i < len; i++) {
			intValues[i] = Float.parseFloat(values[i]);
		}
		return intValues;
	}

	public static Float[] getFloatParameterValues(String name,
			Float[] defaultValues) {
		Float[] values = getFloatParameterValues(name);
		if (values == null) {
			return defaultValues;
		}
		return values;
	}

	public static Double[] getDoubleParameterValues(String name) {
		String[] values = getParameterValues(name);
		if (values == null) {
			return null;
		}
		int len = values.length;
		Double[] intValues = new Double[len];
		for (int i = 0; i < len; i++) {
			intValues[i] = Double.parseDouble(values[i]);
		}
		return intValues;
	}

	public static Double[] getDoubleParameterValues(String name,
			Double[] defaultValues) {
		Double[] values = HttpTool.getDoubleParameterValues(name);
		if (values == null) {
			return defaultValues;
		}
		return values;
	}

	public static Date getDateParameter(String name) throws ParseException {
		String value = HttpTool.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.parse(value);
		}
		return null;
	}

	public static Date getDateParameter(String name, Date defaultValue)
			throws ParseException {
		Date value = HttpTool.getDateParameter(name);
		if (value == null)
			return defaultValue;
		return value;
	}

	/**
	 * 封装request设置参数
	 * 
	 * @param name
	 * @param o
	 */
	public static void setAttribute(String name, Object o) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute(name,  o);
	}

	/**
	 * 获取当前IP
	 * @return
	 * @throws ParseException
	 */
	public static String getCurrentIp()throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getRemoteAddr();
	}
}