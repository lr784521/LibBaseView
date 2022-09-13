package com.huayun.lib_base_view.view.adapter.recycle;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * 处理BaseRecycleAdapter Item加载
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViewMap;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        mViewMap = new SparseArray<>();
    }

    public View getItemView() {
        return itemView;
    }

    /**
     * 获取设置的view
     *
     * @param id
     * @return
     */
    public <T extends View> T getView(int id) {
        View view = mViewMap.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViewMap.put(id, view);
        }
        return (T) view;
    }

    public BaseViewHolder setOnClickListener(int view_id, View.OnClickListener listener) {
        View view = getView(view_id);
        view.setOnClickListener(listener);
        return this;
    }

    public TextView getTextView(int viewId) {
        return getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return getView(viewId);
    }

    public BaseViewHolder setText(int viewId, CharSequence text) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (TextUtils.isEmpty(text)) {
                textView.setText("");
            } else {
                textView.setText(text);
            }
        }
        return this;
    }

    public BaseViewHolder setText(int viewId, int textResId) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setText(textResId);
        }
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int imageResId) {
        View view = getView(viewId);
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setImageResource(imageResId);
        }
        return this;
    }

}
