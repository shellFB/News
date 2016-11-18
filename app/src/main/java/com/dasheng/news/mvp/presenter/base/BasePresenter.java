package com.dasheng.news.mvp.presenter.base;

import android.support.annotation.Nullable;

import com.dasheng.news.mvp.view.base.BaseView;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/17 0017 10:19
 */
public interface BasePresenter {
    void onCreate();
    void attachView(@Nullable BaseView view);
    void onDestroy();
}
