package com.save.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {
	// 定义加密算法DESede(即3DES) 
	private static final String Algorithm = "DESede";
	//秘钥【这样写在这里是没有任何意义的，应该在某个配置文件里或者其他系统传送过来】
	private static final String PASSWORD_CRYPT_KEY = "PASSWORD_KEY";

	/*
     * 根据 密钥字符串生成密钥字节数组
     * 
     * @param keyStr 密钥字符串
     * 
     * @return
     * 
     * @throws UnsupportedEncodingException
     */ public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException { 
    	 byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
    	 byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组
    	 // 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
    	 if (key.length > temp.length) {
    		// 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中	 
    		 System.arraycopy(temp, 0, key, 0, temp.length);
    	 }else{
    		 // 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
    		 System.arraycopy(temp, 0, key, 0, key.length);
    	 }
    	 return key;
    	 }
     /**
      * 加密方法
      * 
      * @param originalText 
      *            源数据的字节数组
      * @return
      */
     public static byte[] encryptMode(byte[] originalText) {
    	 try {
    	 SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm); // 生成密钥
    	 Cipher cipher = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
    	 
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
			return cipher.doFinal(originalText);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 初始化为加密模式
        catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return null;
     }
     /**
      * 解密函数
      * 
      * @param cipherText
      *            密文的字节数组
      * @return
      */
     public static byte[] decryptMode(byte[] cipherText) {
    	 try {
    	 SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
    	 Cipher cipher = Cipher.getInstance(Algorithm);
    	 cipher.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
    	 return cipher.doFinal(cipherText);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return null;
    
     }
     public static String byte2Hex(byte[] b){  
    	 String hs="";                                           
         String stmp=""; 
         for(int n=0; n<b.length; n++){  
        	 stmp = (java.lang.Integer.toHexString(b[n]& 0XFF)); 
        	 if(stmp.length()==1){  
        		 hs = hs + "0" + stmp;  
        	 }else{
        		 hs = hs + stmp;  
        	 }
        	 if(n<b.length-1);  
         }
         return hs.toUpperCase();   
     }
      public static void main(String[] args) {
    	  String msg = "1213456789";
    	  System.out.println("【加密前】：" + msg);
    	  //加密
    	  byte[] secretArr = DESUtil.encryptMode(msg.getBytes());
//    	  System.out.println(new String (secretArr));
    	  String byte2Hex = byte2Hex(secretArr);
    	  System.out.println(byte2Hex);
    	  //解密
    	  byte[] myMsgArr = DESUtil.decryptMode(secretArr);
    	  System.out.println("【解密后】：" + new String(myMsgArr));
	}
}
