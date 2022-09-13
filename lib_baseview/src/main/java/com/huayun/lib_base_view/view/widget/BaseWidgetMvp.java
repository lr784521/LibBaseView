package com.huayun.lib_base_view.view.widget;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.huayun.lib_base_view.call.mvp.IViewInterface;
import com.huayun.lib_base_view.call.mvp.widget.IWidgetPresenterInterface;

/**
 * 处理Activity-Mvp业务
 */
public abstract class BaseWidgetMvp extends RelativeLayout implements IViewInterface {

    protected IWidgetPresenterInterface mPresenter; //业务调度控制器
    protected AppCompatActivity activity;
    protected Fragment fragment;

    public BaseWidgetMvp(Context context) {
        super(context);
    }

    public BaseWidgetMvp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWidgetMvp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void setWidgetPresenter(IWidgetPresenterInterface mPresenter, Object viewType) {
        this.mPresenter = mPresenter;
        if (viewType instanceof AppCompatActivity) {
            this.activity = (AppCompatActivity) viewType;
            registerActivityLife();
        } else if (viewType instanceof Fragment) {
            this.fragment = (Fragment) viewType;
            registerFragmentLife();
        } else {
            throw new RuntimeException("View类型错误,限定:AppCompatActivity/Fragment");
        }
    }

    /**
     * 注册Fragment生命周期监听
     */
    private void registerFragmentLife(){
        fragment.getParentFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentStarted(fm, f);
                //回调P层生命周期
                mPresenter.onCreateActivityOrFragment(BaseWidgetMvp.this, fragment);
            }

            @Override
            public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentResumed(fm, f);
                mPresenter.onResume();
            }

            @Override
            public void onFragmentPaused(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentPaused(fm, f);
                mPresenter.onPause();
            }

            @Override
            public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentDestroyed(fm, f);
                mPresenter.onDestroy();
            }
        }, false);
    }

    /**
     * 注册Activity生命周期监听
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void registerActivityLife() {
        activity.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            }

            @Override
            public void onActivityStarted(@NonNull Activity ac) {
                //回调P层生命周期
                mPresenter.onCreateActivityOrFragment(BaseWidgetMvp.this, activity);
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                mPresenter.onResume();
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                mPresenter.onPause();
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                mPresenter.onDestroy();
            }
        });
    }



}
