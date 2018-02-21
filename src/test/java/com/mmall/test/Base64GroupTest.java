package com.mmall.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by geely
 */
public class Base64GroupTest {
    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //todo execute
        String base64TestStr = "6L+Z6YO96KKr5L2g5Y+R546w5ZOH5ZKU5ZKUfn5oYXBweW1tYWxs55qEUVHnvqTlj7c6NTAwNTUwNzDvvIzmrKLov47liqDlhaV+fg==";
        System.out.println(getFromBase64(base64TestStr));
    }
}
