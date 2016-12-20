package com.llkj.creditchecking.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 描    述：MD5加密工具类
 * 修订历史：
 */
public class MD5Utils {

    private static List<Map.Entry<String, String>> mHashMapEntryList;
    private  static String singValue="";

    private MD5Utils() {}

    /**
     * 对网络请求实体参数加密
     */
    public String getSingValue(HashMap<String, String> mHashMap)
    {
        mHashMapEntryList=new ArrayList<Map.Entry<String,String>>(mHashMap.entrySet());
        SPUtils spUtils = new SPUtils("AppInfo");
        String keyString= spUtils.getString("singKey","asdh2fjka145sljfdl");//TODO key 在版本校验时获取，存储本地
        String sEncodedString = null;
        try
        {
            for (int i = 0; i < mHashMapEntryList.size(); i++) {
                System.out.println(mHashMapEntryList.get(i));
            }

            Collections.sort(mHashMapEntryList, new Comparator<Map.Entry<String,String>>() {

                @Override
                public int compare(Map.Entry<String, String> lhs, Map.Entry<String, String> rhs)
                {
                    return lhs.getKey().compareTo(rhs.getKey());
                }
            });

            for (int i = 0; i < mHashMapEntryList.size(); i++) {

                System.out.println(mHashMapEntryList.get(i).getKey()+"----"+mHashMapEntryList.get(i).getValue());
                singValue+=mHashMapEntryList.get(i).getValue();
            }

            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(key);

            byte[] bytes = mac.doFinal(singValue.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();

            for (int i=0; i<bytes.length; i++) {
                String hex = Integer.toHexString(0xFF &  bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            sEncodedString = hash.toString();
        }
        catch (UnsupportedEncodingException e) {
            LogUtils.e("Encoding UTF-8 is not supported", e.getMessage());
        }
        catch(InvalidKeyException e){

            LogUtils.e("Invalid key", e.getMessage());
        }
        catch (NoSuchAlgorithmException e) {

            LogUtils.e("Hash algorithm SHA-1 is not supported", e.getMessage());
        }
        return sEncodedString ;
    }


    /**
     * 获取字符串的 MD5
     */
    public static String encode(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            byte messageDigest[] = md5.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", b));
            }
            return hexString.toString().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取文件的 MD5
     */
    public static String encode(File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream inputStream = new FileInputStream(file);
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
            //必须把文件读取完毕才能拿到md5
            byte[] buffer = new byte[4096];
            while (digestInputStream.read(buffer) > -1) {
            }
            MessageDigest digest = digestInputStream.getMessageDigest();
            digestInputStream.close();
            byte[] md5 = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                sb.append(String.format("%02X", b));
            }
            return sb.toString().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
