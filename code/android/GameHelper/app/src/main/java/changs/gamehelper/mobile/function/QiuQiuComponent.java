package changs.gamehelper.mobile.function;

import changs.android.frame.components.Component;
import changs.android.frame.components.FView;
import changs.android.frame.entity.action.Action1;
import changs.android.frame.entity.exception.AppException;
import changs.android.utils.EmptyUtils;
import changs.gamehelper.mobile.domain.AppResQiuQiu;
import changs.gamehelper.mobile.entity.GetLollipopResult;
import changs.gamehelper.mobile.http.HttpQiuQiu;
import changs.gamehelper.mobile.utils.HttpUtils;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by yincs on 2017/2/25.
 */

public class QiuQiuComponent extends Component {

    private AppResQiuQiu.QueryUserId queryUserId;

    public QiuQiuComponent(FView view, Action1<Object> success, Action1<Object> fail) {
        super(view, success, fail);
    }


    public void getLollipop(String url) {
        if (EmptyUtils.isEmpty(url)) {
            fail.call("url不能为空");
            return;
        }

        HttpUtils.create(HttpQiuQiu.class)
                .queryUserId(HttpQiuQiu.BASE_URL, url)
                .filter(queryUserId -> {
                    if (queryUserId.getId() == -1) {
                        throw new AppException("查询用户id失败");
                    }
                    return true;
                })
                .doOnNext(queryUserId -> this.queryUserId = queryUserId)
                .flatMap(new Function<AppResQiuQiu.QueryUserId, ObservableSource<AppResQiuQiu.GetLollipop>>() {
                    @Override
                    public ObservableSource<AppResQiuQiu.GetLollipop> apply(AppResQiuQiu.QueryUserId queryUserId) throws Exception {
                        return HttpUtils.create(HttpQiuQiu.class).getLollipop(HttpQiuQiu.BASE_URL_BBT, queryUserId.getId(), 1);
                    }
                })
                .compose(view.bindUntilDestroyHandle())
                .map(getLollipop -> new GetLollipopResult(queryUserId.getAccount(), getLollipop.isSuccess(), getLollipop.getMsg()))
                .subscribe(
                        getLollipop -> success.call(getLollipop),
                        error -> fail.call(error.getMessage())
                );
    }

}
