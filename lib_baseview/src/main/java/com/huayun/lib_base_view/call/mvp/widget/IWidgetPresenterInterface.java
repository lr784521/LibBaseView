package com.huayun.lib_base_view.call.mvp.widget;

import com.huayun.lib_base_view.call.mvp.IPresenterInterface;


/**
 * P层顶层接口定义
 */
public interface IWidgetPresenterInterface extends IPresenterInterface {

    /**
     * 页面初始化
     *
     * @param view
     * @param <VI>
     */
    <VI> void onCreateActivityOrFragment(VI view, Object viewType);


}
