package me.leon.libs.utils;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author PC
 * @date 2017/12/21
 */

public class RxUtils {
    private RxUtils() {
    }

    /**
     * @param <T>
     * @return
     * @Desc 线程切换 使用io密集型操作， 例如网络请求,数据库操作
     */
    public static <T> FlowableTransformer<T, T> rxSwitch() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * @param <T>
     * @return
     * @Desc 线程切换 使用io密集型操作， 例如网络请求,数据库操作
     */
    public static <T> ObservableTransformer<T, T> observeThreadSwitch() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static <T> ObservableTransformer<T, T> observeLifecycle(LifecycleProvider provider) {


        return observable -> {
            if (provider instanceof RxFragment) {
                return observable.compose(((RxFragment) provider).bindToLifecycle());
            } else if (provider instanceof RxAppCompatActivity) {
                return observable.compose(((RxAppCompatActivity) provider).bindToLifecycle());
            } else if (provider instanceof RxAppCompatDialogFragment) {
                return observable.compose(((RxAppCompatDialogFragment) provider).bindToLifecycle());
            } else {
                throw new IllegalArgumentException("illegal LifecycleProvider !!");
            }

        };

    }

    public static <T> FlowableTransformer<T, T> flowableLifecycle(LifecycleProvider provider) {

        return flowable -> {
            if (provider instanceof RxFragment) {
                return flowable.compose(((RxFragment) provider).bindToLifecycle());
            } else if (provider instanceof RxAppCompatActivity) {
                return flowable.compose(((RxAppCompatActivity) provider).bindToLifecycle());
            } else if (provider instanceof RxAppCompatDialogFragment) {
                return flowable.compose(((RxAppCompatDialogFragment) provider).bindToLifecycle());
            } else {
                throw new IllegalArgumentException("illegal LifecycleProvider !!");
            }

        };

    }

    public static Flowable<Long> timer(int initDelay,int period, int count ,TimeUnit unit, LifecycleProvider provider) {

        return Flowable.interval(initDelay, period, unit)
                .take(count)
                .compose(RxUtils.flowableLifecycle(provider))
                .compose(RxUtils.rxSwitch());

    }

    public static Flowable<Long> timer(int period,int count , LifecycleProvider provider) {

        return timer(0,period,count,TimeUnit.SECONDS,provider);
    }

    public static Flowable<Long> timer(int period,int count , TimeUnit unit,LifecycleProvider provider) {
        return timer(0,period,count,unit,provider);
    }

    public static Flowable<Long> delay(int time, TimeUnit unit,LifecycleProvider provider) {
        return Flowable.timer( time, unit)
                .compose(RxUtils.flowableLifecycle(provider))
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    public static Flowable<Long> delay(int time,LifecycleProvider provider) {
        return Flowable.timer( time, TimeUnit.SECONDS)
                .compose(RxUtils.flowableLifecycle(provider))
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}
