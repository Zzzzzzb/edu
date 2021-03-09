package com.zhengzb.edu.utils.sm4;


import com.zhengzb.edu.utils.sm3.HexUtil;

import java.nio.charset.StandardCharsets;



public class SM4Utils {
    
    public SM4Utils() {
    }
    
    public static String encryptData_ECB(String plainText, String secretKey, boolean hexString) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;
            
            byte[] keyBytes;
            if (hexString) {
                keyBytes = HexUtil.decode(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }
            
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes(StandardCharsets.UTF_8));
            return HexUtil.encode(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String decryptData_ECB(String cipherText, String secretKey, boolean hexString) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;
            
            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }
            
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, HexUtil.decode(cipherText));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String encryptData_CBC(String plainText, String secretKey, boolean hexString, String iv) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;
            
            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }
            
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes(StandardCharsets.UTF_8));
            return HexUtil.encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String decryptData_CBC(String cipherText, String secretKey, boolean hexString, String iv) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;
            
            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }
            
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, HexUtil.decode(cipherText));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
