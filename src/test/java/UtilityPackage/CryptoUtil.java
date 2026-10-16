package UtilityPackage;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil
{

	static String key="1234567890123456";
	
	public static String encryption(String Data) throws  InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher c= Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(),"AES"));
		String encryptValue= Base64.getEncoder().encodeToString(c.doFinal(Data.getBytes()));
		return encryptValue;
			
	}
	
	public static String decryption(String Data) throws  InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher c= Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(),"AES"));
		String decryptValue= new String(c.doFinal(Base64.getDecoder().decode(Data)));
		return decryptValue;
		
	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		System.out.println(encryption("AQDE5yGdjECc9z-KVwNVJDHSLQeaLJgthMhGb3krSp17Q0diD4jD5P9JELVqh-gzF-_g_qfPoFNiqf2p_GGeM6AFRlLneRN4rC_hTCIZDGYYAlhM6WfcTmf0VOriqob4QXA"));
		System.out.println(encryption("a8969aff0935424caa2852a20e82e4dc"));
		System.out.println(encryption("8e1605d6053a416db3554e807903a713"));
		
		
	}
	
}
