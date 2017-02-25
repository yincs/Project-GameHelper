package changs.gamehelper.mobile.http;

import org.junit.Test;

import changs.gamehelper.mobile.utils.HttpUtils;

/**
 * Created by yincs on 2017/2/25.
 */
public class QiuQiuTest {

    @Test
    public void test() throws Exception {

    }

    @Test
    public void getLollipop() throws Exception {
        HttpUtils.create(QiuQiu.class).getLollipop("http://xzfuli.cn/index.php?a=api_qiuqiu", "4", "http://t.cn/RVMlBJQ")
                .subscribe();
    }
}