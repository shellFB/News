package com.dasheng.news.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author shell
 * @version 1.0
 * @desc 判断网络的工具类
 * @since 2016/8/24 0024 9:57
 */
public class NetWorkUtil {
    private NetWorkUtil(){
        throw new UnsupportedOperationException("网络连接异常");
    }
    /**
     *@desc 判断网络是否连接
     *
     */
    public static boolean isConnected(Context context){
        ConnectivityManager connectivity= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity!=null){
            NetworkInfo info=connectivity.getActiveNetworkInfo();
            if(null!=info&&info.isConnected()){
                if(info.getState()==NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     *@desc 判断是否是WiFi连接
     *
     */
    public static boolean isWifi(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager==null){
            return false;
        }
        return manager.getActiveNetworkInfo().getType()==ConnectivityManager.TYPE_WIFI;
    }
    /**
     *@desc 打开网络设置界面
     *
     */
    public static void openSetting(Activity act){
        /*Intent intent=new Intent("/");
        ComponentName component=new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
        intent.setComponent(component);
        intent.setAction("android.intent.action.VIEW");
        act.startActivityForResult(intent,0);*/
        if(android.os.Build.VERSION.SDK_INT > 10 ){
            //3.0以上打开设置界面
            act.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        }else
        {
            act.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }
    /**
     *@desc 使用SSL不信任的证书
     *
     */
    public static void userUntrustedCertificate(){
        TrustManager[] manager=new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };
        //下载信任的证书
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, manager, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
