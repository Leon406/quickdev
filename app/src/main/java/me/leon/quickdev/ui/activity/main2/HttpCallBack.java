package me.leon.quickdev.ui.activity.main2;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by Leon on 2017/12/23 0023.
 */

public abstract class HttpCallBack<T> extends AbsCallback<T> {

    public HttpCallBack(LifecycleProvider mProvider) {
        this.mProvider = mProvider;
    }

    private LifecycleProvider mProvider;

    @Override
    public void onSuccess(Response<T> response) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        return null;
    }
}
