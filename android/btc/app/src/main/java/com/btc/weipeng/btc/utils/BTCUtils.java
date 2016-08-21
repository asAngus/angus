package com.btc.weipeng.btc.utils;

import com.btc.weipeng.btc.vo.BtVo;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weipeng on 16/8/11.
 */
public abstract class BTCUtils {
    public static List<BtVo> getPrices() {

        List<BtVo> list = new ArrayList<>(3);
        list.add(getEtcPrice(BtVo.class));
        list.add(getEthPrice(BtVo.class));
        list.add(getBtcPrice(BtVo.class));
        return list;
    }

    public static BtVo getEtcPrice(Class<BtVo> clazz) {
        BtVo price = getPrice(HttpUtils.etcUrl, clazz);
        price.setName("ETC:");
        return price;
    }

    public static BtVo getEthPrice(Class<BtVo> clazz) {
        BtVo price = getPrice(HttpUtils.ethUrl, clazz);
        price.setName("ETH:");
        return price;
    }

    public static BtVo getBtcPrice(Class<BtVo> clazz) {
        BtVo price = getPrice(HttpUtils.btcUrl, clazz);
        price.setName("BTC:");
        return price;
    }

    public static BtVo getPrice(String url, Class<BtVo> clazz) {

        String ethInfo = HttpUtils.readContentFromGet(url);
        if (StringUtils.isEmpty(ethInfo)) {
            return new BtVo();
        }
        String substring = ethInfo.substring(ethInfo.indexOf("(") + 1, ethInfo.indexOf(")"));
        org.json.JSONArray s = null;
        try {
            s = new org.json.JSONArray(substring);
            org.json.JSONObject jsonObject = s.getJSONObject(0);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //BtVo object = objects.getObject(0, clazz);
        // System.out.println(object);
        return null;
    }
}
