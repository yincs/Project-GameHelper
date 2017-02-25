package changs.gamehelper.mobile.function;

import android.net.Uri;

import changs.android.frame.components.Component2;
import changs.android.frame.components.FView;
import changs.android.frame.entity.action.Action1;
import changs.android.frame.entity.exception.AppException;
import changs.android.utils.EmptyUtils;
import changs.gamehelper.mobile.http.QiuQiu;
import changs.gamehelper.mobile.utils.HttpUtils;

/**
 * Created by yincs on 2017/2/25.
 */

public class QiuQiuPresenter extends Component2<Action1<Object>, Action1<Object>> {


    public QiuQiuPresenter(FView view, Action1<Object> objectAction1, Action1<Object> objectAction12) {
        super(view, objectAction1, objectAction12);
    }


    public void getLollipop(String url) {
        if (EmptyUtils.isEmpty(url)) {
            fail.call("url不能为空");
            return;
        }

        HttpUtils.create(QiuQiu.class)
                .getLollipop("http://xzfuli.cn/index.php?a=api_qiuqiu", "4", url)
                .compose(view.bindUntilDestroyHandle())

                .subscribe(appResQiuQiu -> {
                    if (appResQiuQiu.getCode() != 0)
                        throw new AppException(appResQiuQiu.getMsg());
                    success.call(getUrlAccount(appResQiuQiu.getUrl()));
                }, error -> {
                    fail.call(error.getMessage());
                });

    }

    private String getUrlAccount(String url) {
        if (EmptyUtils.isEmpty(url)) {
            return null;
        }
        return Uri.parse(url).getQueryParameter("Account");
    }
}
