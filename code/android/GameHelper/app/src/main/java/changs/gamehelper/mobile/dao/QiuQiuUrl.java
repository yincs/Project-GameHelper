package changs.gamehelper.mobile.dao;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by yincs on 2017/2/25.
 */

public class QiuQiuUrl extends RealmObject {

    private int id;

    private String url;

    private Date date;

    private boolean success;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
