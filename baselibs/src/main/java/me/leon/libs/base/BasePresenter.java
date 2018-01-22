package me.leon.libs.base;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

/**
 * @Author:  Leon
 * @Time:    2017/4/5 上午11:33
 */

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> mView;
    private LifecycleProvider mProvider;

    public BasePresenter(LifecycleProvider mProvider) {
        this.mProvider = mProvider;
    }

    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        mView.clear();
        mView = null;
        mProvider = null;
    }

    @Override
    public V getView() {
        if (mView == null || mView.get() == null) {
            throw new NullPointerException("null view exception");
        } else {
            return mView.get();
        }
    }

    public LifecycleProvider getProvider() {
        return mProvider;
    }
}
