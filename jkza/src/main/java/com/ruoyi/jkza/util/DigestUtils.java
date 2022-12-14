package com.ruoyi.jkza.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class DigestUtils {
    /**
     * 根据当前时间戳生成一个随机字符串
     * @return
     */
    public static String generateToken() {
        String s = String.valueOf(System.currentTimeMillis() + new Random().nextInt());

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] digest = messageDigest.digest(s.getBytes());

            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public static String MD5(String inStr) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 该方法用于将 Authorization 请求头的内容封装成一个对象。
     *
     * Authorization 请求头的内容为：
     *     Digest username="aaa", realm="no auth", nonce="b2b74be03ff44e1884ba0645bb961b53",
     *     uri="/BootDemo/login", response="90aff948e6f2207d69ecedc5d39f6192", qop=auth,
     *     nc=00000002, cnonce="eb73c2c68543faaa"
     */
    public static DigestAuthInfo getAuthInfoObject(String authStr) {
        if (authStr == null || authStr.length() <= 7)
            return null;

        if (authStr.toLowerCase().indexOf("digest") >= 0) {
            // 截掉前缀 Digest
            authStr = authStr.substring(6);
        }

        // 将双引号去掉
        authStr = authStr.replaceAll("\"","");

        DigestAuthInfo digestAuthObject = new DigestAuthInfo();
        String[] authArray = new String[8];
        authArray = authStr.split(",");
        // System.out.println(java.util.Arrays.toString(authArray));

        for (int i = 0, len = authArray.length; i < len; i++) {
            String auth = authArray[i];
            String key = auth.substring(0, auth.indexOf("=")).trim();
            String value = auth.substring(auth.indexOf("=") + 1).trim();
            switch (key) {
                case "username":
                    digestAuthObject.setUsername(value);
                    break;
                case "realm":
                    digestAuthObject.setRealm(value);
                    break;
                case "nonce":
                    digestAuthObject.setNonce(value);
                    break;
                case "uri":
                    digestAuthObject.setUri(value);
                    break;
                case "response":
                    digestAuthObject.setResponse(value);
                    break;
                case "qop":
                    digestAuthObject.setQop(value);
                    break;
                case "nc":
                    digestAuthObject.setNc(value);
                    break;
                case "cnonce":
                    digestAuthObject.setCnonce(value);
                    break;
            }
        }
        return digestAuthObject;
    }

}
