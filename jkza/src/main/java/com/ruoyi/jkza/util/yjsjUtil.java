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
            String url = "/smc-ac/accounts/authorize"; // 第一次鉴权
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
                secondAuthRequest.setSignature(SecondAuthRequest.encryptSignature(name, secondAuthRequest.getRealm(), secondAuthRequest.getPassword(), secondAuthRequest.getRandomKey())); // 第二次鉴权
                String secondResponseBody = HttpRequest.post(ip + url).body(JSON.toJSONString(secondAuthRequest)).execute().body();
                JSONObject secondJsonObject = JSONObject.parseObject(secondResponseBody);
                return secondJsonObject.getString("token");
            }
        } catch (Exception e) {
            System.out.println("获取级联平台token发生异常:" + e.getStackTrace());// log.error("获取级联平台token发生异常: {}", e.getStackTrace());
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
            String body = execute.body(); // log.info("返回:" + body);
            return body;
        } catch (Exception e) {
            System.out.println("调用消息通知地址异常，pushMessageUrl：" + e);
            //log.error("调用消息通知地址异常，pushMessageUrl：", e);
        }
        return null;
    }

    /*** 平台token保活 * * @return */
    public static boolean tokenKeepaliveDemo(String token) {
        try {
            String param = "{token: \"" + token + "\"}";
            System.out.println("保活token:"+token);
            System.out.println("保活param:"+param);
            String responseBody = HttpRequest.post("http://133.1.3.101/zhcs/apiserve/smc-ac/token/keepalive")
                    .header("X-Subject-Token",token)
                    .body(JSON.toJSONString(param)).execute().body();
//                    .body(data.toJSONString()).execute().body();
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            System.out.println(jsonObject);
            if (jsonObject != null && "200".equals(jsonObject.getString("code"))) {
                System.out.println("平台token保活成功");
                return true;
//            } else { log.error("平台token保活失败"); return false;
            } else {
                System.out.println(("平台token保活失败"));
                return false;
            }
        } catch (Exception e) {
            System.out.println("平台token保活发生异常");
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
