package com.allenfancy.memcached.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;

public class MemcachedUtils {

	private static final Logger logger = Logger.getLogger(MemcachedUtils.class);
	
	private static MemCachedClient cachedClient;
	
	static {
		if(cachedClient == null){
			cachedClient = new MemCachedClient();
		}
	}
	private MemcachedUtils(){
		
	}
	
	public static boolean set(String key,Object value){
		return setExp(key,value,null);
	}
	
	public static boolean set(String key,Object value,Date expire){
		return setExp(key,value,expire);
	}
	
	private static boolean setExp(String key,Object value,Date expire){
		boolean flag = false;
		try{
			flag = cachedClient.set(key,value,expire);
		}catch(Exception e){
			MemcahedLog.writeLog("Memcached set方法报错,key值："+ key +"\r\n" +  exceptionWrite(e));
		}
		return flag;
	}
	
	public static boolean replace(String key,Object value){
		return replaceExp(key,value,null);
	}

	public static boolean replace(String key,Object value,Date expire){
		return replaceExp(key,value,expire);
	}
	
	private static boolean replaceExp(String key,Object value,Date expire){
		boolean flag = false;
		try{
			flag = cachedClient.replace(key,value,expire);
		}catch(Exception e){
			MemcahedLog.writeLog("Memcached replace 方法报错，key值："+key +"\r\n" + exceptionWrite(e));
		}
		return flag;
	}
	
	public static Object get(String key){
		Object obj = null;
		try{
			obj = cachedClient.get(key);
		}catch(Exception e){
			MemcahedLog.writeLog("Memcahed get方法报错，key值 ：" + key + "\r\n" + exceptionWrite(e));
		}
		return obj;
	}
	
	public static boolean delete(String key){
		return deleteExp(key,null);
	}
	
	public static boolean delete(String key,Date expire){
		return deleteExp(key,expire);
	}
	
	private static boolean deleteExp(String key,Date expire){
		boolean flag = false;
		try{
			flag = cachedClient.delete(key,expire);
		}catch(Exception e){
			MemcahedLog.writeLog("Memcached delete 方法报错，key值："+ key + "\r\n" + exceptionWrite(e));
		}
		return flag;
	}
	public static boolean flashAll() {  
        boolean flag = false;  
        try {  
            flag = cachedClient.flushAll();  
        } catch (Exception e) {  
        	MemcahedLog.writeLog("Memcached flashAll方法报错\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 返回异常栈信息，String类型 
     *  
     * @param e 
     * @return 
     */  
    private static String exceptionWrite(Exception e) {  
        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw);  
        e.printStackTrace(pw);  
        pw.flush();  
        return sw.toString();  
    }  
    
    private static class MemcahedLog{
    	private final static String MEMCACHED_LOG = "";
    	private final static String LINUX_MEMCACHED_LOG = "";
    	private static FileWriter fileWriter;
    	private static BufferedWriter logWriter;
    	
    	private final static RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
    	
    	private final static String PID = runtime.getName();
    	
    	static{
    		try{
    			String osName = System.getProperty("os.name");
    			if(osName.indexOf("windows")==-1){
    				fileWriter = new FileWriter(MEMCACHED_LOG,true);
    			}else{
    				fileWriter = new FileWriter(LINUX_MEMCACHED_LOG,true);
    			}
    			logWriter = new BufferedWriter(fileWriter);
    		}catch(IOException e){
    			logger.error("memcached 日志初始化失败",e);
    			closeLogStream();
    		}
    	}
    	 /** 
         * 写入日志信息 
         *  
         * @param content 
         *            日志内容 
         */  
        public static void writeLog(String content) {  
            try {  
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                logWriter.write("[" + PID + "] " + "- [" + sdf.format(new Date()) + "]\r\n"  
                        + content);  
                logWriter.newLine();  
                logWriter.flush();  
            } catch (IOException e) {  
                logger.error("memcached 写入日志信息失败", e);  
            }  
        }  
  
        /** 
         * 关闭流 
         */  
        private static void closeLogStream() {  
            try {  
                fileWriter.close();  
                logWriter.close();  
            } catch (IOException e) {  
                logger.error("memcached 日志对象关闭失败", e);  
            }  
        }  
    }  
}
