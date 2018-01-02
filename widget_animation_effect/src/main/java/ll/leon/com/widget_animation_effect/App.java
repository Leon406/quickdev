package ll.leon.com.widget_animation_effect;

import android.app.Application;

import com.kingja.loadsir.core.LoadSir;

import ll.leon.com.widget_animation_effect.effect.loading.EmptyCallback;
import ll.leon.com.widget_animation_effect.effect.loading.ErrorCallback;
import ll.leon.com.widget_animation_effect.effect.loading.LoadingCallback;
import ll.leon.com.widget_animation_effect.effect.loading.TimeoutCallback;

/**
 * Created by Leon on 2018/1/2 0002.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//'添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())

                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();

    }
}
