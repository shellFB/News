package com.dasheng.news.mvp.ui.activity.base;
import android.annotation.TargetApi;
import android.app.Application;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import com.dasheng.news.MyApplication;
import com.dasheng.news.R;
import com.dasheng.news.annotation.BindValues;
import com.dasheng.news.di.component.ActivityComponent;
import com.dasheng.news.di.component.DaggerActivityComponent;
import com.dasheng.news.mvp.presenter.base.BasePresenter;
import com.dasheng.news.mvp.ui.activity.NewsDetailActivity;
import com.dasheng.news.mvp.ui.activity.PhotoActivity;
import com.dasheng.news.mvp.ui.activity.PhotoDetailActivity;
import com.dasheng.news.utils.MyUtils;
import com.dasheng.news.utils.NetWorkUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import rx.Subscription;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/17 0017 10:17
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    protected ActivityComponent mActivityComponent;
    private WindowManager mWindowManager=null;
    private View mNightView=null;
    private boolean mIsAddedView;
    protected T mPresenter;
    protected boolean mIsHasNavigationView;
    private DrawerLayout mDrawerLayout;
    private Class mClass;
    public abstract int getLayoutId();
    public abstract void initInjector();
    public abstract void initViews();
    protected Subscription mSubscription;
    protected NavigationView mBaseNavView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAnnotation();
        NetWorkUtil.isConnected(this);
        initActivityComponent();
        setStatusBarTranslucent();
        setNightOrDayMode();
        int layoutId=getLayoutId();
        setContentView(layoutId);
        initInjector();
        initToolBar();
        initViews();
        if(mIsHasNavigationView){
            initDrawerLayout();
        }
    }

    private void initDrawerLayout() {
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
    }

    private void initToolBar() {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private  void setNightOrDayMode(){
        if(MyUtils.isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            initNightView();
            mNightView.setBackgroundResource(R.color.night_mask);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected  void setStatusBarTranslucent(){
        if((Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                !(this instanceof NewsDetailActivity || this instanceof PhotoActivity
                        || this instanceof PhotoDetailActivity))
                || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT
                && this instanceof NewsDetailActivity)){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
    }

    private void initActivityComponent() {
        mActivityComponent= DaggerActivityComponent.builder()
                .applicationComponent(((MyApplication)getApplication()).getApplicationComponent())
                .build();
    }

    private void initAnnotation(){
        if(getClass().isAnnotationPresent(BindValues.class)){
            BindValues annotation=getClass().getAnnotation(BindValues.class);
            mIsHasNavigationView=annotation.mIsHasNavigationView();
        }
    }
    public void changeToDay(){
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mNightView.setBackgroundResource(android.R.color.transparent);
    }
    public void changeToNight(){
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        initNightView();
        mNightView.setBackgroundResource(R.color.night_mask);
    }

    private  void initNightView(){
        if(mIsAddedView){
            return;
        }
        //添加夜间模式蒙板
        WindowManager.LayoutParams nightViewParam=new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        mWindowManager= (WindowManager) getSystemService(WINDOW_SERVICE);
        mNightView=new View(this);
        mWindowManager.addView(mNightView,nightViewParam);
        mIsAddedView=true;
    }
}
