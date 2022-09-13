package com.huayun.lib_base_view.call.mvp;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.huayun.lib_tools.constant.event.EventMessage;
import com.huayun.lib_tools.util.toast.ToastEnum;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * Activity、Fragment通用业务
 */
public interface IBaseViewBusinessInterface {

    /**
     * 提示信息
     *
     * @param type 0 成功 1失败 2警告
     * @param msg  信息
     */
    void showToast(ToastEnum type, String msg);

    /**
     * 提示信息
     *
     * @param type 0 成功 1失败 2警告
     * @param msg  信息
     */
    void showToast(ToastEnum type, int msg);

    /**
     * 系统通知栏
     *
     * @param tag         标记
     * @param title       标题
     * @param content     内容
     * @param aClass      跳转界面
     * @param autoCancel  点击通知后自动消失
     * @param isDisappear 点击是否不消失
     */
    void showSystemNotification(int tag, String title, String content, Class aClass, boolean autoCancel, boolean isDisappear,int logo);

    /**
     * 自定义通知栏
     *
     * @param tag         标记
     * @param aClass      跳转界面
     * @param isDisappear 点击是否不消失
     */
    void showCustomNotification(int tag, Class aClass, boolean isDisappear,int logo);

    /**
     * Event消息接收回调
     *
     * @param event 消息实体
     */
    void onEventComing(EventMessage event);

    /**
     * EventBus 需要注册EventBus的Activity重写该方法,返回true
     *
     * @return true 注册  false 不注册
     */
    boolean isBindEventBusHere();

    /**
     * 初始化状态栏
     *
     * @param type 样式类型
     *             0  单一颜色样式   使用场景  单一颜色状态栏
     *             1  文件渐变颜色样式   使用场景  渐变色状态栏  支持透明色值
     *             2  沉浸式状态栏
     */
    void initStatusBar(int type);

    /**
     * 标题栏-添加监听，若不设置，默认只有左上角返回键有响应
     *
     * @param titleId 标题栏控件ID
     */
    void setTitleListener(int titleId);

    /**
     * 标题栏-右上角点击事件
     */
    void toRight();

    /**
     * 标题栏-左上角返回点击事件
     */
    void toBack();


    /**
     * 设置状态栏样式类型
     *
     * @return -1 不使用默认状态栏配置  自行在Activity中动态处理  标题栏 不与状态栏保持一致
     * <p>
     * 0  单一颜色样式   使用场景  单一颜色状态栏
     * 1  文件渐变颜色样式   使用场景  渐变色状态栏  支持透明色值
     * 2  沉浸式状态栏
     */
    int setStatusBarStyleType();

    /**
     * 设置状态栏样式
     * 单一颜色样式
     *
     * @return 状态栏颜色(return R.color.status_bar_color ;)
     */
    int setStatusBarDefaultColor();

    /**
     * 当设置沉浸式状态栏时:标题栏颜色
     *
     * @return 标题栏颜色(return R.color.status_bar_color ;)
     */
    int setStatusBarImmersionTitleBarStyle();

    /**
     * 设置状态栏样式
     *
     * @return 文件渐变颜色样式 (return R.drawable.shape_status_bar_bg;)
     */
    int setStatusBarDefaultDrawableColor();

    /**
     * 设置状态栏文字颜色
     *
     * @return light模式：状态栏字体 true: 灰色，false: 白色 Android 6.0+
     */
    boolean setStatusBarTextColor();

    /**
     * 布局是否侵入状态栏
     *
     * @return true 不侵入，false 侵入
     */
    boolean setFitWindow();

    /**
     * 设置标题栏样式是否与状态栏样式保持一致
     *
     * @return true 一致  false 不一致
     */
    boolean setTitleStyleIsToStatusBar();

    /**
     * RecyclerView 无数据时空视图展示
     * @param layout  刷新布局
     * @param emptyView  空布局
     * @param list  列表数据
     */
    void recyclerViewEmptyView(SmartRefreshLayout layout, View emptyView, List list);

    /**
     * RecyclerView 无数据时空视图展示
     * @param rvList  刷新布局
     * @param emptyView  空布局
     * @param list  列表数据
     */
    void recyclerViewEmptyView(RecyclerView rvList, View emptyView, List list);
}
