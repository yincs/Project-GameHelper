package changs.gamehelper.mobile.http;

import changs.gamehelper.mobile.domain.AppResQiuQiu;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by yincs on 2017/2/25.
 */

public interface QiuQiu {

    @FormUrlEncoded
    @POST()
    Observable<AppResQiuQiu> getLollipop(@Url String baseUrl, @Field("type") String type, @Field("url") String url);

}
