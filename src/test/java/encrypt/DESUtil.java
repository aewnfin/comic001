package encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class DESUtil {
	
	private static SecretKey key;
	//密钥
	private static String KEY_STR="mykey";
	private static String CHARSETNAME="UTF-8";
	private static String ALGORITHM="DES";
	
	static {
		try {
			//生产DES算法对象
			KeyGenerator generator=KeyGenerator.getInstance(ALGORITHM);
			//运用SHA1安全策略
			SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
			//设置密钥种子
			secureRandom.setSeed(KEY_STR.getBytes());
			//初始化：基于SHA1安全策略的DES算法对象
			generator.init(secureRandom);
			//生成密钥
			key=generator.generateKey();
			generator=null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getEncryptString(String str) {
		//sun专属API:BASE64Encoder不建议使用
		//基于BASE64编码，接收byte[]，并转换成String
		BASE64Encoder base64Encoder=new BASE64Encoder();
		try {
			//按UTF-8编码
			byte[] bytes = str.getBytes(CHARSETNAME);
			//获取加密对象
			Cipher cipher=Cipher.getInstance(ALGORITHM);
			//初始化加密对象
			cipher.init(Cipher.ENCRYPT_MODE, key);
			//加密
			byte[] doFinal=cipher.doFinal(bytes);
			//转换加密字节码byte[]返回String
			return base64Encoder.encode(doFinal);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getDecryptString(String str) {
		//sun专属API:BASE64Decoder不建议使用
		//
		BASE64Decoder base64Decoder=new BASE64Decoder();
		try {
			byte[] bytes=base64Decoder.decodeBuffer(str);
			//获取指定加密方法的解密对象
			Cipher cipher =Cipher.getInstance(ALGORITHM);
			//初始化解密对象
			cipher.init(Cipher.DECRYPT_MODE, key);
			//解密
			byte[] doFinal=cipher.doFinal(bytes);
			//返回解密结果
			return new String(doFinal,CHARSETNAME);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getEncryptString("root"));
		System.out.println(getEncryptString("123456"));
		System.out.println(getDecryptString("Ov4j7fKiCzY="));
		System.out.println(getDecryptString("xfcqz2HNCeY="));
	}
}
