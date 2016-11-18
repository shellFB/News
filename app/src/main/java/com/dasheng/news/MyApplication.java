package com.dasheng.news;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatDelegate;

import com.dasheng.news.common.Constants;
import com.dasheng.news.dao.DaoMaster;
import com.dasheng.news.dao.DaoSession;
import com.dasheng.news.dao.NewsChannelTableDao;
import com.dasheng.news.di.component.ApplicationComponent;
import com.dasheng.news.di.component.DaggerActivityComponent;
import com.dasheng.news.di.component.DaggerApplicationComponent;
import com.dasheng.news.di.module.ApplicationModule;
import com.dasheng.news.utils.L;
import com.dasheng.news.utils.MyUtils;
import com.dasheng.news.utils.SPUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author shell
 * @version 1.0
 * @desctt
 * @since 2016/11/17 0017 9:23
 */
public class MyApplication extends Application {
    private static final String TAG="MyApplication";
    private ApplicationComponent mApplicationComponent;
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context){
        MyApplication application=(MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }
    private static Context sAppContext;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext=this;
        initLeakCanary();
        initActivityLifecycleLogs();
        setUpDatabase();
        initStrictMode();
        initDayNightMode();
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        mApplicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initDayNightMode() {
        if(MyUtils.isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initStrictMode() {
        if(BuildConfig.DEBUG){
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyDialog()//弹出违规提示对话框
                            .penaltyLog()//打印违规异常信息
                            .build()
            );
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            );

        }
    }

    //数据库初始化
    private void setUpDatabase() {
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this, Constants.DB_NAME, null);
        SQLiteDatabase db=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db);
        mDaoSession=daoMaster.newSession();
        QueryBuilder.LOG_SQL=BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES=BuildConfig.DEBUG;
    }

    private void initActivityLifecycleLogs() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                L.i(TAG,activity+"  onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                L.i(TAG,activity+"  onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                L.i(TAG,activity+"  onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                L.i(TAG,activity+"  onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                L.i(TAG,activity+"  onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                L.i(TAG,activity+"  onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                L.i(TAG,activity+"  onActivityDestroyed");
            }
        });
    }

    //内存泄露跟踪
    private void initLeakCanary() {
        if(BuildConfig.DEBUG){
            refWatcher= LeakCanary.install(this);
        }else{
            refWatcher=installLeakCanary();
        }
    }

    private RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }
    public static Context getAppContext(){
        return sAppContext;
    }
    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }
    public static NewsChannelTableDao getNewsChannelTableDao(){
        return mDaoSession.getNewsChannelTableDao();
    }
    public static boolean isHavaPhoto(){
        return (boolean)SPUtils.get(sAppContext,Constants.SHOW_NEWS_PHOTO,true);
    }
}
