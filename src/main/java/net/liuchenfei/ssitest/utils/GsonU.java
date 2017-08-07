package net.liuchenfei.ssitest.utils;

import com.google.gson.Gson;

/**
 * Created by liuchenfei on 2017/5/11.
 */
public class GsonU {
    public static String toJson(Object o){
        Gson gson=new Gson();
        return gson.toJson(o);
    }

    public <T> T toObject(String str,Class<T> clazz){
        Gson gson=new Gson();
        T t=gson.fromJson(str,clazz);
        return t;
    }
}
