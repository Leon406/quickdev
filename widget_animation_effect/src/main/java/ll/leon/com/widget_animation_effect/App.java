package ll.leon.com.widget_animation_effect;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.kingja.loadsir.core.LoadSir;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ll.leon.com.widget_animation_effect.dbtest.realm.RealmDao;
import ll.leon.com.widget_animation_effect.effect.loading.EmptyCallback;
import ll.leon.com.widget_animation_effect.effect.loading.ErrorCallback;
import ll.leon.com.widget_animation_effect.effect.loading.LoadingCallback;
import ll.leon.com.widget_animation_effect.effect.loading.TimeoutCallback;
import me.leon.libs.utils.T;

/**
 * Created by Leon on 2018/1/2 0002.
 */

public class App extends Application {
    public static Context ctx;
    public static Realm realm;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ctx =this;
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//'添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())

                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();

        T.getInstance().init(this);

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("test.mRealm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();


        realm = Realm.getInstance(config);
        RealmDao.setmRealm(realm);
    }
}
