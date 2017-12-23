package me.leon.libs.utils;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *
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
        return observable -> observable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());

    }
}
