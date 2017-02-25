package changs.android.rcc.adapter;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by yincs on 2016/5/10.
 */
class EventHandle implements RecyclerViewAdapter.EventHelper, View.OnClickListener {
    RecyclerViewAdapter.MyViewHolder viewHolder;
    RecyclerViewAdapter.OnItemClickListener itemClickListener;

    public EventHandle(RecyclerViewAdapter.MyViewHolder viewHolder) {
        this.viewHolder = viewHolder;
        this.viewHolder.dataBinding.getRoot().setOnClickListener(this);
    }

    @Override
    public RecyclerViewAdapter.EventHelper setOnChildViewClickListener(View view) {
        view.setOnClickListener(this);
        return this;
    }

    @Override
    public RecyclerViewAdapter.EventHelper setOnChildViewClickListener(@IdRes int id) {
        viewHolder.dataBinding.getRoot().findViewById(id).setOnClickListener(this);
        return this;
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener == null) return;

        int adapterPosition = viewHolder.getAdapterPosition();
        if (viewHolder.belongIRecyclerView)
            adapterPosition -= 2;

        if (adapterPosition < 0) return;

        if (v == viewHolder.dataBinding.getRoot()) {
            itemClickListener.onItemClick(adapterPosition);
        }
        itemClickListener.onItemChildViewClick(v, adapterPosition);
    }
}
