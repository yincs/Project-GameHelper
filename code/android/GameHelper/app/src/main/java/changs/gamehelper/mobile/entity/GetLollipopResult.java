package changs.gamehelper.mobile.entity;

/**
 * Created by yincs on 2017/2/27.
 */

public class GetLollipopResult {
    private String account;
    private boolean isSuccess;
    private String result;

    public GetLollipopResult(String account, boolean isSuccess, String result) {
        this.account = account;
        this.isSuccess = isSuccess;
        this.result = result;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
