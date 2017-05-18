package com.iif.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hxjz.common.utils.StrUtil;
import com.hxjz.common.utils.SystemConstant;
import com.iif.system.code.entity.Dictionary;


/**
 * 通过自定义枚举初始化下拉列表
 * @author LiuM
 * @version 2017
 *
 */
public class InitSelect {
	
	/**
	 * 
	 * 初始化下拉列表值
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getSelectList(Class enumClass) {
		Map rtnLstMap=getSelectMap(enumClass);
		List<Object> rtnLst = new ArrayList<Object>();
		for (Iterator iterator = rtnLstMap.keySet().iterator(); iterator.hasNext();) {
			Map addMap = new HashMap();
			Object name = iterator.next();
			addMap.put(SysConstant.SELECT_OPTION_VALUE, name);
			addMap.put(SysConstant.SELECT_OPTION_TEXT, rtnLstMap.get(name));
			rtnLst.add(addMap);
		}
	
		return rtnLst;
	}
	
	/**
	 * 
	 * 初始化下拉列表值，过滤作废数据和空数据
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getValidList(Class enumClass) {
		Map rtnLstMap=getSelectMap(enumClass);
		List<Object> rtnLst = new ArrayList<Object>();
		for (Iterator iterator = rtnLstMap.keySet().iterator(); iterator.hasNext();) {
			Object name = iterator.next();
			String text=rtnLstMap.get(name).toString();
			if (StringUtils.isBlank(text)||text.indexOf("废")!=-1) {
				continue;
			}
			Map addMap = new HashMap();
			addMap.put(SysConstant.SELECT_OPTION_VALUE, name);
			addMap.put(SysConstant.SELECT_OPTION_TEXT, text);
			rtnLst.add(addMap);
		}
	
		return rtnLst;
	}
	
	/**
	 * 实例化枚举，返回map
	 * @param enumClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map getSelectMap(Class enumClass) {
		if(StrUtil.isNullOrBlank(enumClass))
			return null;
		String strClazz=enumClass.getName();
		LinkedHashMap enumMap=null;
		try {
			Class c = Class.forName(strClazz);
			Object o = c.newInstance();
			Method method = o.getClass().getMethod(SysConstant.GET_SELECT_MAP);
			enumMap = (LinkedHashMap)method.invoke(o);//反射获取
			return enumMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实例化枚举，返回map
	 * @param enumClass
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getSelectMapByEnumName(String enumName) {
		if(StrUtil.isNullOrBlank(enumName))
			return null;

		Map enumMap = new LinkedHashMap();
		//缓存开关
		boolean redisOn = Boolean.valueOf(SystemConstant.getSystemConstant("redis_on"));
		redisOn=false;
		List<Dictionary> enumList=null;
		if (redisOn) {
			
			enumList=(List)RedisUtil.getObject(enumName);
			if (enumList==null||enumList.size()==0) {
				// 如果从缓存里取不到，则从数据库里面取
				enumList=EnumUtil.getDictionaryFromDB(enumName);
				RedisUtil.setObject(enumName, enumList);
			}
			
		} else {
			enumList=EnumUtil.getDictionaryFromDB(enumName);
		}

		for (int i = 0; i < enumList.size(); i++) {
			Dictionary dic=enumList.get(i);
			try {
				enumMap.put(Integer.valueOf(dic.getKey()), dic.getValue());
			} catch (Exception e) {
				enumMap.put(dic.getKey(), dic.getValue());
			}
		}
		return enumMap;
	}

	/**
	 * 
	 * 初始化下拉列表值，过滤作废数据和空数据
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> getValidListByEnumName(String enumName) {
		Map rtnLstMap=getSelectMapByEnumName(enumName);
		List<Map> rtnLst = new ArrayList<Map>();
		for (Iterator iterator = rtnLstMap.keySet().iterator(); iterator.hasNext();) {
			Object name = iterator.next();
			String text=rtnLstMap.get(name).toString();
			if (StringUtils.isBlank(text)||text.indexOf("废")!=-1) {
				continue;
			}
			Map addMap = new HashMap();
			addMap.put(SysConstant.SELECT_OPTION_VALUE, name);
			addMap.put(SysConstant.SELECT_OPTION_TEXT, text);
			rtnLst.add(addMap);
		}
		return rtnLst;
	}
	
	/**
	 * 初始化国籍列表
	 */
	@SuppressWarnings("rawtypes")
	public static List getAllNationName(){
		Locale[] list = Locale.getAvailableLocales();
		ArrayList<String> localeList = new ArrayList<String>();
		for (int i = 0; i < list.length; i++) {
            String str = list[i].getDisplayCountry();
            if (StringUtils.isNotEmpty(str)) {
            	str = list[i].getDisplayCountry();
                if(localeList.contains(str)){
                    continue;
                }else{
                    localeList.add(str);
                }
            }
        }
		return localeList;
	}
}
