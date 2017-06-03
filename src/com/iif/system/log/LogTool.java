package com.iif.system.log;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.hxjz.common.core.SpringTool;
import com.hxjz.common.utils.SystemConstant;
import com.iif.system.log.entity.LoginLog;
import com.iif.system.log.entity.OperationLog;
import com.iif.system.log.service.impl.LoginLogServiceImpl;
import com.iif.system.log.service.impl.OperationLogServiceImpl;
import com.iif.system.userManager.entity.User;

/**
 * 日志工具类
 * 
 * <p>@author : LiuM</p>
 * <p>@createDate : 2014-08-28</p>
 */
@SuppressWarnings("unchecked")
public class LogTool {
	protected final static Logger logger = LoggerFactory.getLogger("系统访问信息");

	static OperationLogServiceImpl operationLogServiceService = (OperationLogServiceImpl) SpringTool.getBean(OperationLogServiceImpl.class);
	
	/**
	 * 记录登录日志
	 * 
	 * @param request
	 * @param user
	 */
	public static Long saveLoginLog(HttpServletRequest request, User user) {
		LoginLogServiceImpl loginLogService = (LoginLogServiceImpl) SpringTool.getBean(LoginLogServiceImpl.class);
		LoginLog loginLog = new LoginLog();
		loginLog.setUserName(user.getName());
		loginLog.setLoginTime(new Date());
		loginLog.setLoginIp(request.getRemoteHost());
		if (null != user.getOrg()) {
			loginLog.setOrgName(user.getOrg().getName());
		}
		loginLogService.save(loginLog);
		
		return loginLog.getId();
	}

	/**
	 * 系统保存操作日志
	 * 
	 * @param request
	 * @param methodName
	 * @param isSuccess
	 */
	public static void saveOperationLog(User user, String methodName, Integer isSuccess) {
		
		Map<String, String> map = (Map) SystemConstant.logProperties.get(methodName);
		if (map != null) {
			if (null == user) {
				return;
			}
			OperationLog operationLog = new OperationLog();
			String[] values = map.get("value").split("--");
			if (values.length != 2) {
				logger
						.error("日志配置不正确，请重新配置!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
			}
			String idName = map.get("idName");
			String operationTypeCode = map.get("type");
			operationLog.setIdName(idName);
			operationLog.setOperationTypeCode(operationTypeCode);
			operationLog.setOrgName(user.getOrgName());
			operationLog.setBusinessName(values[0]);
			operationLog.setOperationName(values[1]);
			operationLog.setOperationTime(new Date());
			operationLog.setLoginLogId(user.getLoginLogId());
			operationLog.setUserName(user.getName());
			operationLog.setIsSuccess(isSuccess);
			operationLogServiceService.save(operationLog);
		}
	}
	
	/**
	 * 保存HR日志
	 * @param isSuccess
	 * @param description
	 */
	public static void saveHRLog(String description) {
		
		OperationLog operationLog = new OperationLog();
		operationLog.setBusinessName(description);
		operationLog.setOperationTime(new Date());
		operationLogServiceService.save(operationLog);
	}
	
	/**
	 * 保存日志信息
	 * @param data
	 * @param operatorFlag
	 * @param id
	 */
	public static void saveOneDataObject(Object data, String operatorFlag, String id) {
		OperationLog operationLog = new OperationLog();			
		operationLog.setOperationName(operatorFlag);
		operationLog.setOperationTime(new Date());
		operationLog.setIdValue(id);
		
		if(data instanceof Class ){// 无须保存
			operationLog.setEntityName( ((Class)data ).getSimpleName() );
		} else 
		if (data != null) {			
			if("fail".equals(operatorFlag)){//java 异常
				operationLog.setIsSuccess(0);
				operationLog.setBusinessName(data.toString());
			} else if(operatorFlag !=null && operatorFlag.startsWith("House") ){//只更新ID列表
				
				operationLog.setEntityName("HmHouse");
				operationLog.setOperationTypeCode( id );				
				operationLog.setIdValue( operatorFlag.substring(6) );//更新ID数量
				
				if(operatorFlag.equals("HouseD")) operationLog.setIsSuccess(0);// 删除操作
				else operationLog.setIsSuccess(1);
				operationLog.setBusinessName(data.toString());// 分号分隔的多个ID列表
			} else {
				Method med = ReflectionUtils.findMethod( data.getClass(), "getBatch");
				if(med !=null){
					operationLog.setOperationTypeCode((String)ReflectionUtils.invokeMethod(med, data));
				}
				operationLog.setEntityName(data.getClass().getSimpleName());
				String contr = ToStringBuilder.reflectionToString(data);
				operationLog.setBusinessName(contr);
			}
		} 
		operationLogServiceService.save(operationLog);
	}

	/**
	 * 加载系统日志文件
	 */
	public static void initLog() {
		/*Document document = XmlDom4j.loadFile("log-config.xml");
		Element element = null;
		List<Element> logElementList = XmlDom4j.getRootElement(document)
				.selectNodes("log");
		String key = null;
		String value = null;
		String operationTypeCode = null;
		String tableName = null;
		String entityName = null;
		String idName = null;
		String parameterName = null;
		for (int i = 0; i < logElementList.size(); i++) {
			element = logElementList.get(i);
			key = element.attributeValue("key");
			value = element.attributeValue("value");
			operationTypeCode = element.attributeValue("type");
			tableName = element.attributeValue("table");
			entityName = element.attributeValue("entity");
			idName = element.attributeValue("idName");
			parameterName = element.attributeValue("parameterName");
			Map map = new HashMap();
			map.put("value", value);
			map.put("type", operationTypeCode);
			map.put("table", tableName);
			map.put("entity", entityName);
			map.put("idName", idName);
			map.put("parameterName", parameterName);
			SystemConstant.logProperties.put(key, map);
		}*/
	}
	
	/**
	 * 通过反射，将Object对象中所有属性的值都记录日志
	 * @param obj
	 * @param operation
	 */
	public static void logObjectAllInfo(Object obj , String operation){
		if(obj != null){
			String info = ToStringBuilder.reflectionToString(obj) ;
			info = "{" + operation + ":[" + info  + "]}" ;
			saveHRLog(info) ;
		}
	}

}
