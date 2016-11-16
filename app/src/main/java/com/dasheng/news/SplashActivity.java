package com.dasheng.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dasheng.news.utils.WindowTittle;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/16 0016 15:25
 */
public class SplashActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowTittle.setWindowTittle(this);
        overridePendingTransition(R.anim.zoomin,0);//页面加载动画
        setContentView(R.layout.activity_splash);
        initAnimation();
    }

    private void initAnimation() {
        startInnerAnimation();
        startLogoOuterAndAppName();
    }
    private void startLogoOuterAndAppName() {
    }

    private void startInnerAnimation() {
    }
}
