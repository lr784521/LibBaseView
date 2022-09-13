package com.huayun.lib_base_view.call.mvp.activity;

import com.huayun.lib_base_view.call.listener.ActivityFinishDelayListener;
import com.huayun.lib_base_view.call.mvp.IBaseViewBusinessInterface;

/**
 * Activity基础业务扩展
 */
public interface IBaseActivityBusinessInterface extends IBaseViewBusinessInterface {

    /**
     * 是否开启再按一次退出
     *
     * @return true 开启  false 不开启
     */
    boolean isDoubleClickExit();

    void finishDelay();

    void finishDelay(ActivityFinishDelayListener listener);

    void finishDelay(int time, ActivityFinishDelayListener listener);

}
