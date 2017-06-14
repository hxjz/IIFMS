package com.iif.common.util;

/**
 * 系统常量
 * 
 * @author LiuM
 */
public class SysConstant {
	// redisIp地址
	public static final String REDISIP = "127.0.0.1";
	/////////////////////////
	public static final int RESOURCE_IS_LEAF_Y = 1;
	public static final int RESOURCE_IS_LEAF_N = 0;
	public static final int RESOURCE_TYPE_1 = 1;
	
	public static final int RESOURCE_IS_LINE_Y = 1;
	public static final int RESOURCE_IS_LINE_N = 0;
	
	public static final int RESOURCE_LEVEL_NUM_1 = 1;
	public static final int RESOURCE_LEVEL_NUM_3 = 3;
	
	public static final String RESOURCE_ROOT_ID = "1";
	/////////////////////////
	
	public final static String admin_name = "admin"; // 管理员名称

	public final static String administrator_name = "administrator"; // 管理员名称
	
	public final static String iifadmin_name = "IIFadmin" ; // 管理员名称

	public final static String SYSTEM_CON_ZER = "0";// 0
	public final static String SYSTEM_CON_ONE = "1";// 1
	public final static String SYSTEM_CON_TWO = "2";// 2
	public final static String SYSTEM_CON_THR = "3";// 3
	public final static String SYSTEM_CON_FUR = "4";// 4
	public final static String SYSTEM_CON_FIV = "5";// 5
	public final static String SYSTEM_CON_SIX = "6";// 6
	public final static String StringZERO = "0.00";//

	public final static String SYSTEM_USER_ID = "system_userId";// 默认系统用户
	
	public static final String CURRENT_ROLE = "CURRENT_ROLE";// 当前角色

	// 下拉框option显示文本
	public final static String SELECT_OPTION_TEXT = "value";

	// 下拉框option取值value
	public final static String SELECT_OPTION_VALUE = "key";

	// 从自定义枚举中获取Map调用方法(自定义枚举中map取名必须为getEnumMap)
	public final static String GET_SELECT_MAP = "getEnumMap";
	
	
	//菜单属性
	public final static String MENU_ATTR = "attr";

	//菜单ID
	public final static String MENU_ID = "id";

	//菜单标题
	public final static String MENU_TITLE = "data";

	//菜单展开状态
	public final static String MENU_STATE = "state";

	//菜单关闭
	public final static String MENU_CLOSE = "closed";

	//菜单class
	public final static String MENU_CLASS = "CLASS";

	//菜单选中
	public final static String MENU_CHECKED = "jstree-checked";

	//秘钥
	public final static String SECRET_KEY="com.hxjz";
	
	//cookie key 
	public final static String COOKIE_KEY_TOKEN="iifms_token";

	// 空格分隔符
	public final static String SPLIT_CHAR_BLANK = " ";

	public final static String SPLIT_CHAR_LINE = "_";

	// 物理删除
	public final static boolean REAL_DELETE = true;

	// 模板根路径
	public final static String TEMPLATE_ROOT_PATH = "ftl_path";

	// list size
	public final static String TEMPLATE_RESULT_TOTAL = "total";

	// 模板解析的list
	public final static String TEMPLATE_RESULT_LIST = "list";

	// 报表url
	public final static String REPORT_URL = "report.url";
	// 模板解析字符设置
	public final static String ENCODING_UTF = "UTF-8";

	public final static String ENCODING_GBK = "GBK";

	public final static String ENCODING_ISO = "iso-8859-1";

	public final static String DATE_FORMAT_MMDD = "MMdd";
	// 时间格式 精确到月
	public final static String DATE_FORMAT_MONTH = "yyyy-MM";

	// 时间格式 精确到日期
	public final static String DATE_FORMAT_DAY = "yyyy-MM-dd";

	// 时间格式 精确到分钟
	public final static String DATE_FORMAT_MIN = "yyyy-MM-dd HH:mm";

	// 时间格式 精确到分钟(ORACLE)
	public final static String DATE_FORMAT_MIN_ORA = "yyyy-MM-dd hh24:mi";

	// 时间格式 精确到秒
	public final static String DATE_FORMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";

	// 域名
	public final static String DOMAIN = "xxxx.com";

	// cookie key
	public final static String COOKIE_KEY_USER_NAME = "iif_userName";

	// 发送方式
	public final static int AUTOSEND = 1; // 自动发送
	public final static int HANDSEND = 0;// 手动发送
	// 发送成功/失败
	public final static int SENDFALSE = 1;// 发送失败
	public final static int SENDTRUE = 0;// 发送成功
	// 发送失败类型
	public final static int INVALIDFORMAT = 1;// 格式错误
	public final static int UNKNOWERROR = 2;// 未知错误
	public final static int NONE = 0;// 无

	// 路径符号
	public final static String CHAR_SPLIT = "/";
	public final static String CHAR_POINT = ".";

	// 图片格式
	public final static String PIC_FLG = "image";
	public final static String PIC_JPG = "jpg";
	public final static String PIC_BMP = "bmp";
	public final static String PIC_PNG = "png";
	public final static String PIC_JPEG = "pjpeg";

	// 邮件--短信
	public static final int IS_ENABLE = 1; // 可用
	public static final int IS_UNENABLE = 0; // 不可用
	public static final int IS_DEL = 1; // 已删除
	public static final int IS_NOT_DEL = 0; // 未删除
	public static final String IS_NOT_CONFIRMED = "0"; // 没有确认
	public static final String IS_CONFIRMED = "1"; // 已确认

	// 邮件发送相关
	public static final int EMAIL_IMMEDIATELY_SEND = 1; // 立即发送
	public static final int EMAIL_CRONTAB_SEND = 2; // 定时任务
	public static final int EMAIL_SIMPLE_TYPE = 1; // 简单邮件
	public static final int EMAIL_TEMPLATE_TYPE = 2; // 带有模版邮件
	
	// baseSource
	public static final int ITEM_NOT_USE = 0; // 停用
	public static final int ITEM_USE = 1; // 在用
	
	
	public static final String MD5KEY = "iifuse2017";//Md5 加密Key后缀

	public static final String CURRENT_SESSION_USER = "currentUser"; // 当前用户在session中的KEY

	// 出入库相关
	public static final int STOCK_STATE_NONE = 1; // 登记
	public static final int STOCK_STATE_IN = 2; // 入库  在库
	public static final int STOCK_STATE_OUT = 3; // 出库  不在库
	public static final String STOCK_IN_EXCEL_NAME = "/财物出入库审批表V1.xls";
	public static final String STOCK_IN_EXCEL_NAME2 = "/财物出入库审批表print.xls";
}
