package changs.gamehelper.mobile;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import changs.android.utils.KeyboardUtils;
import changs.android.utils.SizeUtils;
import changs.android.utils.TimeUtils;
import changs.android.utils.ToastUtils;
import changs.gamehelper.mobile.dao.QiuQiuUrl;
import changs.gamehelper.mobile.databinding.MainBinding;
import changs.gamehelper.mobile.function.QiuQiuPresenter;
import io.realm.Realm;

import static io.realm.Realm.getDefaultInstance;

public class MainActivity extends BaseActivityB<MainBinding> {

    private List<QiuQiuUrl> qiuQiuUrls = new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.main;
    }

    @Override
    public void afterView(Bundle savedInstanceState) {
        binding.btnGetLollipop.setOnClickListener(v -> {
            KeyboardUtils.hideSoftInput(getContext());
            if (!binding.btnGetLollipop.isEnabled()) return;
            binding.btnGetLollipop.setEnabled(false);
            binding.btnGetLollipop.setText("正在执行...");
            final String url = binding.etUrl.getText().toString();
            new QiuQiuPresenter(getContext(),
                    account -> {
                        ToastUtils.showLongToast("刷棒棒粮成功" + (account == null ? "" : ":" + account));
                        getGetLollipopOver(url, true);
                    },
                    error -> {
                        ToastUtils.showLongToast("刷棒棒粮失败:" + error);
                        getGetLollipopOver(url, false);
                    }
            ).getLollipop(url);
        });

        qiuQiuUrls.addAll(Realm.getDefaultInstance()
                .where(QiuQiuUrl.class)
                .findAll());

        binding.rccView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rccView.setAdapter(adapter);
        binding.rccView.scrollToPosition(qiuQiuUrls.size() - 1);
    }

    private void getGetLollipopOver(String url, boolean success) {
        binding.btnGetLollipop.setEnabled(true);
        binding.btnGetLollipop.setText(R.string.brush_lollipop);

        QiuQiuUrl qiuQiuUrl = new QiuQiuUrl();
        qiuQiuUrl.setUrl(url);
        qiuQiuUrl.setDate(new Date());
        qiuQiuUrl.setSuccess(success);

        getDefaultInstance()
                .executeTransactionAsync(realm -> realm.insert(qiuQiuUrl));

        qiuQiuUrls.add(qiuQiuUrl);
        adapter.notifyDataSetChanged();
        binding.rccView.scrollToPosition(qiuQiuUrls.size() - 1);
    }

    private RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        int paddingTop = SizeUtils.dp2px(3);
        int paddingRight = SizeUtils.dp2px(5);

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getContext());
            textView.setPadding(paddingRight, paddingTop, paddingRight, paddingTop);
            return new RecyclerView.ViewHolder(textView) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final QiuQiuUrl qiuQiuUrl = qiuQiuUrls.get(position);
            String result = qiuQiuUrl.getUrl() + "\n" + TimeUtils.millis2String(qiuQiuUrl.getDate().getTime()) + (qiuQiuUrl.isSuccess() ? "\t成功" : "");
            ((TextView) holder.itemView).setText(result);

            holder.itemView.setOnClickListener(v -> binding.etUrl.setText(qiuQiuUrl.getUrl()));
        }

        @Override
        public int getItemCount() {
            return qiuQiuUrls.size();
        }
    };
}
