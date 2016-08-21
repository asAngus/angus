package com.btc.weipeng.btc.utils;

import com.btc.weipeng.btc.vo.BtVo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by weipeng on 16/8/13.
 */
public class Json2Object {

    public static <T> T json2Object(JSONObject jsonObject, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            Field[] fields = clazz.getFields();

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object o = jsonObject.get(field.getName());
                    if (o != null) {
                        field.set(o, t);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static void main(String[] args) {
        JSONObject j = new JSONObject();
        try {
            j.put("dayNumber","1");
            json2Object(j, BtVo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
