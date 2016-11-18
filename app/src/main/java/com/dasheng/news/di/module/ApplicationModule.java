package com.dasheng.news.di.module;

import android.content.Context;

import com.dasheng.news.MyApplication;
import com.dasheng.news.di.scope.ContextLife;
import com.dasheng.news.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/17 0017 11:24
 */
@Module
public class ApplicationModule {
    private MyApplication mApplication;

    public ApplicationModule(MyApplication mApplication) {
        this.mApplication = mApplication;
    }
    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext(){
        return mApplication.getApplicationContext();
    }
}
