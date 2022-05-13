package com.ruoyi.jkza.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;

import java.util.HashMap;

public class VideoLive {

    //根据通道编码获取实时视频
    public static String getLive(String channelId, String name, String password)throws Exception{
        String ip = "https://133.1.3.14:8320/zhcs/apiserve/";
        String url = "resource-catalog/tripartite/authority/video/play";
        String token = getTokenDemo(name, password);
        HttpRequest httpRequest = null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authorityType",1);
        jsonObject.put("channelId",channelId);
        jsonObject.put("scheme","HLS");
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
    //根据请求参数、url以及视频账号密码调取接口
    public static String getVideoData(JSONObject jsonObject, String url, String name, String password){
        String ip = "https://133.1.3.14:8320/zhcs/apiserve/";
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

    public static String getTokenDemo(String name, String password) {
        try {
            String param = "{userName: \"" + name + "\", clientType: \"web\"}";
            String ip = "https://133.1.3.14:8320/zhcs/apiserve";
            String url = "/smc-ac/accounts/authorize"; // 第一次鉴权
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


    public static void main(String[] args){
        //try {
        //    getLive("W7w7VKk2B1DMIQSBMQ2EQC");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

        String ip = "https://133.1.3.14:8320/zhcs/apiserve/";
        //获取(视频录像,实时视频)
        //String url = "resource-catalog/tripartite/authority/video/play";
        //获取告警订阅中心列表页查询信息
        //String url = "ability/tripartite/find/alarm/subscribe/page";
        //分页查询个人有权限视频通道资源
        String url = "resource-catalog/tripartite/authority/channel/page";
        String token = getTokenDemo("jsjtj001", "jsjtj12345");
        HttpRequest httpRequest = null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo",1);
        jsonObject.put("pageSize",100);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword","");
        jsonObject.put("param",jsonObject1);
        System.out.println(jsonObject);
        String jsonparam = jsonObject.toString();
        //String jsonparam = "{\n" + "\t\"pageNo\": 1,\n" + "\t\"pageSize\": 10,\n" + "\t\"param\": {\n" + "\t\t\"algorithmName\": \"\",\n" + "\t\t\"applyId\": \"\",\n" + "\t\t\"channelCode\": \"\",\n" + "\t\t\"endDate\": \"\",\n" + "\t\t\"startDate\": \"\",\n" + "\t\t\"subscribeStatus\": 0\n" + "\t}\n" + "}";
        try {
            httpRequest = HttpUtil.createPost(ip + url);
            HashMap<String, String> headerMap = new HashMap<>();
            headerMap.put("X-Subject-Token", token);
            httpRequest.addHeaders(headerMap);
            httpRequest.body(jsonparam);
            httpRequest.setReadTimeout(15000);
            HttpResponse execute = httpRequest.execute();
            String body = execute.body(); // log.info("返回:" + body);
            System.out.println("返回:" + body);
        } catch (Exception e) {
            System.out.println("调用消息通知地址异常，pushMessageUrl："+e);
            //log.error("调用消息通知地址异常，pushMessageUrl：", e);
        }
    }

    private void test5300() {
        String ip = "https://10.35.39.211:8320/zhcs/apiserve/";
        String url = "ability/tripartite/find/alarm/subscribe/page";
        String token = getTokenDemo("lq004", "aadmin123");
        HttpRequest httpRequest = null;
        String jsonparam = "{\n" + "\t\"pageNo\": 1,\n" + "\t\"pageSize\": 10,\n" + "\t\"param\": {\n" + "\t\t\"algorithmName\": \"\",\n" + "\t\t\"applyId\": \"\",\n" + "\t\t\"channelCode\": \"\",\n" + "\t\t\"endDate\": \"\",\n" + "\t\t\"startDate\": \"\",\n" + "\t\t\"subscribeStatus\": 0\n" + "\t}\n" + "}";
        try {
            httpRequest = HttpUtil.createPost(ip + url);
            HashMap<String, String> headerMap = new HashMap<>();
            headerMap.put("X-Subject-Token", token);
            httpRequest.addHeaders(headerMap);
            httpRequest.body(jsonparam);
            httpRequest.setReadTimeout(15000);
            HttpResponse execute = httpRequest.execute();
            String body = execute.body(); // log.info("返回:" + body);
        } catch (Exception e) {
        //log.error("调用消息通知地址异常，pushMessageUrl：", e);
        }
    }
}
