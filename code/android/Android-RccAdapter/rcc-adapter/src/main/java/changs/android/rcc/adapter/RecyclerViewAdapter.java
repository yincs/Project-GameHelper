package changs.android.rcc.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yincs on 2016/5/10.
 */
public abstract class RecyclerViewAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder<V>> {
    public final List<T> data;
    public final Context context;
    public final LayoutInflater inflater;

    @LayoutRes
    private final int itemLayout;

    private OnItemClickListener onItemClickListener;

    private boolean belongIRecyclerView;

    public RecyclerViewAdapter(Context ctx) {
        this(ctx, null);
    }

    public RecyclerViewAdapter(Context ctx, List<T> data) {
        this(ctx, data, 0);
    }

    public RecyclerViewAdapter(Context ctx, @LayoutRes int itemLayout) {
        this(ctx, null, itemLayout);
    }

    public RecyclerViewAdapter(Context ctx, List<T> data, @LayoutRes int itemLayout) {
        this.context = ctx;
        this.data = data == null ? new ArrayList<T>() : data;
        this.inflater = LayoutInflater.from(ctx);
        this.itemLayout = itemLayout;
    }

    protected V onBindingCreate(ViewGroup parent, int viewType) {
        return DataBindingUtil.inflate(inflater, itemLayout, parent, false);
    }

    protected void onBindingEvent(V dataBinding, EventHelper helper) {

    }

    protected abstract void onBindingData(V dataBinding, int position);

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public MyViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        final V bindingCreate = onBindingCreate(parent, viewType);
        final MyViewHolder<V> holder = new MyViewHolder<>(bindingCreate, belongIRecyclerView);
        holder.eventHandle.itemClickListener = onItemClickListener;
        onBindingEvent(bindingCreate, holder.eventHandle);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder<V> holder, int position) {
        onBindingData(holder.dataBinding, position);

        holder.dataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public T getItem(int position) {
        return data.get(position);
    }

    public Context getContext() {
        return context;
    }

    public void belongIRecyclerView() {
        belongIRecyclerView = true;
    }

    static class MyViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
        T dataBinding;
        EventHandle eventHandle;
        boolean belongIRecyclerView;


        public MyViewHolder(T dataBinding, boolean belongIRecyclerView) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
            this.eventHandle = new EventHandle(this);
            this.belongIRecyclerView = belongIRecyclerView;
        }

        /**
         * 添加header or footer
         *
         * @param itemView
         */
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface EventHelper {
        EventHelper setOnChildViewClickListener(View view);

        EventHelper setOnChildViewClickListener(@IdRes int id);
    }

    public static abstract class OnItemClickListener {
        public abstract void onItemClick(int position);

        public void onItemChildViewClick(View childView, int position) {
        }

    }
}
