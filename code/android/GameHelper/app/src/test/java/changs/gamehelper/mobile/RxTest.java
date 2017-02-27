package changs.gamehelper.mobile;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by yincs on 2017/2/26.
 */

public class RxTest {

    @Test
    public void test1() throws Exception {

        Observable
                .just("张")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return false;
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(" ===== ");
                        throw new RuntimeException("我的");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("RxTest.doFinally");
                    }
                })
                .subscribe(new Consumer<String>() {
                               @Override
                               public void accept(String s) throws Exception {
                                   System.out.println("RxTest.consumer" + s);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                System.out.println("RxTest.throwable ");
                            }
                        });

    }
}
