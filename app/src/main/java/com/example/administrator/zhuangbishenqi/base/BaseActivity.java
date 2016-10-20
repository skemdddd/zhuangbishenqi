package com.example.administrator.zhuangbishenqi.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;


import com.example.administrator.zhuangbishenqi.utils.AppManager;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/7/15.
 */
public abstract class BaseActivity extends AppCompatActivity implements
        View.OnClickListener {
    private static final int ACTIVITY_RESUME = 0;
    private static final int ACTIVITY_STOP = 1;
    private static final int ACTIVITY_PAUSE = 2;
    private static final int ACTIVITY_DESTROY = 3;

    public int activityState;

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

    public abstract void initWidget();

    public abstract void widgetClick(View v);
    public abstract void findId();

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /***************************************************************************
     *
     * 打印Activity生命周期
     *
     ***************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(this.getClass() + "---------onCreat ");
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        AppManager.getAppManager().addActivity(this);
        initWidget();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.i( "---------onStart ",this.getClass());
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
        Logger.i( "---------onResume ",this.getClass());
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityState = ACTIVITY_STOP;
        Logger.i( "---------onStop ",this.getClass());
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
        Logger.i("---------onPause ",this.getClass());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.i("---------onRestart ",this.getClass());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = ACTIVITY_DESTROY;
        Logger.i( "---------onDestroy ",this.getClass());
        AppManager.getAppManager().finishActivity(this);
    }
}
