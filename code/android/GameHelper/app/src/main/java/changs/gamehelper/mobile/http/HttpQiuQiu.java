package changs.gamehelper.mobile.http;

import changs.gamehelper.mobile.domain.AppResQiuQiu;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by yincs on 2017/2/25.
 */

public interface HttpQiuQiu {
    String BASE_URL = "http://api.shuaqiuqiu.com/bob_api.php?act=expand";
    String BASE_URL_BBT = "http://api.shuaqiuqiu.com/bob_api.php?act=bbt";

    @FormUrlEncoded
    @POST()
    Observable<AppResQiuQiu.QueryUserId> queryUserId(@Url String baseUrl, @Field("url_short") String url);


    @Multipart
    @POST()
    Observable<AppResQiuQiu.GetLollipop> getLollipop(@Url String baseUrl, @Part("id") int id, @Part("count") int count);


}
