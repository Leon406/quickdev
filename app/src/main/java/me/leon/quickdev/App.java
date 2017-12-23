package me.leon.quickdev;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bilibili.socialize.share.core.BiliShare;
import com.bilibili.socialize.share.core.BiliShareConfiguration;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cn.jpush.android.api.JPushInterface;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.leon.devsuit.android.CrashUtils;
import me.leon.devsuit.android.Utils;
import me.leon.libs.engine.http.LoggerInterceptor;
import me.leon.libs.engine.image.GlideImageDownloader;
import okhttp3.OkHttpClient;
//import me.leon.libs.utils.T;

/**
 * Author : Leon
 * E-mail : deadogone@gmail.com
 * Time   : 2017/12/20 0020 23:57
 * Desc   : This is App
 * Version: 1.0.1
 */

public class App extends Application {

    public static final String TAG = "AppDebug";
    public static Realm realm;


    @Override
    public void onCreate() {
        super.onCreate();

        if (!initTool()) {
            return;
        }
        Log.d(TAG, "App Create");
        init3rdSdk();

//        int s = 5 / 0;
        bindActivityLife();
    }

    /**
     * 工具类初始化
     *
     * @return boolean
     */
    private boolean initTool() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return false;
        }

        LeakCanary.install(this);
        Utils.init(this);
//        T.getInstance().init(this);
        CrashUtils.init();
        BiliShare.global()
                .config(new BiliShareConfiguration.Builder(this)
                        .qq("1106059675")
                        .weixin("wx311952cca4d43c2d")
                        .imageDownloader(new GlideImageDownloader())

                        .build()
                );
        initOkGo();


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("my.realm") //文件名
                .schemaVersion(1) //版本号
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);

        return true;
    }


    private void initOkGo() {

        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        //header不支持中文，不允许有特殊字符
        HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");
        headers.put("commonHeaderKey2", "commonHeaderValue2");
        //param支持中文,直接传,不要自己编码
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");
        params.put("commonParamsKey2", "这里支持中文参数");
        //----------------------------------------------------------------------------------------//
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .cookieJar(new CookieJarImpl(new DBCookieStore(this)))
                .addInterceptor(new LoggerInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();

        OkGo.getInstance()
                .init(this)
                .setOkHttpClient(okHttpClient)
//                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
//                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(2)
//                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params)
        ;                                                 //全局公共参数;
    }

    /**
     * 三方SDK 初始化
     */
    private void init3rdSdk() {
        // U-APP 初始化
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setDebugMode(BuildConfig.DEBUG);
        //极光推送
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
    }

    /**
     * 回调每个activity的生命周期
     */
    private void bindActivityLife() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                Log.d(TAG, activity.getClass().getSimpleName() + "   onActivityCreated");
                //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里

            }

            @Override
            public void onActivityStarted(Activity activity) {

                Log.d(TAG, activity.getClass().getSimpleName() + "   onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (activity.findViewById(R.id.title_bar) != null) {
                    ((TextView) activity.findViewById(R.id.title)).setText(activity.getTitle());
                }
                if (activity.findViewById(R.id.back) != null) {
                    activity.findViewById(R.id.back).setOnClickListener(v -> {
                        activity.onBackPressed();
                    });
                }
                Log.d(TAG, activity.getClass().getSimpleName() + "   onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "   onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "   onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d(TAG, activity.getClass().getSimpleName() + "   onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {

                Log.d(TAG, activity.getClass().getSimpleName() + "   onActivityDestroyed");
            }
        });
    }
}
