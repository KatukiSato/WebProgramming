package controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Common {


	public static String Ango(String password) throws NoSuchAlgorithmException {
		//ハッシュを生成したい元の文字列

		String source = password;

		Charset charset = StandardCharsets.UTF_8;

		String algorithm = "MD5";

		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
		String result = DatatypeConverter.printHexBinary(bytes);
		//結果を返す
		return result;
	}

}
