package com.iif.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 * 读取配置文件信息
 * @author LiuM
 * @date 2014-9-22
 * PropertiesUtil
 */
public class PropertiesUtil {

    /**
     * 获取class目录下的配置文件
     * 2014-1-10
     * @param fileName  class目录下的配置文件名
     * @return
     * Properties
     */
    public static Properties getProperties(String fileName) {
        Properties prop = new Properties();
        PropertiesUtil pu = new PropertiesUtil();
        InputStream in = pu.getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * 通过配置的文件的完整路径获取配置文件
     * 2014-1-10
     * @param filePath 配置文件的完整路径
     * @return
     * Properties
     */
    public static Properties getPropertiesByPath(String filePath) {
        Properties props = new Properties();
        InputStream propInput = null;

        try {
            propInput = new FileInputStream(filePath);
            props.load(propInput);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (propInput != null)
                    propInput.close();
            } catch (IOException e) {
                propInput = null;
            }
        }
        return props;
    }

    /**
     * 配置文件的信息写入 
     * 2014-1-10
     * @param p  proerties文件对象 
     * @param fileName  文件的完整路径
     * void
     */
    public static void persist(Properties p, String fileName) {
        OutputStream propOutput = null;
        Properties props = getPropertiesByPath(fileName);
        try {
            props.putAll(p);
            propOutput = new FileOutputStream(fileName);
            props.store(propOutput, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (propOutput != null)
                    propOutput.close();
            } catch (IOException e) {
                propOutput = null;
            }
        }
    }
   /**
    * 获取class目录下的properties配置文件的 健对应的值
    * 2014-1-9
    * @param fileName 配置文件名
    * @param keyStr 要获取值的健(字符串类型)
    * @return 
    * String
    */
    public static String getValueStrByKey(String fileName,String keyStr){
    	Properties pro = getProperties(fileName);
    	return pro.getProperty(keyStr);
    }
    /**
     * 获取class目录下的properties配置文件的 健对应的值
     * 2014-1-9
     * @param fileName 配置文件名
     * @param keyStr  要获取值的健(整数类型)
     * @return
     * int
     */
    public static int getIntegerValueByKey(String fileName,String keyStr){
    	Properties pro = getProperties(fileName);
    	return Integer.valueOf(pro.getProperty(keyStr));
    }
}
