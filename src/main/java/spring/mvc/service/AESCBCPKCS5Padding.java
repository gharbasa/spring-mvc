package spring.mvc.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//https://www.novixys.com/blog/java-aes-example/
public class AESCBCPKCS5Padding {
	
	public static void main(String[] str) {
		byte[] iv = new byte[128/8]; //16
		SecureRandom srandom = new SecureRandom();
		
		srandom.nextBytes(iv);
		//IvParameterSpec ivspec = new IvParameterSpec(iv);
		System.out.println("iv size=" + iv.length);
		String ivFile = "iv_file.txt";
		try (FileOutputStream out = new FileOutputStream(ivFile)) {
		    out.write(iv);
		    out.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		byte[] keyb = new byte[16];
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecretKey skey = kgen.generateKey();
			String keyFile = "key_file.txt";
			FileOutputStream out = new FileOutputStream(keyFile);
			keyb = skey.getEncoded();
			System.out.println("Key size=" + keyb.length);
			out.write(keyb);
			out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		byte[] combinedKey = new byte[33];
		combinedKey[0] = 16; //iv length
		System.arraycopy(iv, 0, combinedKey, 1, 16);//(src, srcPos, dest, destPos, length);
		System.arraycopy(keyb, 0, combinedKey, 17, 16);
		String encodedString = Base64.getEncoder().encodeToString(combinedKey);
		System.out.println("Final Key=" + encodedString);
		
		
		//hgere starts the fun after gettin the key encodedString
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());
		byte ivLength = decodedBytes[0]; 
		System.out.println("from the key, Iv length=" + ivLength);
		byte[] ivByteArray = new byte[ivLength];
		System.arraycopy(decodedBytes, 1, ivByteArray, 0, ivLength);//(src, srcPos, dest, destPos, length);
		IvParameterSpec ivspec = new IvParameterSpec(ivByteArray);
		byte[] keyBytes = new byte[decodedBytes.length - 1 - ivLength];
		System.arraycopy(decodedBytes, ivByteArray.length, keyBytes, 0, keyBytes.length);//(src, srcPos, dest, destPos, length);
		System.out.println("keyBytes length=" + keyBytes.length);
		SecretKeySpec skey = new SecretKeySpec(keyBytes, "AES");
		
		try {
			Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);
			
			
		}
		catch(Exception e) {
			
		}
		
		
		
		
		
		
		
	}
	
}
