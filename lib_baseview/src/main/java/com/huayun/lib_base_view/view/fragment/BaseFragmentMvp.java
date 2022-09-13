package com.huayun.lib_base_view.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import com.huayun.lib_base_view.call.mvp.IViewInterface;
import javax.inject.Inject;

/**
 * 处理Activity-Mvp业务
 *
 * @param <P> 指定P层实现类
 */
public abstract class BaseFragmentMvp<P extends BaseFragmentPresenter> extends BaseFragmentBusiness implements IViewInterface {

    @Inject
    protected P mPresenter; //业务调度控制器

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //回调P层生命周期
        mPresenter.onCreateFragment(this);
    }

    /**
     * 页面可见
     */
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    /**
     * 页面不可见
     */
    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    /**
     * 页面销毁
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
