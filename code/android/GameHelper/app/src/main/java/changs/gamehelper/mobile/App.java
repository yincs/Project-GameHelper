package changs.gamehelper.mobile;

import android.app.Application;
import android.util.Log;

import changs.android.frame.app.AppClient;
import changs.android.utils.Utils;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yincs on 2017/2/25.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new AppClient.Config(this)
                .debug()
                .logLevel(Log.INFO)
                .setup();
        Utils.init(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
