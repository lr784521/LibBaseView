package com.huayun.lib_base_view.view.fragment;


import androidx.fragment.app.Fragment;

import com.huayun.lib_base_view.view.BasePresenter;
import com.huayun.lib_base_view.call.mvp.IModelInterface;
import com.huayun.lib_base_view.call.mvp.fragment.IFragmentPresenterInterface;

/**
 * 业务调度控制器
 *
 * @param <M> 数据层
 * @param <V> 视图层
 */
public abstract class BaseFragmentPresenter<M extends IModelInterface, V> extends BasePresenter<M, V> implements IFragmentPresenterInterface {


    public BaseFragmentPresenter(M mModel) {
        super(mModel);
    }

    /**
     * 页面创建
     */
    @Override
    public <VI extends Fragment> void onCreateFragment(VI view) {
        mView = (V) view;
        mModel.setFragment(view);
        onInit();
    }

    /**
     * 页面初始化
     */
    protected abstract void onInit();

}
