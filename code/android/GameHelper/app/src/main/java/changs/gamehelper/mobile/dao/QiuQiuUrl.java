package changs.gamehelper.mobile.dao;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yincs on 2017/2/25.
 */

public class QiuQiuUrl extends RealmObject {

    @PrimaryKey
    private String url;

    private String account;


    public QiuQiuUrl() {
    }

    public QiuQiuUrl(String url, String account) {
        this.url = url;
        this.account = account;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof QiuQiuUrl && ((QiuQiuUrl) obj).url.equals(url);
    }
}
