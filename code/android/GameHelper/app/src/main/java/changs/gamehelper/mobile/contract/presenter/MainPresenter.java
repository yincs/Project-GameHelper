package changs.gamehelper.mobile.contract.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import changs.android.utils.ToastUtils;
import changs.gamehelper.mobile.contract.MainContract;
import changs.gamehelper.mobile.dao.QiuQiuOpt;
import changs.gamehelper.mobile.dao.QiuQiuUrl;
import changs.gamehelper.mobile.entity.GetLollipopResult;
import changs.gamehelper.mobile.function.QiuQiuComponent;
import io.realm.Realm;

import static io.realm.Realm.getDefaultInstance;

/**
 * Created by yincs on 2017/2/26.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    private List<QiuQiuOpt> qiuQiuOpts;
    private List<QiuQiuUrl> qiuQiuUrls;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }


    @Override
    public void getLollipop(String url) {
        view.getLollipopStart();

        new QiuQiuComponent(view,
                result -> {
                    GetLollipopResult getLollipop = (GetLollipopResult) result;
                    ToastUtils.showLongToast(getLollipop.getAccount() + ":" + getLollipop.getResult());

                    QiuQiuUrl qiuQiuUrl = new QiuQiuUrl(url, getLollipop.getAccount());
                    if (!getHistoryUrl().contains(qiuQiuUrl)) {
                        getHistoryUrl().add(qiuQiuUrl);
                        getDefaultInstance().executeTransactionAsync(realm -> realm.insertOrUpdate(qiuQiuUrl));
                    }
                    getGetLollipopOver(url, getLollipop.isSuccess());
                },
                error -> {
                    ToastUtils.showLongToast("刷棒棒粮失败:" + error);
                    getGetLollipopOver(url, false);
                }
        ).getLollipop(url);
    }

    @Override
    public List<QiuQiuOpt> getHistoryOpt() {
        if (qiuQiuOpts == null) {
            qiuQiuOpts = new ArrayList<>();
            qiuQiuOpts.addAll(Realm.getDefaultInstance()
                    .where(QiuQiuOpt.class)
                    .findAll());
        }
        return qiuQiuOpts;
    }

    @Override
    public List<QiuQiuUrl> getHistoryUrl() {
        if (qiuQiuUrls == null) {
            qiuQiuUrls = new ArrayList<>();
            qiuQiuUrls.addAll(Realm.getDefaultInstance()
                    .where(QiuQiuUrl.class)
                    .findAll());
        }
        return qiuQiuUrls;
    }

    private void getGetLollipopOver(String url, boolean success) {
        QiuQiuOpt qiuQiuOpt = new QiuQiuOpt();
        qiuQiuOpt.setUrl(url);
        qiuQiuOpt.setDate(new Date());
        qiuQiuOpt.setSuccess(success);
        getDefaultInstance().executeTransactionAsync(realm -> realm.insert(qiuQiuOpt));
        getHistoryOpt().add(qiuQiuOpt);

        view.getLollipopEnd();
    }

}
