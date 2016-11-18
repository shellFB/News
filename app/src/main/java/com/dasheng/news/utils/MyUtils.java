package com.dasheng.news.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.dasheng.news.MyApplication;
import com.dasheng.news.R;
import com.dasheng.news.common.Constants;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;

/**
 * @author shell
 * @version 1.0
 * @desc 白天夜晚模式
 * @since 2016/11/17 0017 14:15
 */
public class MyUtils {
    public static boolean isNightMode(){
        return (boolean)SPUtils.get(MyApplication.getAppContext(), Constants.NIGHT_THEME_MODE, false);
    }
    public static void saveTheme(boolean isNight){
        SPUtils.put(MyApplication.getAppContext(),Constants.NIGHT_THEME_MODE,isNight);
    }
    /**
     *@desc from yyyy-MM-dd HH:mm:ss to MM-dd HH:mm
     */
    public static String formatDate(String before){
        String after;
        try{
            Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .parse(before);
            after=new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(date);
        }catch (ParseException e){
            L.e(Constants.APP_LOG,"MyUtils:转换新闻日期格式异常:"+e.toString());
            return before;
        }
        return after;
    }
    public static int calculateTabWidth(TabLayout tabLayout){
        int tabWidth=0;
        for(int i=0;i<tabLayout.getChildCount();i++){
            final View view=tabLayout.getChildAt(i);
            view.measure(0,0);
            tabWidth+=view.getMeasuredWidth();
        }
        return tabWidth;
    }
    public static void dynamicSetTabLayoutMode(TabLayout tabLayout){
        int tabWidth=calculateTabWidth(tabLayout);
        int screenWidth=getScreenWidth();
        if(tabWidth<=screenWidth){
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }else{
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    public static int getScreenWidth() {
        return MyApplication.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }
    public static int getColor(int nightColor,int dayColor){
        int color;
        if(!MyUtils.isNightMode()){
            color=nightColor;
        }else{
            color=dayColor;
        }
        return color;
    }
    public static String analyzeNetworkError(Throwable e){
        String errMsg=MyApplication.getAppContext().getString(R.string.load_error);
        if(e instanceof HttpException){
            int state=((HttpException) e).code();
            if(state==403){
                errMsg=MyApplication.getAppContext().getString(R.string.retry_after);
            }
        }
        return errMsg;
    }
    public static void cancelSubscription(Subscription su){
        if(su!=null&&!su.isUnsubscribed()){
            su.unsubscribe();
        }
    }
    //解决InputMethodManager内存泄露现象
    public static void fixInputMethodManagerLeak(Context context){
        if(context==null){
            return;
        }
        InputMethodManager imm= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm==null){
            return;
        }
        String[] arr=new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f;
        Object obj;
        for(String param:arr){
            try{
                f=imm.getClass().getDeclaredField(param);
                if(!f.isAccessible()){
                    f.setAccessible(true);
                }
                obj=f.get(imm);
                if(obj!=null&&obj instanceof View){
                    View v= (View) obj;
                    if(v.getContext()==context){
                        f.set(imm,null);
                    }else{
                        break;
                    }
                }
            }catch (Throwable t){

            }
        }
    }
    public static View getRootView(Activity act){
        return ((ViewGroup) act.findViewById(android.R.id.content)).getChildAt(0);
    }
}
