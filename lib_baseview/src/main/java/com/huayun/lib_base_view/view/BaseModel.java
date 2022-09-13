package com.huayun.lib_base_view.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.huayun.lib_base_view.call.hilt.IDB;
import com.huayun.lib_base_view.call.mvp.IModelInterface;
import com.huayun.lib_base_view.call.hilt.IApplication;

import java.util.Map;

import javax.inject.Inject;

/**
 * 用于向Model提供View层的生命周期：
 * 动态做网络请求生命周期销毁
 */
public class BaseModel implements IModelInterface {
    @Inject
    public IApplication app;
    @Inject
    public IDB db;
    protected Map<String,Object> requestMap;
    protected FragmentActivity activity;
    protected Fragment fragment;

    @Override
    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
