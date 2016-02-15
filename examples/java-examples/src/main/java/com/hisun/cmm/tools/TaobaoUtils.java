/**
 * Title: TaobaoUtils.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2016<br/>
 * Company: cloudfn<br/>
 *
 */
package com.hisun.cmm.tools;

import java.util.Map;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.request.TimeGetRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.taobao.api.response.TimeGetResponse;

/**
 * Title: TaobaoUtils<br/>
 * Description: <br/>
 * Company: cloudfn<br/>
 * 
 * @author weipeng
 * @date 2016��1��12������3:51:14
 *
 */
public class TaobaoUtils {

    private static String url = "http://gw.api.taobao.com/router/rest";
    private static String appkey = "23298271";
    private static String secret = "d93fc7ebcf99cf765b69485c9f00e2c9";

    public static void main(String[] args) {
        sendSms();
        DefaultTaobaoClient client = new DefaultTaobaoClient(url, appkey,
                secret);
        TimeGetRequest request = new TimeGetRequest();
        TimeGetResponse response;
        try {
            response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println(response.getBody());
            }
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void sendSms() {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("�����֤");
        String params = "{\"code\":\"251314\",\"product\":\"tests \"}";
        // ��֤��${code}�������ڽ���${product}�����֤��������Ҫ���߱���Ŷ��
        req.setSmsParamString(params);
        req.setRecNum("15873312100");
        req.setSmsTemplateCode("SMS_3990948");
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
            Map<String, String> params2 = rsp.getParams();
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
