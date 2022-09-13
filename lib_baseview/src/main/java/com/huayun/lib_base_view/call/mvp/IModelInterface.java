package com.huayun.lib_base_view.call.mvp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * M层顶层接口 提供Activity/Fragment接口
 * 用于网络请求生命周期管理
 */
public interface IModelInterface {
    /**
     * M层获取View生命周期
     * @param activity  由P层给M层
     */
    void setActivity(FragmentActivity activity);

    /**
     * M层获取View生命周期
     * @param fragment  由P层给M层
     */
    void setFragment(Fragment fragment);
}
