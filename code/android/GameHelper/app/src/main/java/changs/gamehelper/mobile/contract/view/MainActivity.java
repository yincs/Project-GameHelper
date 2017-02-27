package changs.gamehelper.mobile.contract.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import changs.android.utils.KeyboardUtils;
import changs.android.utils.SizeUtils;
import changs.android.utils.TimeUtils;
import changs.gamehelper.mobile.BaseActivityB;
import changs.gamehelper.mobile.R;
import changs.gamehelper.mobile.contract.MainContract;
import changs.gamehelper.mobile.contract.presenter.MainPresenter;
import changs.gamehelper.mobile.dao.QiuQiuOpt;
import changs.gamehelper.mobile.dao.QiuQiuUrl;
import changs.gamehelper.mobile.databinding.MainBinding;

public class MainActivity extends BaseActivityB<MainBinding> implements MainContract.View {


    private MainContract.Presenter presenter = new MainPresenter(this);

    @Override
    public int getLayoutRes() {
        return R.layout.main;
    }

    @Override
    public void afterView(Bundle savedInstanceState) {
        binding.btnGetLollipop.setOnClickListener(v -> {
            KeyboardUtils.hideSoftInput(getContext());
            if (!binding.btnGetLollipop.isEnabled()) return;
            presenter.getLollipop(binding.etUrl.getText().toString());
        });


        binding.rccView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rccView.setAdapter(adapter);

        binding.rccViewUrl.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rccViewUrl.setAdapter(adapter2);
    }


    private RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        int paddingTop = SizeUtils.dp2px(1);

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getContext());
            textView.setPadding(0, paddingTop, 0, paddingTop);
            return new RecyclerView.ViewHolder(textView) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final QiuQiuOpt qiuQiuOpt = presenter.getHistoryOpt().get(position);
            String result = qiuQiuOpt.getUrl() + "\n" + TimeUtils.millis2String(qiuQiuOpt.getDate().getTime()) + (qiuQiuOpt.isSuccess() ? "\t成功" : "");
            ((TextView) holder.itemView).setText(result);

            holder.itemView.setOnClickListener(v -> binding.etUrl.setText(qiuQiuOpt.getUrl()));
        }

        @Override
        public int getItemCount() {
            return presenter.getHistoryOpt().size();
        }
    };

    private RecyclerView.Adapter adapter2 = new RecyclerView.Adapter() {
        int paddingTop = SizeUtils.dp2px(1);

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getContext());
            textView.setPadding(0, paddingTop, 0, paddingTop);
            return new RecyclerView.ViewHolder(textView) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final QiuQiuUrl qiuQiuUrl = presenter.getHistoryUrl().get(position);
            String result = "账户:" + qiuQiuUrl.getAccount() + "\n" + "链接:" + qiuQiuUrl.getUrl();
            ((TextView) holder.itemView).setText(result);

            holder.itemView.setOnClickListener(v -> binding.etUrl.setText(qiuQiuUrl.getUrl()));
        }

        @Override
        public int getItemCount() {
            return presenter.getHistoryUrl().size();
        }
    };

    @Override
    public void getLollipopStart() {
        binding.btnGetLollipop.setEnabled(false);
        binding.btnGetLollipop.setText("正在执行...");
    }

    @Override
    public void getLollipopEnd() {
        binding.btnGetLollipop.setEnabled(true);
        binding.btnGetLollipop.setText(R.string.brush_lollipop);
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

}
