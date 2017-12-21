package me.leon.quickdev;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import me.leon.devsuit.android.Utils;
import me.leon.libs.utils.CrashHandler;
import me.leon.libs.utils.T;

/**
 * Author : Leon
 * E-mail : deadogone@gmail.com
 * Time   : 2017/12/20 0020 23:57
 * Desc   : This is App
 * Version: 1.0.1
 */

public class App extends Application {

    public static final String TAG = "AppDebug";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "App Create");
        CrashHandler.getInstance().init(this);
//        Utils.init(this);
        T.getInstance().init(this);



        bindActivityLife();
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
