package com.huayun.lib_base_view.call.mvp.fragment;

/**
 * 为顶层Fragment提供扩展方法
 */
public interface IBaseFragmentInterface {
    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse 是否复用View
     */
    void reuseView(boolean isReuse);

}
