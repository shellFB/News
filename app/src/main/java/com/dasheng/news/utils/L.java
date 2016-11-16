package com.dasheng.news.utils;


import android.util.Log;
/**
 * @author shell
 * @version 1.0
 * @desc log打印的工具类
 * @since 2016/8/24 0024 10:35
 */
public final class L {

    private final static int LEVEL = 5;

    private final static String DEFAULT_TAG = "L";

    private L() {
        throw new UnsupportedOperationException("L cannot instantiated!");
    }

    public static void v(String tag,String msg) {
        if(LEVEL >= 5)Log.v(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }

    public static void d(String tag,String msg) {
        if(LEVEL >= 4)Log.d(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }

    public static void i(String tag,String msg) {
        if(LEVEL >= 3)Log.i(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }

    public static void w(String tag,String msg) {
        if(LEVEL >= 2)Log.w(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }

    public static void e(String tag,String msg) {
        if(LEVEL >= 1)Log.e(tag == null ? DEFAULT_TAG:tag,msg == null?"":msg);
    }
}
