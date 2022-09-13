package com.huayun.lib_base_view.view.widget;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.huayun.lib_base_view.view.BasePresenter;
import com.huayun.lib_base_view.call.mvp.IModelInterface;
import com.huayun.lib_base_view.call.mvp.widget.IWidgetPresenterInterface;

/**
 * 业务调度控制器
 *
 * @param <M> 数据层
 * @param <V> 视图层
 */
public abstract class BaseWidgetPresenter<M extends IModelInterface, V> extends BasePresenter<M, V> implements IWidgetPresenterInterface {

    public BaseWidgetPresenter(M mModel) {
        super(mModel);
    }

    @Override
    public <VI> void onCreateActivityOrFragment(VI view, Object viewType) {
        mView =(V) view;
        if(viewType instanceof AppCompatActivity){
            mModel.setActivity((FragmentActivity) viewType);
        }else if(viewType instanceof Fragment){
            mModel.setFragment((Fragment) viewType);
        }else {
            throw new RuntimeException("View类型错误,限定:AppCompatActivity/Fragment");
        }
        onInit();
    }

    /**
     * 页面初始化
     */
    public abstract void onInit();

}
