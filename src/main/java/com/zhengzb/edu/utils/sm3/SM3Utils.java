package com.zhengzb.edu.utils.sm3;

import java.nio.charset.StandardCharsets;



public class SM3Utils {

	/**
	 * 使用SM3国密算法进行加密
	 * 
	 * @param content
	 * @return
	 */
	public static String encrypt(String content) {
		byte[] md2 = new byte[32];
		byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);

		SM3Digest sm3 = new SM3Digest();
		sm3.update(contentBytes, 0, contentBytes.length);
		sm3.doFinal(md2, 0);
		return HexUtil.encode(md2);
	}
}
