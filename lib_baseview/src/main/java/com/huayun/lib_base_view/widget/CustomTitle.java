package com.huayun.lib_base_view.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huayun.lib_base_view.R;
import com.huayun.lib_tools.util.SoftKeyboardUtil;


/**
 * 标题控件
 */
public class CustomTitle extends RelativeLayout {

    private String titleText;
    private boolean canBack;
    private String backText = "";
    private String moreText = "";
    private int moreImg;
    private TextView tvMore;
    private RelativeLayout llTitleLayout;
    private Context mContext;
    private TextView tvTitle;
    private ImageView imgBack;

    public CustomTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater.from(context).inflate(R.layout.custom_title, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTitle, 0, 0);
        try {
            titleText = ta.getString(R.styleable.CustomTitle_titleText);
            canBack = ta.getBoolean(R.styleable.CustomTitle_canBack, false);
            backText = ta.getString(R.styleable.CustomTitle_backText);
            moreImg = ta.getResourceId(R.styleable.CustomTitle_moreImg, 0);
            moreText = ta.getString(R.styleable.CustomTitle_moreText);
            setUpView();
        } finally {
            ta.recycle();
        }
    }

    private void setUpView() {
        llTitleLayout = findViewById(R.id.ll_title_layout);
        tvTitle = findViewById(R.id.tv_title);
        imgBack = findViewById(R.id.img_back);
        tvTitle.setText(titleText);
        LinearLayout backBtn = findViewById(R.id.title_back);
        backBtn.setVisibility(canBack ? VISIBLE : INVISIBLE);
        TextView tvBack = findViewById(R.id.txt_back);
        if (canBack) {
            if (backText != null && !backText.equals("")) {
                tvBack.setVisibility(VISIBLE);
                tvBack.setText(backText);
            }
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SoftKeyboardUtil.Closekeyboard((Activity) getContext());
                    ((Activity) getContext()).finish();
                }
            });
        }
        ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
        if (moreImg != 0) {
            moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
        }
        tvMore = (TextView) findViewById(R.id.txt_more);
        if (moreText != null && !moreText.equals("")) {
            tvMore.setVisibility(VISIBLE);
            tvMore.setText(moreText);
        }
    }


    /**
     * 标题控件
     *
     * @param titleText 设置标题文案
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
        tvTitle.setText(titleText);
    }

    /**
     * 标题控件
     *
     * @param titleText 设置标题文案
     */
    public void setTitleText(int titleText) {
        this.titleText = mContext.getString(titleText);
        tvTitle.setText(this.titleText);
    }


    public void setTitleTextColor(int color) {
        tvTitle.setTextColor(mContext.getColor(color));
    }

    public void setBackImgColor(int color) {
        imgBack.setImageTintList(ColorStateList.valueOf(color));

//        Drawable tipsArrow = imgBack.getDrawable();
//        tipsArrow.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
//        imgBack.setImageDrawable(tipsArrow);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    /**
     * 获取返回按钮
     * @return
     */
    public ImageView getImgBack(){
        return imgBack;
    }

    /**
     * 标题更多按钮
     *
     * @param img 设置更多按钮
     */
    public void setMoreImg(int img) {
        moreImg = img;
        ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
        if (moreImg != 0) {
            moreImgView.setVisibility(View.VISIBLE);
            moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
        } else {
            moreImgView.setVisibility(View.GONE);
        }
    }


    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreImgAction(View.OnClickListener listener) {
        ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
        moreImgView.setOnClickListener(listener);
    }


    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreTextAction(View.OnClickListener listener) {
        tvMore.setOnClickListener(listener);
    }


    /**
     * 设置更多文字内容
     *
     * @param text 更多文本
     */
    public void setMoreTextContext(String text) {
        tvMore.setVisibility(VISIBLE);
        tvMore.setText(text);
    }

    public void setMoreTextColor(int color) {
        tvMore.setTextColor(color);
    }

    /**
     * 设置返回按钮事件
     *
     * @param listener 事件监听
     */
    public void setBackListener(View.OnClickListener listener) {
        if (canBack) {
            LinearLayout backBtn = (LinearLayout) findViewById(R.id.title_back);
            backBtn.setOnClickListener(listener);
        }
    }

    /**
     * 设置标题栏背景
     *
     * @param color
     */
    public void setTitleBg(int color) {
        llTitleLayout.setBackgroundResource(color);
    }

    /**
     * 当设置透明+沉浸式状态栏 是添加标题栏高度
     */
    public void setTitlePadding() {
        llTitleLayout.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    /**
     * 用于获取状态栏的高度。 使用Resource对象获取（推荐这种方式）
     *
     * @return 返回状态栏高度的像素值。
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

