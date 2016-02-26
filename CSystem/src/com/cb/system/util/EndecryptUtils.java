package com.cb.system.util;

import org.apache.shiro.codec.Base64; 
import org.apache.shiro.codec.Hex; 
import org.apache.shiro.crypto.hash.Md5Hash; 

/**
 * @author chen
 *
 */
public final class EndecryptUtils {

    /** 
     * base64进制加密 
     * @param text 
     * @return 
     */
    public static String encrytBase64(String text) {
    	if(text==null){
    		return null;
    	}
        byte[] bytes = text.getBytes(); 
        return Base64.encodeToString(bytes);
    }
    
    /** 
     * base64进制解密 
     * @param text 
     * @return 
     */ 
    public static String decryptBase64(String text) { 
    	if(text==null){
    		return null;
    	}
        return Base64.decodeToString(text); 
    }
    
    /** 
     * 16进制加密 
     * @param text 
     * @return 
     */ 
    public static String encrytHex(String text) { 
    	if(text==null){
    		return null;
    	}
        byte[] bytes = text.getBytes(); 
        return Hex.encodeToString(bytes); 
    }
    
    /** 
     * 16进制解密 
     * @param text 
     * @return 
     */ 
    public static String decryptHex(String text) { 
    	if(text==null){
    		return null;
    	}
        return new String(Hex.decode(text)); 
    } 

    /** 
     * 进行md5加密
     * @param text 
     */ 
    public static String md5(String text){
    	if(text==null){
    		return null;
    	}
        String str= new Md5Hash(text).toBase64(); 
        return str; 
    } 
	
}
