package com.dasheng.news;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasheng.news.utils.WindowTittle;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/16 0016 15:25
 */
public class SplashActivity extends AppCompatActivity {
    boolean isShowingRubberEffect = false;
    @InjectView(R.id.logo_outer_iv)
    ImageView logoOuterIv;
    @InjectView(R.id.logo_inner_iv)
    ImageView logoInnerIv;
    @InjectView(R.id.app_name_tv)
    TextView appNameTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowTittle.setWindowTittle(this);
        overridePendingTransition(R.anim.zoomin, 0);//页面加载动画
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        initAnimation();
    }

    private void initAnimation() {
        startInnerAnimation();
        startShowAppName();
        startLogoOuter();
    }

    private void startShowAppName() {
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.anim_splash_appname);
        appNameTv.startAnimation(animation);
    }

    private void startLogoOuter() {

    }

    private void startInnerAnimation() {
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_top_in);
        logoInnerIv.startAnimation(animation);
    }
}
