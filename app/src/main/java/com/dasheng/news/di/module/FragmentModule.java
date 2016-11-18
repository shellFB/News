package com.dasheng.news.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.dasheng.news.di.scope.ContextLife;
import com.dasheng.news.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/17 0017 11:27
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment mFragment) {
        this.mFragment = mFragment;
    }
    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return mFragment;
    }
}
