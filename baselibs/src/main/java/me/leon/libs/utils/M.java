package me.leon.libs.utils;

import android.os.Build;

import java.security.MessageDigest;

/**
 * Author:  Parorisim
 * Time:    2017/4/7 上午11:52
 * Desc:    MD5加密
 */

public class M {

    /**
     * 生成 MD5
     *
     * @param str 原始字符串
     * @return 加密字符串
     */
    public static String generate(String str) {
        String md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] barr = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aBarr : barr) sb.append(byte2Hex(aBarr));
            md5 = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * 字节转十六进制字符串
     *
     * @param b 字节
     * @return 十六进制字符串
     */
    private static String byte2Hex(byte b) {
        String[] h = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        int i = b;
        if (i < 0) i += 256;
        return h[i / 16] + h[i % 16];
    }

    /**
     * 生成设备唯一标识
     *
     * @return 唯一标识
     */
    public static String generateUUID() {
        return generate("35" +
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10);
    }
}
