package com.iif.common.enums;

import java.util.LinkedHashMap;

import com.iif.common.util.RedisUtil;

/**
 * 
 * 案件类型枚举
 * @author LiuM
 * @version V0.1
 * @date 2017
 */
public class CaseTypeEnum {
	static final public int TYPE1 = 1;// 危害国家安全罪
	static final public int TYPE2 = 2;// 危害公共安全罪
	static final public int TYPE3 = 3;// 放火案
	static final public int TYPE4 = 4;// 决水案
	static final public int TYPE5 = 5;// 爆炸案
	static final public int TYPE6 = 6;// 投毒案
	static final public int TYPE7 = 7;// 以其他方法危害公共安全案
	static final public int TYPE8 = 8;// 失火按
	static final public int TYPE9 = 9;// 过失决水案
	static final public int TYPE10 = 10;// 过失爆炸案
	static final public int TYPE11 = 11;// 过失投毒案
	static final public int TYPE12 = 12;// 过失以其他方法危害公共安全案
	static final public int TYPE13 = 13;// 破坏社会主义市场经济秩序案
	static final public int TYPE14 = 14;// 侵害公民人身权利、民主权利案
	static final public int TYPE15 = 15;// 故意杀人案
	static final public int TYPE16 = 16;// 过失致人死亡案
	static final public int TYPE17 = 17;// 故意伤害案
	static final public int TYPE18 = 18;// 过失致人重伤案
	static final public int TYPE19 = 19;// 强奸案
	static final public int TYPE20 = 20;// 绑架案
	static final public int TYPE21 = 21;// 侵犯财产案
	static final public int TYPE22 = 22;// 抢劫案
	static final public int TYPE23 = 23;// 盗窃案
	static final public int TYPE24 = 24;// 诈骗案
	static final public int TYPE25 = 25;// 抢夺案
	static final public int TYPE26 = 26;// 妨碍社会管理秩序案
	static final public int TYPE27 = 27;// 危害国防利益案
	static final public int TYPE28 = 28;// 贪污贿赂案
	static final public int TYPE29 = 29;// 渎职案
	static final public int TYPE30 = 30;// 军人违反职责案
	static final public int TYPE31 = 31;// 其他案件

	// 此名固定，不可修改
	@SuppressWarnings("rawtypes")
	public LinkedHashMap enumMap;

	@SuppressWarnings("rawtypes")
	public LinkedHashMap getEnumMap() {
		return enumMap;
	}

	// jsp中按照类似"value=0 text=全部"的规则来操作
	@SuppressWarnings("rawtypes")
	public CaseTypeEnum() {
		enumMap = new LinkedHashMap();
		RedisUtil.putEnumMap(enumMap, this);
	}
}
