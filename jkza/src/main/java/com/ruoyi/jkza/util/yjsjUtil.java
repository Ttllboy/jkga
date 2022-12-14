package com.ruoyi.jkza.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.HashMap;

public class yjsjUtil {
    HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };

    public static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }

    public static String getTokenDemo(String name, String password) {
        try {
            String param = "{userName: \"" + name + "\", clientType: \"web\"}";
            String ip = "https://133.1.3.101/zhcs/apiserve";
            String url = "/smc-ac/accounts/authorize"; // ???????????????
            String firstResponseBody = HttpRequest.post(ip + url).body(param).execute().body();
            System.out.println("firstResponseBody"+firstResponseBody);
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
                secondAuthRequest.setSignature(SecondAuthRequest.encryptSignature(name, secondAuthRequest.getRealm(), secondAuthRequest.getPassword(), secondAuthRequest.getRandomKey())); // ???????????????
                String secondResponseBody = HttpRequest.post(ip + url).body(JSON.toJSONString(secondAuthRequest)).execute().body();
                JSONObject secondJsonObject = JSONObject.parseObject(secondResponseBody);
                return secondJsonObject.getString("token");
            }
        } catch (Exception e) {
            System.out.println("??????????????????token????????????:" + e.getStackTrace());// log.error("??????????????????token????????????: {}", e.getStackTrace());
        }
        return null;
    }

    public static String getEvent(JSONObject jsonObject, String url, String name, String password) {
        String ip = "https://133.1.3.101/zhcs/apiserve/";
        String token = getTokenDemo(name, password);
        System.out.println(token);
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
            String body = execute.body(); // log.info("??????:" + body);
            return body;
        } catch (Exception e) {
            System.out.println("?????????????????????????????????pushMessageUrl???" + e);
            //log.error("?????????????????????????????????pushMessageUrl???", e);
        }
        return null;
    }

    /*** ??????token?????? * * @return */
    public static boolean tokenKeepaliveDemo(String token) {
        try {
            String param = "{token: \"" + token + "\"}";
            System.out.println("??????token:"+token);
            System.out.println("??????param:"+param);
            String responseBody = HttpRequest.post("http://133.1.3.101/zhcs/apiserve/smc-ac/token/keepalive")
                    .header("X-Subject-Token",token)
                    .body(JSON.toJSONString(param)).execute().body();
//                    .body(data.toJSONString()).execute().body();
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            System.out.println(jsonObject);
            if (jsonObject != null && "200".equals(jsonObject.getString("code"))) {
                System.out.println("??????token????????????");
                return true;
//            } else { log.error("??????token????????????"); return false;
            } else {
                System.out.println(("??????token????????????"));
                return false;
            }
        } catch (Exception e) {
            System.out.println("??????token??????????????????");
            return false;
        }
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.set("token",token);
//        String url = "http://133.1.3.101/smc-ac/token/keepalive";
//        System.out.println(restTemplate.postForObject(url,requestHeaders,String.class));
//        return true;
//    }
    }
}
