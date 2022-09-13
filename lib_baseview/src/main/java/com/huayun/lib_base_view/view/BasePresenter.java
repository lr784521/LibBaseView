package com.huayun.lib_base_view.view;

import com.huayun.lib_base_view.call.mvp.IModelInterface;
import com.huayun.lib_base_view.call.mvp.IPresenterInterface;

/**
 * 业务调度控制器
 *
 * @param <M> 数据层
 * @param <V> 视图层
 */
public abstract class BasePresenter<M extends IModelInterface, V> implements IPresenterInterface {

    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel) {
        this.mModel = mModel;
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
}
