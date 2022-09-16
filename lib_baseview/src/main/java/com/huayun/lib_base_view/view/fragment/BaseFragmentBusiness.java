package com.huayun.lib_base_view.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.huayun.lib_base_view.R;
import com.huayun.lib_base_view.call.mvp.fragment.IBaseFragmentBusinessInterface;
import com.huayun.lib_base_view.view.activity.BaseActivityBusiness;
import com.huayun.lib_base_view.widget.CustomTitle;
import com.huayun.lib_tools.util.SoftKeyboardUtil;
import com.huayun.lib_tools.util.event.EventMessage;
import com.huayun.lib_tools.util.toast.ToastEnum;
import com.huayun.lib_tools.util.toast.ToastUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;


/**
 * Fragment基础业务
 */
public abstract class BaseFragmentBusiness extends BaseFragment implements IBaseFragmentBusinessInterface {

    public Map<String, Object> map; //用于子类传参使用
    private CustomTitle title; //自定义标题栏
    protected BaseActivityBusiness context; //父Activity
    protected int pageNo=1;//页码
    protected int pageSize=20;//页大小

    //状态栏样式
    private int statusBarStyleType = 0; //状态栏样式类型
    private int defaultStatusBarColor; //类型0.单一颜色样式
    private int defaultStatusBarDrawableColor; //类型1.文件渐变颜色样式
    private int immersionBarTitleStyle; //当设置沉浸式状态栏时 标题栏颜色
    private boolean isLight; //状态栏文字颜色  light模式：状态栏字体 true: 灰色，false: 白色 Android 6.0+
    //样式配置
    private boolean titleStyleIsToBar = true; //设置标题栏颜色是否与状态栏颜色一致
    private boolean fitWindow = true; //布局是否侵入状态栏（true 不侵入，false 侵入）

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取父Activity
        context = (BaseActivityBusiness) getActivity();
        //绑定EventBus
        if (isBindEventBusHere() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //初始化状态栏样式设置
        initStatusBarStyle();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 初始化状态栏样式
     */
    private void initStatusBarStyle() {
        statusBarStyleType = setStatusBarStyleType();
        defaultStatusBarColor = setStatusBarDefaultColor();
        defaultStatusBarDrawableColor = setStatusBarDefaultDrawableColor();
        immersionBarTitleStyle = setStatusBarImmersionTitleBarStyle();
        isLight = setStatusBarTextColor();
        initStatusBar(statusBarStyleType);
    }

    /**
     * 初始化状态栏
     *
     * @param type 状态栏类型
     */
    public void initStatusBar(int type) {
        switch (type) {
            case -1:
                //不使用默认状态栏配置  自行在Activity中动态处理
                //标题栏 不与状态栏保持一致
                titleStyleIsToBar = false;
                break;
            case 0: //单一颜色样式   使用场景  单一颜色状态栏
                UltimateBarX.statusBarOnly(this)
                        .fitWindow(fitWindow)
                        .colorRes(defaultStatusBarColor)
                        .light(isLight)
                        .lvlColorRes(defaultStatusBarColor)
                        .apply();
                break;
            case 1: //文件渐变颜色样式   使用场景  渐变色状态栏  支持透明色值
                UltimateBarX.statusBarOnly(this)
                        .fitWindow(fitWindow)
                        .drawableRes(defaultStatusBarDrawableColor)
                        .light(isLight)
                        .lvlDrawableRes(defaultStatusBarDrawableColor)
                        .apply();
                break;
            case 2: //沉浸式状态栏
                UltimateBarX.statusBarOnly(this)
                        .fitWindow(true)
                        .light(isLight)
                        .transparent()
                        .apply();
                break;
        }
    }

    /**
     * 页面销毁
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消EventBus注册
        if (isBindEventBusHere() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * EventBus 需要注册EventBus的Activity重写该方法,返回true
     *
     * @return true 注册  false 不注册
     */
    @Override
    public boolean isBindEventBusHere() {
        return false;
    }

    /**
     * EventBus 主线程运行
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage event) {
        if (event != null) {
            onEventComing(event);
        }
    }

    /**
     * Event消息接收回调
     *
     * @param event 消息实体
     */
    @Override
    public void onEventComing(EventMessage event) {

    }

    /**
     * 提示信息
     *
     * @param type 0 成功 1失败 2警告
     * @param msg  信息
     */
    @Override
    public void showToast(ToastEnum type, String msg) {
        ToastUtil.getInstance().showToast(type, msg);
    }

    /**
     * 提示信息
     *
     * @param type 0 成功 1失败 2警告
     * @param msg  信息
     */
    @Override
    public void showToast(ToastEnum type, int msg) {
        ToastUtil.getInstance().showToast(type, msg);
    }

    /**
     * 经典通知栏
     *
     * @param tag         标记
     * @param title       标题
     * @param content     内容
     * @param aClass      跳转界面
     * @param autoCancel  点击通知后自动消失
     * @param isDisappear 点击是否不消失
     */
    @Override
    public void showSystemNotification(int tag, String title, String content, Class aClass, boolean autoCancel, boolean isDisappear,int logo) {
        context.showSystemNotification(tag, title, content, aClass, autoCancel, isDisappear,logo);
    }

    /**
     * 自定义通知栏
     *
     * @param tag         标记
     * @param aClass      跳转界面
     * @param isDisappear 点击是否不消失
     */
    @Override
    public void showCustomNotification(int tag, Class aClass, boolean isDisappear,int logo) {
        context.showCustomNotification(tag, aClass, isDisappear,logo);
    }

    /**
     * 给标题栏添加监听，若不设置，默认只有左上角返回键有响应
     */
    public void setTitleListener(int titleId) {

        //初始化标题栏控件
        title = (CustomTitle) mRootView.findViewById(titleId);

        //标题栏左边返回键点击事件
        //如果在子界面不重写本方法 则默认关闭当前界面
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBack();
            }
        });

        //标题栏右边图片点击事情
        title.setMoreImgAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRight();
            }
        });

        //标题栏右边文字点击事件
        title.setMoreTextAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRight();
            }
        });

        titleStyleIsToBar = setTitleStyleIsToStatusBar();

        if (titleStyleIsToBar) {
            //标题样式与状态栏保持一致   注：需在xml中给控件设置id  并在 界面中 设置监听setTitleListener(id)
            switch (statusBarStyleType) {
                case 0:
                    title.setTitleBg(defaultStatusBarColor);
                    break;
                case 1:
                    title.setTitleBg(defaultStatusBarDrawableColor);
                    break;
                case 2:
                    title.setTitlePadding();
                    title.setTitleBg(immersionBarTitleStyle);
                    break;
            }
        } else {
            switch (statusBarStyleType) {
                case 1:
                    title.setTitleBg(defaultStatusBarDrawableColor);
                    break;
                case 2:
                    title.setTitlePadding();
                    title.setTitleBg(immersionBarTitleStyle);
                    break;
                default:
                    title.setTitleBg(defaultStatusBarColor);
                    break;
            }
        }
    }

    /**
     * 标题栏-右上角点击事件
     */
    @Override
    public void toRight() {

    }

    /**
     * 点击标题栏左上角
     */
    @Override
    public void toBack() {
        SoftKeyboardUtil.Closekeyboard(context);
    }

    /**
     * 设置状态栏样式类型
     *
     * @return -1 不使用默认状态栏配置  自行在Activity中动态处理
     * 标题栏 不与状态栏保持一致
     * 0  单一颜色样式   使用场景  单一颜色状态栏
     * 1  文件渐变颜色样式   使用场景  渐变色状态栏  支持透明色值
     * 2  沉浸式状态栏
     */
    @Override
    public int setStatusBarStyleType() {
        return statusBarStyleType;
    }

    /**
     * 设置状态栏样式
     * 单一颜色样式
     *
     * @return 状态栏颜色(return R.color.status_bar_color ;)
     */
    @Override
    public int setStatusBarDefaultColor() {
        return R.color.status_bar_color;
    }

    /**
     * 当设置沉浸式状态栏时:标题栏颜色
     *
     * @return 标题栏颜色(return R.color.status_bar_color ;)
     */
    @Override
    public int setStatusBarImmersionTitleBarStyle() {
        return R.color.status_bar_color;
    }

    /**
     * 设置状态栏样式
     *
     * @return 文件渐变颜色样式 (return R.drawable.shape_status_bar_bg;)
     */
    @Override
    public int setStatusBarDefaultDrawableColor() {
        return R.drawable.shape_status_bar_bg;
    }

    /**
     * 设置状态栏文字颜色
     *
     * @return light模式：状态栏字体 true: 灰色，false: 白色 Android 6.0+
     */
    @Override
    public boolean setStatusBarTextColor() {
        return true;
    }

    /**
     * 布局是否侵入状态栏
     *
     * @return true 不侵入，false 侵入
     */
    @Override
    public boolean setFitWindow() {
        return true;
    }

    /**
     * 设置标题栏样式是否与状态栏样式保持一致
     *
     * @return true 一致  false 不一致
     */
    @Override
    public boolean setTitleStyleIsToStatusBar() {
        return true;
    }

    /**
     * RecyclerView 无数据时空视图展示
     *
     * @param layout    刷新布局
     * @param emptyView 空布局
     * @param list      列表数据
     */
    @Override
    public void recyclerViewEmptyView(SmartRefreshLayout layout, View emptyView, List list) {
        if (pageNo == 1 && list.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        } else {
            if (pageNo == 1 && list.size() > 0) {
                layout.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * RecyclerView 无数据时空视图展示
     *
     * @param rvList    刷新布局
     * @param emptyView 空布局
     * @param list      列表数据
     */
    @Override
    public void recyclerViewEmptyView(RecyclerView rvList, View emptyView, List list) {
        if (list.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            rvList.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            rvList.setVisibility(View.VISIBLE);
        }
    }
}
