package changs.gamehelper.mobile.http;

import org.junit.Test;

import changs.gamehelper.mobile.utils.HttpUtils;

/**
 * Created by yincs on 2017/2/25.
 */
public class HttpQiuQiuTest {

    @Test
    public void queryUserId() throws Exception {
        HttpUtils.create(HttpQiuQiu.class).queryUserId(HttpQiuQiu.BASE_URL, "http://t.cn/RV7CE22")
                .subscribe();
    }

    @Test
    public void getLollipop() throws Exception {
        HttpUtils.create(HttpQiuQiu.class).getLollipop(HttpQiuQiu.BASE_URL_BBT, 331551098, 1)
                .subscribe();
    }
}