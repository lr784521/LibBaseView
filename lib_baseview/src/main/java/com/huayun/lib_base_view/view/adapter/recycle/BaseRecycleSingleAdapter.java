package com.huayun.lib_base_view.view.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;

/**
 * 使用时 只需要继承该类即可
 * 仅适用于多布局类型,并且非列表,数据只有一个实体
 *
 * @param <T>
 */
public abstract class BaseRecycleSingleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected T data; //实体数据

    public Context context;

    public BaseRecycleSingleAdapter(T data) {
        this.data = data;
    }

    /**
     * 获取子item
     *
     * @return
     */
    protected abstract int getLayoutId();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        ViewGroup.LayoutParams params = view.getLayoutParams();

        if (params instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams layoutParams = (FlexboxLayoutManager.LayoutParams) params;
            layoutParams.setFlexGrow(0);
            view.setLayoutParams(layoutParams);
        } else {
            if (isVertical()) {
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            } else {
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
        return new BaseViewHolder(view);
    }

    protected boolean isVertical() {
        return true;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, data, position);
    }


    /**
     * 获取当前所有数据
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * 绑定数据
     *
     * @param holder   具体的viewHolder
     * @param position 对应的索引
     */
    protected abstract void bindData(BaseViewHolder holder, T t, int position);

    /**
     * 刷新数据
     *
     * @param list 最新数据
     */
    public void refresh(T list) {
        this.data = list;
        notifyDataSetChanged();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        this.data = null;
        notifyDataSetChanged();
    }

    /**
     * 获取Context
     *
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * 获取Item数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 1;
    }


}
