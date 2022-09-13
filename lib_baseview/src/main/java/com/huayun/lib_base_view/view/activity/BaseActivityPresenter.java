package com.huayun.lib_base_view.view.activity;


import androidx.appcompat.app.AppCompatActivity;

import com.huayun.lib_base_view.view.BasePresenter;
import com.huayun.lib_base_view.call.mvp.IModelInterface;
import com.huayun.lib_base_view.call.mvp.activity.IActivityPresenterInterface;

/**
 * 业务调度控制器
 *
 * @param <M> 数据层
 * @param <V> 视图层
 */
public abstract class BaseActivityPresenter<M extends IModelInterface, V> extends BasePresenter<M, V> implements IActivityPresenterInterface {

    public BaseActivityPresenter(M mModel) {
        super(mModel);
    }

    /**
     *页面创建
     */
    @Override
    public <VI extends AppCompatActivity> void onCreateActivity(VI view) {
        mView =(V) view;
        mModel.setActivity(view);
        onInit();
    }

    /**
     *页面初始化
     */
    protected abstract void onInit();

}
