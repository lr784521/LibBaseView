package com.huayun.lib_base_view.view.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.huayun.lib_base_view.call.mvp.activity.IBaseActivityInterface;
import com.huayun.lib_tools.constant.ConstantKeyBase;
import com.huayun.lib_tools.util.AppManager;
import com.huayun.lib_tools.util.database.MMkvHelper;
import com.huayun.lib_tools.util.language.LanguageUtil;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity顶层基类
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivityInterface {

    private Unbinder mUnBinder;//ButterKnife
    protected BaseActivity context;//提供给自子类context

    @Override
    protected void attachBaseContext(Context newBase) {
        //初始化语言
        String language = MMkvHelper.getInstance().getString(ConstantKeyBase.SAVE_LANGUAGE_KEY, Locale.getDefault().getLanguage());
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, language));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeOnCreate();
        super.onCreate(savedInstanceState);
        afterOnCreate(savedInstanceState);
        setContentView(getLayout());
        screenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//屏幕方向 默认竖屏
        context = this;
        //将当前Activity添加到栈中
        AppManager.getInstance().addActivity(this);
        //绑定ButterKnife
        mUnBinder = ButterKnife.bind(this);
        //注册ARouter路由
        ARouter.getInstance().inject(this);
        //初始化
        init();
        initListener();
    }

    /**
     * Activity onCreate 前操作
     */
    @Override
    public void beforeOnCreate() {

    }

    /**
     * Activity super.onCreate 后操作
     */
    @Override
    public void afterOnCreate(Bundle savedInstanceState) {

    }

    /**
     * 加载视图
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 设置屏幕方向
     *
     * @param screenOrientation
     */
    @Override
    public void screenOrientation(int screenOrientation) {
        setRequestedOrientation(screenOrientation);
    }

    /**
     * 页面销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        //销毁当前Activity
        AppManager.getInstance().finishActivity(this);
    }

}
