package changs.android.rcc.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lenovo on 2016/8/14.
 */
public class SelectAdapter<T> extends RecyclerView.Adapter<SelectAdapter.MyViewHolder> {

    public final List<T> data;
    private final LayoutInflater inflater;
    private int selectPosition;

    private OnItemClickListener listener;

    public SelectAdapter(Context ctx, List<T> data) {
        this.data = data;
        inflater = LayoutInflater.from(ctx);
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public T getSelectData() {
        if (selectPosition < 0
                || selectPosition >= data.size())
            return null;
        return data.get(selectPosition);
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_text, parent, false), selectListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setSelected(position == selectPosition);
        bindView(holder, position);
    }

    protected void bindView(MyViewHolder holder, int position) {
        holder.tv.setText(data.get(position).toString());
    }

    public void resetData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        this.selectPosition = -1;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private OnItemClickListener selectListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if (position != selectPosition) {
                int lastSelect = selectPosition;
                selectPosition = position;
                notifyItemChanged(lastSelect);
                notifyItemChanged(selectPosition);
            }
            if (listener != null)
                listener.onItemClick(position);
        }
    };

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener listener;
        private TextView tv;

        public MyViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);

            tv = (TextView) itemView.findViewById(R.id.tv_content);
        }

        @Override
        public void onClick(View v) {
            final int adapterPosition = getAdapterPosition();
            if (adapterPosition == -1) return;
            listener.onItemClick(adapterPosition);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static <T> SelectAdapter<T> setAdapter(RecyclerView rccView, SelectAdapter<T> adapter) {
        rccView.setLayoutManager(new LinearLayoutManager(rccView.getContext()));
        rccView.setAdapter(adapter);
        rccView.setHasFixedSize(true);
        return adapter;
    }
}
