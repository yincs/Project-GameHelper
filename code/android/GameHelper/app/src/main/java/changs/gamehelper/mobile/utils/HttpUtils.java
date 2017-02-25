package changs.gamehelper.mobile.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import changs.android.utils.MyLog;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yincs on 2017/2/25.
 */

public class HttpUtils {
    private static final String TAG = "HTTP";

    private static final OkHttpClient OK_HTTP_CLIENT;
    private static final Retrofit RETROFIT;

    static {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                MyLog.h(TAG, message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        RETROFIT = new Retrofit.Builder()
                .baseUrl("http://aaaa")
                .client(OK_HTTP_CLIENT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static <T> T create(Class<T> service) {
        return RETROFIT.create(service);
    }
}
