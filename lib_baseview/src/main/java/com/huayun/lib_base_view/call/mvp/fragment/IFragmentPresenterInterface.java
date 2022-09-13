package com.huayun.lib_base_view.call.mvp.fragment;

import androidx.fragment.app.Fragment;

import com.huayun.lib_base_view.call.mvp.IPresenterInterface;

/**
 * P层顶层接口定义
 */
public interface IFragmentPresenterInterface extends IPresenterInterface {
    /**
     * 页面初始化
     * @param view
     * @param <VI>
     */
    <VI extends Fragment> void onCreateFragment(VI view);


}
