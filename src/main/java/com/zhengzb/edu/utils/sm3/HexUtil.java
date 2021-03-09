package com.zhengzb.edu.utils.sm3;


public class HexUtil {
    
    /**
     * 字节流转成十六进制表示
     */
    public static String encode(byte[] bytes) {
        String strHex;
        StringBuilder sb = new StringBuilder();
        for (byte aSrc : bytes) {
            strHex = Integer.toHexString(aSrc & 0xFF);
            // 每个字节由两个字符表示，位数不够，高位补0
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex);
        }
        return sb.toString().trim();
    }
    
    /**
     * 字符串转成字节流
     */
    public static byte[] decode(String string) {
        int m, n;
        // 每两个字符描述一个字节
        int byteLen = string.length() / 2;
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + string.substring(i * 2, m) + string.substring(m, n));
            ret[i] = (byte) intVal;
        }
        return ret;
    }
    
}
