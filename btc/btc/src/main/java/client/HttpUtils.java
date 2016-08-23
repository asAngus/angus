/**
 *
 */
package client;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * @author LUCHUNLIANG
 */
public class HttpUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
//    private static final MediaType MEDIATYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    public static final String REQUEST_CONTENTTYPE = "Content-Type";
    public static final int DEFAULT_CONNECT_TIMEOUT = 20000;
    public static final int DEFAULT_SOCKET_TIMEOUT = 20000;
    public static final String GAME_CHARSET = "UTF-8";

    private static final int DEFAULT_MAX_CONNECTIONS = 10000;
    private static final int DEFAULT_MAX_PER_ROUTE = 500;
    private static PoolingHttpClientConnectionManager httpClientConnectionMgr;
    private static CloseableHttpClient httpClient;
    private static boolean shutdown;
    public static String ethUrl = "https://trans.chbtc.com/dish/data-ethdefault?lastTime=0&length=5&depth=0.01&jsoncallback=jQuery19107656935583382163_1470835000665&_=1470835000666";

    public static String etcUrl = "https://trans.chbtc.com/dish/data-etcdefault?lastTime=0&length=5&depth=0.01&jsoncallback=jQuery19106626792252982123_1470883154981&_=1470883154982";
    public static String btcUrl = "https://trans.chbtc.com/dish/data-btcdefault?lastTime=0&length=5&depth=0.01&jsoncallback=jQuery19106626792252982123_1470883154981&_=1470883154982";

    static {
//		String substring = rsp.substring(rsp.indexOf("(")+1, rsp.indexOf(")"));
//
//		System.out.println(substring);
//		JSONObject jsonObject = JSON.parseObject(substring);
//
//
//		System.out.println(jsonObject.toString());
//        startConnectionEvictionTask();
        try {
            initHttpClientNew();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initHttpClientNew() throws Exception {
        SSLContextBuilder sslContextBuilder = SSLContexts.custom().useProtocol("TLS");
//		File trustStoreFile = getTrustStoreFile();
//		if (trustStoreFile != null) {
//			sslContextBuilder.loadTrustMaterial(getTrustStoreFile(), "xgsdk.com".toCharArray());
//		}
        SSLContext sslContext = sslContextBuilder.build();
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        sslContext.init(null, new TrustManager[]{tm}, new java.security.SecureRandom());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        ConnectionSocketFactory plainsf = new PlainConnectionSocketFactory();
        Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();
        httpClientConnectionMgr = new PoolingHttpClientConnectionManager(r);
        httpClientConnectionMgr.setMaxTotal(DEFAULT_MAX_CONNECTIONS);
        httpClientConnectionMgr.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);

        RequestConfig config = RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).build();
        httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionMgr).setDefaultRequestConfig(config).build();
    }
//
//	private static File getTrustStoreFile() throws IOException {
//		ClassPathResource resource = new ClassPathResource("xgsdk.trust");
//		try {
//			return resource.getFile();
//		} catch (Exception ex) {
//			File temp = File.createTempFile("xgsdk.trust", ".tmp");
//			FileCopyUtils.copy(resource.getInputStream(), new FileOutputStream(temp));
//			return temp;
//		}
//	}

    private static void startConnectionEvictionTask() {
        Thread cleanListener = new Thread("Connection Eviction Listener") {
            @Override
            public void run() {
                while (!shutdown) {
                    try {
                        sleep(5000);
                        httpClientConnectionMgr.closeExpiredConnections();
                        httpClientConnectionMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        cleanListener.start();
    }

    @Override
    protected void finalize() throws Throwable {
        shutdown = true;

        if (httpClient != null) {
            httpClient.close();
        }
        if (httpClient != null) {
            httpClientConnectionMgr.close();
        }
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPostForm(String url, String body) {
        return doPostForm(url, body, null);
    }

    public static String doPostJson(String url, String body) {
        return doPostJson(url, body, null);
    }

    public static String doGet(String url, Map<String, String> headers) {
        return doGet(url, headers, null);
    }

    public static String doGet(String url, Map<String, String> headers, Map<String, String> cookies) {
        log.info("start to send http request to [{}]. The header parameters are [{}].", url, headers);
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader in = null;
        stringBuffer.append("");
        CloseableHttpResponse response = null;
        try {
            HttpGet httpget = new HttpGet(url);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).build();
            httpget.setConfig(config);
            if (headers != null) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    httpget.setHeader(entry.getKey(), entry.getValue());
                }
            }

            if (cookies != null && cookies.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (Entry<String, String> entry : cookies.entrySet()) {
                    buffer.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
                }
                buffer.deleteCharAt(buffer.length() - 1);
                String cookie = buffer.toString();
                httpget.setHeader("Cookie", cookie);
                log.info("cookie is {}", cookie);
            }

            response = httpClient.execute(httpget);
            if (response.getStatusLine().getStatusCode() != 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    log.error("Failed : HTTP error code : {}, response is : {}", response.getStatusLine().getStatusCode(), EntityUtils.toString(entity));
                } else {
                    log.error("Failed : HTTP error code : {}, response is : {}", response.getStatusLine().getStatusCode(), response);
                }
                return null;
            }
            HttpEntity entity = response.getEntity();
            in = new BufferedReader(new InputStreamReader(entity.getContent()));
            String ln;
            while ((ln = in.readLine()) != null) {
                stringBuffer.append(ln);
                stringBuffer.append("\r\n");
            }
            EntityUtils.consume(entity);
//			response.close();
        } catch (Throwable t) {
            log.error("error in sending http request to [{}]. The header parameters are [{}]. The error is [{}].", url, headers, t.getMessage());
            log.error(t.getMessage(), t);
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                if (response != null) {
                    response.close();
                    response = null;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String responseString = stringBuffer.toString();
        log.info("end to send http request to [{}]. The header parameters are [{}]. The response is [{}].", url, headers, responseString);
        return responseString;
    }

    public static String doPostForm(String url, String body, Map<String, String> headers) {
        log.info("start to send http request to [{}]. The body is [{}]. The header parameters are [{}].", url, body, headers);
        StringBuffer stringBuffer = new StringBuffer();
        HttpEntity entity = null;
        BufferedReader in = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httppost = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).build();
            httppost.setConfig(config);
            if (headers == null) {
                headers = new HashMap<>();
            }
            String contentType = headers.get(REQUEST_CONTENTTYPE);
            if (contentType == null) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    if (StringUtils.equalsIgnoreCase(entry.getKey(), REQUEST_CONTENTTYPE)) {
                        contentType = entry.getValue();
                        headers.remove(entry.getKey());
                        break;
                    }
                }
            } else {
                headers.remove(REQUEST_CONTENTTYPE);
            }
            if (contentType == null) {
                httppost.setHeader(REQUEST_CONTENTTYPE, "application/x-www-form-urlencoded");
            } else {
                httppost.setHeader(REQUEST_CONTENTTYPE, contentType);
            }
            for (Entry<String, String> entry : headers.entrySet()) {
                if (!httppost.containsHeader(entry.getKey()) && !StringUtils.equalsIgnoreCase("content-length", entry.getKey())) {
                    httppost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httppost.setEntity(new ByteArrayEntity(body.getBytes("UTF-8")));
            response = httpClient.execute(httppost);
            if (response.getStatusLine().getStatusCode() != 200) {
                log.error("Failed : HTTP error code : {}, {}", response.getStatusLine().getStatusCode(), response);
                return null;
            }
            entity = response.getEntity();
            in = new BufferedReader(new InputStreamReader(entity.getContent()));
            String ln;
            while ((ln = in.readLine()) != null) {
                stringBuffer.append(ln);
                stringBuffer.append("\r\n");
            }
            EntityUtils.consume(entity);
            response.close();
        } catch (Throwable t) {
            log.error("error in sending http request to [{}]. The body is [{}]. The header parameters are [{}]. The error is [{}].", url, body, headers, t.getMessage());
            log.error(t.getMessage(), t);
        }
        String responseString = stringBuffer.toString();
        log.info("end to send http request to [{}]. The body is [{}]. The header parameters are [{}]. The response is [{}].", url, body, headers, responseString);
        return responseString;
    }

    public static String doPostJson(String url, String body, Map<String, String> headers) {
        log.info("start to send http request to [{}]. The body is [{}]. The header parameters are [{}].", url, body, headers);
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader in = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).build();
            httpPost.setConfig(config);
            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json; charset=" + GAME_CHARSET);
            if (headers != null) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    if (!httpPost.containsHeader(entry.getKey()) && !StringUtils.equalsIgnoreCase("content-length", entry.getKey())) {
                        httpPost.setHeader(entry.getKey(), entry.getValue());
                    }
                }
            }
            httpPost.setEntity(new StringEntity(body == null ? "" : body, Charset.forName(GAME_CHARSET)));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                if (entity != null) {
                    log.error("Failed is : HTTP error code : {}, response is : {}", response.getStatusLine().getStatusCode(), EntityUtils.toString(entity));
                } else {
                    log.error("Failed is : HTTP error code : {}, response is : {}", response.getStatusLine().getStatusCode(), response);
                }
                return null;
            }
            in = new BufferedReader(new InputStreamReader(entity.getContent()));
            String ln;
            while ((ln = in.readLine()) != null) {
                stringBuffer.append(ln);
                stringBuffer.append("\r\n");
            }
            EntityUtils.consume(entity);
            response.close();
        } catch (Throwable t) {
            log.error("error in sending http request to [{}]. The body is [{}]. The header parameters are [{}]. The error is [{}].", url, body, headers, t.getMessage());
            log.error(t.getMessage(), t);
        }
        String responseString = stringBuffer.toString();
        log.info("end to send http request to [{}]. The body is [{}]. The header parameters are [{}]. The response is [{}].", url, body, headers, responseString);
        return responseString;
    }

    public static String doPostXml(String url, String body, Map<String, String> headers) {
        log.info("start to send http request to [{}]. The body is [{}]. The header parameters are [{}].", url, body, headers);
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader in = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).build();
            httpPost.setConfig(config);
            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/xml; charset=" + GAME_CHARSET);
            if (headers != null) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    if (!httpPost.containsHeader(entry.getKey()) && !StringUtils.equalsIgnoreCase("content-length", entry.getKey())) {
                        httpPost.setHeader(entry.getKey(), entry.getValue());
                    }
                }
            }
            httpPost.setEntity(new StringEntity(body == null ? "" : body, Charset.forName(GAME_CHARSET)));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() != 200) {
                if (entity != null) {
                    log.error("Failed: HTTP error code : {}, response is : {}", response.getStatusLine().getStatusCode(), EntityUtils.toString(entity));
                } else {
                    log.error("Failed: HTTP error code : {}, response is : {}", response.getStatusLine().getStatusCode(), response);
                }
                return null;
            }
            in = new BufferedReader(new InputStreamReader(entity.getContent()));
            String ln;
            while ((ln = in.readLine()) != null) {
                stringBuffer.append(ln);
                stringBuffer.append("\r\n");
            }
            EntityUtils.consume(entity);
            response.close();
        } catch (Throwable t) {
            log.error("error in sending http request to [{}]. The body is [{}]. The header parameters are [{}]. The error is [{}].", url, body, headers, t.getMessage());
            log.error(t.getMessage(), t);
        }
        String responseString = stringBuffer.toString();
        log.info("end to send http request to [{}]. The body is [{}]. The header parameters are [{}]. The response is [{}].", url, body, headers, responseString);
        return responseString;
    }
}
