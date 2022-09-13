package com.huayun.lib_base_view.view.activity;

import android.os.Bundle;
import com.huayun.lib_base_view.call.mvp.IViewInterface;
import javax.inject.Inject;

/**
 * 处理Activity-Mvp业务
 *
 * @param <P> 指定P层实现类
 */
public abstract class BaseActivityMvp<P extends BaseActivityPresenter> extends BaseActivityBusiness implements IViewInterface {

    @Inject
    protected P mPresenter; //业务调度控制器

    @Override
    public void afterOnCreate(Bundle savedInstanceState) {
        super.afterOnCreate(savedInstanceState);
        //回调P层生命周期
        mPresenter.onCreateActivity(this);
    }

    /**
     * 页面可见
     */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    /**
     * 页面不可见
     */
    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    /**
     * 页面销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

}
