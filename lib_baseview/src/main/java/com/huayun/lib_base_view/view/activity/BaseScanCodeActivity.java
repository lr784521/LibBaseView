package com.huayun.lib_base_view.view.activity;

import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.SurfaceHolder;

import com.google.zxing.Result;
import com.huayun.lib_tools.util.scan_code.android.BeepManager;
import com.huayun.lib_tools.util.scan_code.android.InactivityTimer;
import com.huayun.lib_tools.util.scan_code.android.MyScanCodeActivityHandler;
import com.huayun.lib_tools.util.scan_code.bean.ZxingConfig;
import com.huayun.lib_tools.util.scan_code.camera.CameraManager;
import com.huayun.lib_tools.util.scan_code.listener.WidScanCodeListener;

import java.io.IOException;

/**
 * 扫码Base基类
 * @param <P>
 */
public abstract class BaseScanCodeActivity<P extends BaseActivityPresenter> extends BaseActivityMvp<P> implements
        SurfaceHolder.Callback, WidScanCodeListener {

    public ZxingConfig config;//zxing配置
    public CameraManager cameraManager;//相机管理
    public MyScanCodeActivityHandler handler;//扫码通知Handler
    protected boolean hasSurface;//是否已初始化SurfaceView
    protected SurfaceHolder surfaceHolder;
    protected BeepManager beepManager;
    protected InactivityTimer inactivityTimer;

    /**
     * 初始化相机
     */
    @Override
    public void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            return;
        }
        try {
            // 打开Camera硬件设备
            cameraManager.openDriver(surfaceHolder);
            // 创建一个handler来打开预览，并抛出一个运行时异常
            if (handler == null) {
                handler = new MyScanCodeActivityHandler(this, cameraManager);
            }
        } catch (IOException ioe) {
            initCameraError(ioe.getMessage());
        } catch (RuntimeException e) {
            initCameraError(e.getMessage());
        }
    }


    /**
     * @param rawResult 返回的扫描结果
     */
    @Override
    public void handleDecode(Result rawResult) {
        inactivityTimer.onActivity();
        beepManager.playBeepSoundAndVibrate();
        scanCodeSuccess(rawResult);
    }

    /**
     * @param flashState 切换闪光灯图片
     */
    @Override
    public void switchFlashImg(int flashState) {
    }

    /**
     * 获取相机管理
     *
     * @return
     */
    @Override
    public CameraManager getCameraManager() {
        return cameraManager;
    }

    /**
     * 获取Handler
     *
     * @return
     */
    @Override
    public Handler getHandler() {
        return handler;
    }

    /**
     * @param pm
     * @return 是否有闪光灯
     */
    @Override
    public boolean isSupportCameraLedFlash(PackageManager pm) {
        if (pm != null) {
            FeatureInfo[] features = pm.getSystemAvailableFeatures();
            if (features != null) {
                for (FeatureInfo f : features) {
                    if (f != null && PackageManager.FEATURE_CAMERA_FLASH.equals(f.name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void scanSetResult(int resultCode, Intent data) {
        setResult(resultCode, data);
    }

    @Override
    public void scanFinish() {
        finish();
    }

    @Override
    public ZxingConfig getZxingConfig() {
        return config;
    }
}
