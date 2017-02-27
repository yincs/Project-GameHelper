package changs.gamehelper.mobile.contract;

import java.util.List;

import changs.android.frame.components.FView;
import changs.gamehelper.mobile.dao.QiuQiuOpt;
import changs.gamehelper.mobile.dao.QiuQiuUrl;

/**
 * Created by yincs on 2017/2/26.
 */

public interface MainContract {
    interface View extends FView {
        void getLollipopStart();

        void getLollipopEnd();
    }

    interface Presenter {

        void getLollipop(String url);

        List<QiuQiuOpt> getHistoryOpt();

        List<QiuQiuUrl> getHistoryUrl();


    }
}
