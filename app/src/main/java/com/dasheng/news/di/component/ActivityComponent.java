package com.dasheng.news.di.component;

import android.app.Activity;
import android.content.Context;

import com.dasheng.news.di.module.ActivityModule;
import com.dasheng.news.di.scope.ContextLife;
import com.dasheng.news.di.scope.PerActivity;
import com.dasheng.news.mvp.ui.activity.NewsActivity;
import com.dasheng.news.mvp.ui.activity.NewsChannelActivity;
import com.dasheng.news.mvp.ui.activity.NewsDetailActivity;
import com.dasheng.news.mvp.ui.activity.NewsPhotoDetailActivity;
import com.dasheng.news.mvp.ui.activity.PhotoActivity;
import com.dasheng.news.mvp.ui.activity.PhotoDetailActivity;

import dagger.Component;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/17 0017 11:01
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(NewsActivity newsActivity);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(NewsChannelActivity newsChannelActivity);

    void inject(NewsPhotoDetailActivity newsPhotoDetailActivity);

    void inject(PhotoActivity photoActivity);

    void inject(PhotoDetailActivity photoDetailActivity);
}
