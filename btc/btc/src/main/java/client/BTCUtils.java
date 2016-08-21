package client;

import client.vo.BtVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

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
        String ethInfo = HttpUtils.doGet(url);
        if (StringUtils.isEmpty(ethInfo)) {
            return new BtVo();
        }
        String substring = ethInfo.substring(ethInfo.indexOf("(") + 1, ethInfo.indexOf(")"));
        JSONArray objects = JSON.parseArray(substring);
        BtVo object = objects.getObject(0, clazz);
        // System.out.println(object);
        return object;
    }
}
