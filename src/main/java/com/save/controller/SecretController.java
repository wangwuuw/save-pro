package com.save.controller;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;


/**
 * RAS，DES加密
 * @author admin
 *
 */
public class SecretController {

	public static void main(String[] args) throws Exception {
		String encrypt = encrypt("1111111","wangwu");
		System.out.println(encrypt);
	}
	public void DES(){
		
	}
	
		   /**
		     *
		     * @param key 秘钥
		     * @param text 需要加密的数据
		     * @return
		     * @throws Exception
		     * 简单了解下 ： DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
		     * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，较为容易破解。
		     */
		    public static String encrypt(String key,String text) throws  Exception {
		        try {
		            byte[] src = text.getBytes("utf-8");
		            //DESedeKeySpec会帮你生成24位秘钥，key可以是任意长度
		            DESedeKeySpec spec = new DESedeKeySpec(key.getBytes("utf-8"));
		            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
		            SecretKey secretKey = factory.generateSecret(spec);
		            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		            byte[] res = cipher.doFinal(src);
		            //encodeBase64会对字符串3位一组自动补全，因而最后可能会出现 == 或者 =
		            return new String(Base64.encodeBase64(res), "utf-8");

		        } catch (Exception e) {
		        	e.printStackTrace();
		            System.out.println("error");
		        }
		        return null;
		    }
	
}
