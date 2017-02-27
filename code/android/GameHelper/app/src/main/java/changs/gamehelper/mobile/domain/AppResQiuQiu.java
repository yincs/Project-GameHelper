package changs.gamehelper.mobile.domain;

import android.net.Uri;

import java.util.List;

import changs.android.utils.EmptyUtils;

/**
 * Created by yincs on 2017/2/25.
 */

public class AppResQiuQiu {

    /**
     * code : 0
     * msg : 成功
     * url : http://www.battleofballs.com/?id=365049418&Account=kelly_lily
     */

    private int code;
    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static class QueryUserId extends AppResQiuQiu {
        private String url_long;

        public String getUrl() {
            return url_long;
        }

        public void setUrl(String url) {
            this.url_long = url;
        }

        public String getAccount() {
            if (EmptyUtils.isEmpty(url_long)) return null;
            return Uri.parse(url_long).getQueryParameter("Account");
        }

        public int getId() {
            if (EmptyUtils.isEmpty(url_long)) return -1;
            return Integer.parseInt(Uri.parse(url_long).getQueryParameter("id"));
        }
    }

    public static class GetLollipop extends AppResQiuQiu {

        /**
         * id : 331551098
         * account : null
         * server_0 : ok
         * okn : 5
         * isok : 5
         * iserror : 0
         * server_1 : ok
         * server_2 : ok
         * server_3 : ok
         * server_4 : ok
         * code : 0
         * msg : 您的棒棒糖刷取成功！
         * time : [0.009656,0.009166,0.012541,0.013711,0.009066]
         */

        private String id;
        private Object account;
        private String server_0;
        private int okn;
        private int isok;
        private int iserror;
        private String server_1;
        private String server_2;
        private String server_3;
        private String server_4;
        private List<Double> time;

        public boolean isSuccess() {
            return serverSuccess(server_0)
                    && serverSuccess(server_1)
                    && serverSuccess(server_2)
                    && serverSuccess(server_3)
                    && serverSuccess(server_4)
                    ;
        }

        private boolean serverSuccess(String serverResult) {
            return serverResult != null && serverResult.equals("ok");

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getAccount() {
            return account;
        }

        public void setAccount(Object account) {
            this.account = account;
        }

        public String getServer_0() {
            return server_0;
        }

        public void setServer_0(String server_0) {
            this.server_0 = server_0;
        }

        public int getOkn() {
            return okn;
        }

        public void setOkn(int okn) {
            this.okn = okn;
        }

        public int getIsok() {
            return isok;
        }

        public void setIsok(int isok) {
            this.isok = isok;
        }

        public int getIserror() {
            return iserror;
        }

        public void setIserror(int iserror) {
            this.iserror = iserror;
        }

        public String getServer_1() {
            return server_1;
        }

        public void setServer_1(String server_1) {
            this.server_1 = server_1;
        }

        public String getServer_2() {
            return server_2;
        }

        public void setServer_2(String server_2) {
            this.server_2 = server_2;
        }

        public String getServer_3() {
            return server_3;
        }

        public void setServer_3(String server_3) {
            this.server_3 = server_3;
        }

        public String getServer_4() {
            return server_4;
        }

        public void setServer_4(String server_4) {
            this.server_4 = server_4;
        }

        public List<Double> getTime() {
            return time;
        }

        public void setTime(List<Double> time) {
            this.time = time;
        }
    }
}
