package com.iif.common.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.SystemConstant;
import com.iif.system.code.entity.Dictionary;

/**
 * 连接和使用数据库资源的工具类
 * 
 * @author LiuM
 * @version 2017
 */
public class RedisUtil {
	/**
	 * 数据源 初始化spring注入
	 */
	private static ShardedJedisPool shardedJedisPool;

	public final static String KEY_PREFIX = "IIF_CACHE_";

	/**
	 * 获取数据库连接
	 * 
	 * @return conn
	 */
	public ShardedJedis getConnection() {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedis;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public void closeConnection(ShardedJedis jedis) {
		if (null != jedis) {
			try {
				shardedJedisPool.returnResource(jedis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置数据
	 * 
	 * @param conn
	 */
	public static boolean setData(String key, String value) {
		try {
			key = getKey(key);
			ShardedJedis jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
			shardedJedisPool.returnResource(jedis);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	/**
	 * 设置数据-设置时间
	 * 
	 * @param conn
	 * @param time seconds
	 */
	public static boolean setData(String key, String value, Integer time) {
		try {
			ShardedJedis jedis = shardedJedisPool.getResource();

			jedis.setex(KEY_PREFIX + key, time, value);

			shardedJedisPool.returnResource(jedis);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取数据
	 * 
	 * @param conn
	 */
	public static String getData(String key) {
		String value = null;
		try {
			ShardedJedis jedis = shardedJedisPool.getResource();
			key = getKey(key);
			value = jedis.get(key);
			shardedJedisPool.returnResource(jedis);
			return value;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return value;
	}

	/**
	 * 删除数据
	 * 
	 * @param key
	 * @return
	 */
	public static boolean deleteData(String key) {
		try {
			key = getKey(key);
			ShardedJedis jedis = shardedJedisPool.getResource();
			jedis.del(key);
			shardedJedisPool.returnResource(jedis);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * 获得对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object getObject(String key) {
		Object value = null;
		try {
			ShardedJedis jedis = shardedJedisPool.getResource();
			key = getKey(key);
			value = ObjectTranscoder.deserialize(jedis.get(key.getBytes()));
			shardedJedisPool.returnResource(jedis);
			return value;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return value;
	}

	/**
	 * 设置对象
	 * 
	 * @param key
	 * @return
	 */
	public static boolean setObject(String key, Object value) {
		try {
			key = getKey(key);
			ShardedJedis jedis = shardedJedisPool.getResource();
			jedis.set(key.getBytes(), ObjectTranscoder.serialize(value));
			shardedJedisPool.returnResource(jedis);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * 设置连接池
	 * 
	 * @return 设置数据源
	 */
	@SuppressWarnings("static-access")
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	/**
	 * 模糊查找所有key
	 * @param likeKey
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Set<String> getKeysLike(String likeKey) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		Collection<Jedis> jedisC = jedis.getAllShards();
		Iterator<Jedis> iter = jedisC.iterator();
		
		TreeSet<String> allKeys=new TreeSet<String>(new Comparator(){
			
			public int compare(Object arg0, Object arg1) {
				String preKey=(String)arg0;
				String key=(String)arg1;
				if (StringUtils.isBlank(preKey)||StringUtils.isBlank(key)) {
					return 0;
				}
				
				String [] preKeyChars=preKey.split(SysConstant.SPLIT_CHAR_LINE);
				String [] keyChars=key.split(SysConstant.SPLIT_CHAR_LINE);
				String lastPKC=new String(preKeyChars[preKeyChars.length-1]);
				String lastKC=new String(keyChars[keyChars.length-1]);
				
				try{
					int lastPKI=Integer.valueOf(lastPKC);
					int lastKI=Integer.valueOf(lastKC);
					if (lastPKI>lastKI) {
						return 1;
					}else if (lastPKI<lastKI) {
						return -1;
					}else{
						return 0;
					}
				}catch(Exception e){
					return 0;
				}
				
			}
			
		});

		likeKey = getKey(likeKey);
		while (iter.hasNext()) {
			Jedis _jedis = iter.next();
			Set<String> keys = _jedis.keys(likeKey + "*");
			for (String key : keys) {
				allKeys.add(key);
			}
		}
		return allKeys;
	}

	/**
	 * 获取连接池
	 * 
	 * @return 数据源
	 */
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	private static String getKey(String key) {
		return KEY_PREFIX + key;
	}

	/**
	 * 通过查询条件拼接缓存key add by wgh
	 * 
	 * @param prefix
	 * @param params
	 * @param page
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getKeyByConditions(String prefix, Map params, Page page) {
		if (params == null) {
			return null;
		}
		Set<String> keySet = params.keySet();
		StringBuilder sb = new StringBuilder(prefix
				+ SysConstant.SPLIT_CHAR_LINE);
		for (String key : keySet) {
			Object o = params.get(key);
			sb.append(key).append(o.toString());
		}
		if (page != null) {
			sb.append("pageNum").append(SysConstant.SPLIT_CHAR_LINE)
					.append(page.getPageNum()).append("pageSize")
					.append(SysConstant.SPLIT_CHAR_LINE)
					.append(page.getPageSize());
		}
		return sb.toString();
	}

	/**
	 * 将枚举值放入map中
	 * 
	 * @param enumMap 用于存储枚举的map
	 * @param key 键
	 * @param enumInstance 枚举实例
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void putEnumMap(Map enumMap, Object enumInstance) {

		if (enumInstance == null) {
			return;
		}

		String enumName = enumInstance.getClass().getSimpleName();

		//缓存开关
		boolean redisOn = Boolean.valueOf(SystemConstant
				.getSystemConstant("redis_on"));
		redisOn=false;
		List<Dictionary> enumList=null;
		if (redisOn) {
			
			enumList=(List)getObject(enumName);
			if (enumList==null||enumList.size()==0) {
				// 如果从缓存里取不到，则从数据库里面取
				enumList=EnumUtil.getDictionaryFromDB(enumName);
				setObject(enumName, enumList);
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
		
	}

}
