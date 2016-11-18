package com.dasheng.news.di.module;

import android.app.Activity;
import android.content.Context;

import com.dasheng.news.di.scope.ContextLife;
import com.dasheng.news.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/17 0017 11:20
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }
    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext(){
        return mActivity;
    }
    @Provides
    @PerActivity
    public Activity provideActivity(){
        return mActivity;
    }

}
