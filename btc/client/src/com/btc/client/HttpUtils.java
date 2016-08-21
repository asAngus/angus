package com.btc.client;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import java.net.SocketTimeoutException;

/**
 * Created by weipeng on 16/8/10.
 */
public abstract class HttpUtils {
    private static String ethUrl = "https://trans.chbtc.com/dish/data-ethdefault?lastTime=0&length=5&depth=0.01&jsoncallback=jQuery19107656935583382163_1470835000665&_=1470835000666";

    public static String getEthInfo() {

        return getInfo(ethUrl);
    }

    public static String getInfo(String url) {
        HttpClient client = new HttpClient();
        Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);
        client.getParams().setContentCharset("UTF-8");
        // url = url + "?" + xml;
//        PostMethod method = new PostMethod(url);
        GetMethod method = new GetMethod(url);
//        method.getParams().setContentCharset(contentCharset);
//        method.setRequestBody(xml);

        try {
            client.executeMethod(method);

            byte[] byteRsp = method.getResponseBody();
            byte[] responseBody = method.getResponseBody();

            String rsp = new String(byteRsp, "UTF-8");
            String substring = rsp.substring(rsp.indexOf("(")+1, rsp.indexOf(")"));

            System.out.println(substring);
            JSONObject jsonObject = JSONObject.fromObject(substring);
            System.out.println(jsonObject.toString());

        } catch (ConnectTimeoutException e) {
            return "";
        } catch (SocketTimeoutException e) {
            return "";
        } catch (Exception e) {
        } finally {
            method.releaseConnection();
        }

        return "";
    }
}
