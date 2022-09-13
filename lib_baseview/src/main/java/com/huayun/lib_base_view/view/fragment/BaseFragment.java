package com.huayun.lib_base_view.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.huayun.lib_base_view.call.mvp.fragment.IBaseFragmentInterface;

import butterknife.ButterKnife;

/**
 * Fragment顶层基类
 */
public abstract class BaseFragment extends Fragment implements IBaseFragmentInterface {

    protected Context mContext;           //向子类提供Context参数（提高复用效率）
    private boolean isReuseView;          //是否复用View  （默认：复用）
    protected View mRootView;             //当前Fragment是否已创建（第一次默认为空）

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fragment状态
        initVariable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //设置xml-View
        View view = setContentView(inflater, getViewId());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (mRootView == null) {
            mRootView = view;
        }
        //判断是否复用View
        super.onViewCreated(isReuseView ? mRootView : view, savedInstanceState);
        //绑定ButterKnife
        ButterKnife.bind(this, view);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    /**
     * 调用该办法可避免重复加载UI
     */
    public View setContentView(LayoutInflater inflater, int resId) {
        if (mRootView == null) {
            mRootView = inflater.inflate(resId, null);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    /**
     * 初始化Fragment状态
     */
    private void initVariable() {
        mRootView = null;
        isReuseView = true;
    }


    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse
     */
    @Override
    public void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    /**
     * 加载视图
     *
     * @return
     */
    protected abstract int getViewId();

    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 初始化监听
     */
    protected abstract void initListener();


}
