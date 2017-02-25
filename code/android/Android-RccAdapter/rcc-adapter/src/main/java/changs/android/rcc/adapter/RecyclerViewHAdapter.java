package changs.android.rcc.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yincs on 2016/5/17.
 * 可以添加头布局
 */
public abstract class RecyclerViewHAdapter<T, V extends Observable, H extends Observable> extends RecyclerViewAdapter<T, V> {
    private static final int TYPE_HEADER = 0x1A2;
    private static final int TYPE_NORMAL = 0x0;

    public H headBinding;

    public RecyclerViewHAdapter(Context ctx, int headLayoutRes, int itemLayoutRes, ViewGroup parent) {
        this(ctx, null, headLayoutRes, itemLayoutRes, parent);
    }

    public RecyclerViewHAdapter(Context ctx, List<T> data, int headLayoutRes, int itemLayoutRes, ViewGroup parent) {
        super(ctx, data, itemLayoutRes);
        headBinding = DataBindingUtil.inflate(inflater, headLayoutRes, parent, false);
    }

    @Override
    public MyViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new MyViewHolder<>(headBinding.getRoot());
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHolder<V> holder, int position) {
        if (position == 0)
            return;
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

}
