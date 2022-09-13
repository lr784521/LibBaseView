package com.huayun.lib_base_view.call.mvp;

/**
 * P层顶层接口定义
 */
public interface IPresenterInterface {

    /**
     * 页面可见
     */
    void onResume();

    /**
     * 页面不可见
     */
    void onPause();

    /**
     * 页面销毁
     */
    void onDestroy();
}
