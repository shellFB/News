package com.dasheng.news.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author shell
 * @version 1.0
 * @desc 获取APP唯一标识码
 * @since 2016/11/14 0014 10:12
 */
public class ApplicationID {
    private Context context;
    private ApplicationID(Context context){
        this.context=context;
    }
    private static ApplicationID instance=null;
    public static ApplicationID getInstance(Context context){
        if(instance==null){
            synchronized (ApplicationID.class){
                if(instance==null){
                    instance=new ApplicationID(context);
                }
            }
        }
        return instance;
    }
    /**
     *@desc Android系统为开发者提供的用于标识手机设备的串号
     * 优点：普适性较高，唯一性良好
     * 限制：1.非手机设备（平板，电脑，电子书等）：这些设备没有通话的硬件功能
     *      2.权限问题：获取DEVICE_ID需要READ_PHONE_STATE权限，如果仅仅是为了获取唯一标识码，没有用到通话等其他功能，会有损性能，并且带来安全问题
     *      3.厂商定制系统的Bug：少数手机设备上，会有漏洞
     * 权限：<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *@param
     */
    public String getDeviceID(){
        TelephonyManager tm= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId().toString();
    }
    /**
     *@desc 可以使用手机WIFI或蓝牙的MAC地址作为设备标识
     * 限制：1.硬件的限制：并不是所有的设备都有WIFI和蓝牙硬件
     *      2.获取的限制：如果wifi没有打开，无法获取mac地址
     * 权限：<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     *@param
     */
    public String getMacAdaress(){
        // 获取mac地址：
        String macAddress = "000000000000";
        try {
            WifiManager wifiMgr = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
            Log.i("====",wifiMgr.getConnectionInfo()+"++++++");
            if (null != info) {
                if (!TextUtils.isEmpty(info.getMacAddress()))
                    macAddress = info.getMacAddress().replace(":", "");
                else
                    return macAddress;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return macAddress;
        }
        return macAddress;
    }
    /**
     *@desc 通过SIM卡获取设备唯一标识
     * 限制：1.必须装有Sim卡
     *      2.和DEVICE_ID一样的限制
     *      3.CDMA（码分多址的英文缩写(CodeDivisionMultipleAccess)，它是在数字技术的分支--扩频通信技术上发展起来的一种崭新而成熟的无线通信技术）设备上返回null
     * 权限：<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *@param
     */
    public String getSimID(){
        TelephonyManager tm= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if(tm.getSimState()==TelephonyManager.SIM_STATE_READY){
            return tm.getSimSerialNumber();
        }else{
            return tm.getSimState()+"----";
        }

    }


}
