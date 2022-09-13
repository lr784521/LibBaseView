package com.huayun.lib_base_view.view.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.huayun.lib_base_view.call.listener.OnItemClickListener;

import java.util.List;

/**
 * 使用时 只需要继承该类即可
 * 仅适用于 列表类型
 *
 * @param <T>
 */
public abstract class BaseRecycleListAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> dataList; //列表数据

    public Context context;
    protected OnItemClickListener onItemClickListener; //Item点击回调

    public BaseRecycleListAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    /**
     * 获取子item
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        //初始化加载xml布局
        View view = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        //设置布局宽高占位
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

    //是否是垂直
    protected boolean isVertical() {
        return true;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (dataList != null && dataList.size() > 0) {
            //绑定Item数据
            bindData(holder, dataList.get(position), position);
        }
    }


    /**
     * 获取当前所有数据
     *
     * @return
     */
    public List<T> getDataList() {
        return dataList;
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
    public void refresh(List<T> list) {
        if (list != null && list.size() > 0) {
            this.dataList.clear();
            this.dataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除指定下标Item
     *
     * @param pos
     */
    public void removeData(int pos) {
        this.dataList.remove(pos);
        this.notifyItemRemoved(pos);
    }


    /**
     * 删除某个Item
     *
     * @param datas
     */
    public void removeData(T datas) {
        this.dataList.remove(datas);
        notifyDataSetChanged();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        this.dataList.clear();
        notifyDataSetChanged();
    }


    /**
     * 添加列表数据到尾部
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        this.dataList.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 添加列表数据 到指定下标
     *
     * @param pos   下标
     * @param datas 列表数据
     */
    public void addData(int pos, List<T> datas) {
        this.dataList.addAll(pos, datas);
        notifyDataSetChanged();
    }

    /**
     * 添加Item到指定位置
     *
     * @param pos   位置
     * @param datas 数据
     */
    public void addData(int pos, T datas) {
        this.dataList.add(pos, datas);
        notifyDataSetChanged();
    }

    /**
     * 给尾部添加数据
     *
     * @param datas 数据
     */
    public void addData(T datas) {
        this.dataList.add(datas);
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
        return dataList == null ? 0 : dataList.size();
    }

    /**
     * 移除某个Item 添加到顶部
     *
     * @param pos
     */
    public void swipeToFirst(int pos) {
        T t = dataList.get(pos);
        dataList.remove(pos);
        dataList.add(0, t);
        notifyDataSetChanged();
    }

    /**
     * Item点击事件
     *
     * @param clickListener
     */
    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }
}
