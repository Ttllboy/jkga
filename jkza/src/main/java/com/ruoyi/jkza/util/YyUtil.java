package com.ruoyi.jkza.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;

import java.util.HashMap;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class YyUtil {
    public static String getTokenDemo(String name, String password) {
        try {
            String param = "{userName: \"" + name + "\", clientType: \"web\"}";
            String ip = "https://133.1.3.15:9400";
            String url = "/VIID/System/Register"; // 第一次鉴权
            String firstResponseBody = HttpRequest.post(ip + url).body(param).execute().body();
            JSONObject jsonObject = JSONObject.parseObject(firstResponseBody);
            String randomKey = jsonObject.getString("randomKey");
            String realm = jsonObject.getString("realm");
            String encryptType = jsonObject.getString("encryptType");
            if (StringUtils.isNotBlank(randomKey) && StringUtils.isNotBlank(realm) && StringUtils.isNotBlank(encryptType)) {
                SecondAuthRequest secondAuthRequest = new SecondAuthRequest();
                secondAuthRequest.setClientType("web");
                secondAuthRequest.setEncryptType(encryptType);
                secondAuthRequest.setRandomKey(randomKey);
                secondAuthRequest.setRealm(realm);
                secondAuthRequest.setUserName(name);
                secondAuthRequest.setPassword(SecondAuthRequest.encryptPassword(name, secondAuthRequest.getRealm(), password, true));
                secondAuthRequest.setSignature(SecondAuthRequest.encryptSignature(name, secondAuthRequest.getRealm(), secondAuthRequest.getPassword(), secondAuthRequest.getRandomKey())); // 第二次鉴权
                String secondResponseBody = HttpRequest.post(ip + url).body(JSON.toJSONString(secondAuthRequest)).execute().body();
                JSONObject secondJsonObject = JSONObject.parseObject(secondResponseBody);
                return secondJsonObject.getString("token");
            }
        } catch (Exception e) {
            System.out.println("获取级联平台token发生异常:"+ e.getStackTrace());// log.error("获取级联平台token发生异常: {}", e.getStackTrace());
        }
        return null;
    }

    public static String getCamera(JSONObject jsonObject, String url, String name, String password){
        String ip = "https://133.1.3.15:9400";
        String token = getTokenDemo(name, password);
        HttpRequest httpRequest = null;
        String jsonparam = jsonObject.toString();
        try {
            httpRequest = HttpUtil.createPost(ip + url);
            HashMap<String, String> headerMap = new HashMap<>();
            headerMap.put("X-Subject-Token", token);
            httpRequest.addHeaders(headerMap);
            httpRequest.body(jsonparam);
            httpRequest.setReadTimeout(15000);
            HttpResponse execute = httpRequest.execute();
            String body = execute.body(); // log.info("返回:" + body);
            return body;
        } catch (Exception e) {
            System.out.println("调用消息通知地址异常，pushMessageUrl："+e);
            //log.error("调用消息通知地址异常，pushMessageUrl：", e);
        }
        return null;
    }

    static String generateDigest(boolean passwordAlreadyEncoded, String username,

                                 String realm, String password, String httpMethod, String uri, String qop,
                                 String nonce, String nc, String cnonce) throws IllegalArgumentException {
//        String a1Md5;
//        String a2 = httpMethod + ":" + uri;
//        String a2Md5 = md5Hex(a2);
//        if (passwordAlreadyEncoded) {
//            a1Md5 = password;
//        }else {
//            a1Md5 = DigestAuthUtils.encodePasswordInA1Format(username, realm, password);
//        }
//        String digest;
//        if (qop == null) {
//            // as per RFC 2069 compliant clients (also reaffirmed by RFC 2617)
//            digest = a1Md5 + ":" + nonce + ":" + a2Md5;
//        }else if ("auth".equals(qop)) {
//            // As per RFC 2617 compliant clients
//            digest = a1Md5 + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop + ":"
//                    + a2Md5;
//        }else {
//            throw new IllegalArgumentException("This method does not support a qop: '"
//                    + qop + "'");
//        }
//        return md5Hex(digest);
        return null;
    }

}
