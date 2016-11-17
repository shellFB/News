package com.dasheng.news;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void startLogoOuter() {
        AnimatorSet set=new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(logoOuterIv, "scaleX", 1, 1.25f, 0.75f, 1.15f, 1),
                ObjectAnimator.ofFloat(logoOuterIv, "scaleY", 1, 0.75f, 1.25f, 0.85f, 1)
        );
        set.setDuration(2000).start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void startInnerAnimation() {
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_top_in);
        logoInnerIv.startAnimation(animation);
    }
}
