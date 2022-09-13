package com.huayun.lib_base_view.call.mvp.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.huayun.lib_base_view.call.mvp.IPresenterInterface;

/**
 * P层顶层接口定义
 */
public interface IActivityPresenterInterface extends IPresenterInterface {

    /**
     * 页面初始化
     * @param view
     * @param <VI>
     */
    <VI extends AppCompatActivity> void onCreateActivity(VI view);


}
