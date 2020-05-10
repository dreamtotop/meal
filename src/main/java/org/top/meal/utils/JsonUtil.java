package org.top.meal.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * json序列化
 */
public class JsonUtil {

    /*
    将对象格式化为json格式
     */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
