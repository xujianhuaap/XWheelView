package com.xujianhuaap.xwheelview.util.security;




import com.xujianhuaap.xwheelview.util.ExceptionUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xujianhua on 2016/6/3.
 */
public class MD5Util {
    static MessageDigest messageDigest;
    public static byte[] obtainMd5Digest(byte[] content){
        ExceptionUtil.nullExeption(content);
        try {
            if(messageDigest==null){
                messageDigest=MessageDigest.getInstance("MD5");
            }else {
                messageDigest.reset();
            }
            messageDigest.update(content);
            return messageDigest.digest();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String toHexString(byte[] digest){
        StringBuilder stringBuilder=new StringBuilder();
        for(byte b: digest){
            stringBuilder.append( Integer.toHexString(b));
        }
        return stringBuilder.toString();
    }

}
