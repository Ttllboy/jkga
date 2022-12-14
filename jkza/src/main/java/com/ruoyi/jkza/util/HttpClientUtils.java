package com.ruoyi.jkza.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.*;

public class HttpClientUtils {


    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String doGet(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        String urlNameString = url;
        try {
            if (param != null || param != "") {
                urlNameString = urlNameString + "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException | ConnectTimeoutException e) {
            e.printStackTrace();
            //SocketTimeoutException：是Java包下抛出的异常，这定义了Socket读数据的超时时间，即从server获取响应数据须要等待的时间；当读取或者接收Socket超时会抛出SocketTimeoutException
            System.out.println("sendGet SocketTimeoutException, url=" + url + ",param=" + param);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("sendGet IOException, url=" + url + ",param=" + param);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("sendGet Exception, url=" + url + ",param=" + param);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("sendGet Exception, url=" + url + ",param=" + param);
            }
        }
        return result.toString();
    }



    /**
     * post请求
     * @param path url地址
     * @param Info jsonobject 参数
     * @return
     * @throws IOException
     */
    public static JSONObject doPost(String path,JSONObject Info) throws IOException{
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(path);

        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {
            StringEntity s = new StringEntity(Info.toString(), "utf-8");
            s.setContentEncoding("application/json");
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("请求服务器成功，做相应处理");
            } else {
                System.out.println("请求服务端失败");
            }

        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return JSONObject.parseObject(result);
    }

    public static JSONObject cameraApiPost(String path,JSONObject Info) throws IOException{
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(path);

        post.setHeader("Content-Type", "application/json");
        post.setHeader("Content-Type", "application/json");
        String result = "";

        try {
            StringEntity s = new StringEntity(Info.toString(), "utf-8");
            s.setContentEncoding("application/json");
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("请求服务器成功，做相应处理");
            } else {
                Header[] headerArray = httpResponse.getHeaders("www-authenticate");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("header",headerArray[0].getValue());
                return jsonObject;
            }
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return JSONObject.parseObject(result);
    }

    public static JSONObject cameraApiPostSe(String path,JSONObject Info,String Authorization) throws IOException{
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(path);

        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", Authorization);
        String result = "";

        try {
            StringEntity s = new StringEntity(Info.toString(), "utf-8");
            s.setContentEncoding("application/json");
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("请求服务器成功，做相应处理");
            } else {
                Header[] headerArray = httpResponse.getHeaders("www-authenticate");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("header",headerArray[0].getValue());
                return jsonObject;
            }
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return JSONObject.parseObject(result);
    }
    public static String  getSummary(String url, String param,String authorization){

        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        String urlNameString = url;
        try {
            if (param != null || param != "") {
                urlNameString = urlNameString + "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Authorization",authorization);

            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException | ConnectTimeoutException e) {
            e.printStackTrace();
            //SocketTimeoutException：是Java包下抛出的异常，这定义了Socket读数据的超时时间，即从server获取响应数据须要等待的时间；当读取或者接收Socket超时会抛出SocketTimeoutException
            System.out.println("sendGet SocketTimeoutException, url=" + url + ",param=" + param);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("sendGet IOException, url=" + url + ",param=" + param);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("sendGet Exception, url=" + url + ",param=" + param);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("sendGet Exception, url=" + url + ",param=" + param);
            }
        }
        return result.toString();
    }
}