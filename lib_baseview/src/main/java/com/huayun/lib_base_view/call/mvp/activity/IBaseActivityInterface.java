package com.huayun.lib_base_view.call.mvp.activity;

import android.os.Bundle;

/**
 * 为顶层Activity提供扩展方法
 */
public interface IBaseActivityInterface {
    /**
     * OnCreate触发之前回调
     */
    void beforeOnCreate();

    /**
     * OnCreate触发之后回调
     * @param savedInstanceState
     */
    void afterOnCreate(Bundle savedInstanceState);

    /**
     * 屏幕方向设置  默认竖屏
     * @param screenOrientation  方向(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
     */
    void screenOrientation(int screenOrientation);
}
