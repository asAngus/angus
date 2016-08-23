package com.btc.weipeng.btc.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUtils {

    public static String ethUrl = "https://trans.chbtc.com/dish/data-ethdefault?lastTime=0&length=5&depth=0.01&jsoncallback=jQuery19107656935583382163_1470835000665&_=1470835000666";

    public static String etcUrl = "https://trans.chbtc.com/dish/data-etcdefault?lastTime=0&length=5&depth=0.01&jsoncallback=jQuery19106626792252982123_1470883154981&_=1470883154982";
    public static String btcUrl = "https://trans.chbtc.com/dish/data-btcdefault?lastTime=0&length=5&depth=0.01&jsoncallback=jQuery19106626792252982123_1470883154981&_=1470883154982";

    public static String readContentFromGet(String url) {
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
//        String getURL = GET_URL + "?username="
//                + URLEncoder.encode("fat man", "utf-8");
        StringBuffer stringBuffer = new StringBuffer();

        URL getUrl = null;
        try {
            getUrl = new URL(url);
            // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
            // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) getUrl
                    .openConnection();
            // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
            // 服务器
            connection.connect();
            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            String ln;
            while ((ln = reader.readLine()) != null) {
                stringBuffer.append(ln);
                stringBuffer.append("\r\n");
            }

            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    public static void doPost(String url) {
        // Post请求的url，与get不同的是不需要带参数

        // 打开连接
        HttpURLConnection connection = null;
        try {
            URL postUrl = new URL(url);
            connection = (HttpURLConnection) postUrl
                    .openConnection();
            // Output to the connection. Default is
            // false, set to true because post
            // method must write something to the
            // connection
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            connection.setDoOutput(true);
            // Read from the connection. Default is true.
            connection.setDoInput(true);
            // Set the post method. Default is GET
            connection.setRequestMethod("POST");
            // Post cannot use caches
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            // This method takes effects to
            // every instances of this class.
            // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
            // connection.setFollowRedirects(true);

            // This methods only
            // takes effacts to this
            // instance.
            // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
            connection.setInstanceFollowRedirects(true);
            // Set the content type to urlencoded,
            // because we will write
            // some URL-encoded content to the
            // connection. Settings above must be set before connect!
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
            // 进行编码
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());
            // The URL-encoded contend
            // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
            String content = "firstname=" + URLEncoder.encode("一个大肥人", "utf-8");
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
            out.writeBytes(content);

            out.flush();
            out.close(); // flush and close
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            System.out.println("=============================");
            System.out.println("Contents of post request");
            System.out.println("=============================");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("=============================");
            System.out.println("Contents of post request ends");
            System.out.println("=============================");
            reader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
