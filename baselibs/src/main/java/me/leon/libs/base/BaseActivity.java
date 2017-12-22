package me.leon.libs.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;


import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import me.leon.libs.engine.image.GlideApp;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Author:  Parorisim
 * Time:    2017/4/5 上午11:29
 * Desc:    活动基类
 */

public abstract class BaseActivity<V extends IView, P extends BasePresenter<V>> extends RxAppCompatActivity {

    private P mPresenter;
    public Dialog dialog;
    protected boolean isDarkStatusBar = true;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 矢量资源兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        // 初始化 Presenter
        mPresenter = bindPresenter();
        // Presenter 绑定 View
        if (mPresenter != null) mPresenter.attachView((V) this);

        setContentView(bindLayout());
        // 绑定布局
        ButterKnife.bind(this);
        // 视图初始化
        onViewInit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Presenter 解绑 View
        if (mPresenter != null) mPresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 优化列表滑动
     * @param rv
     */
    protected void optimizeRecyclerViewScrollLoadImage(RecyclerView rv) {

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                try {
                    if (newState == SCROLL_STATE_IDLE) {
                        GlideApp.with(recyclerView.getContext()).resumeRequests();

                    } else {
                        GlideApp.with(recyclerView.getContext()).pauseRequests();
                    }
                } catch (Exception e) {

                }

            }
        });

    }

    /**
     * 获取 Presenter 实例
     * @return Presenter
     */
    @NonNull
    protected P getPresenter() {
        return mPresenter;
    }

    /**
     * 绑定布局
     * @return 布局资源 ID
     */
    @LayoutRes
    protected abstract int bindLayout();

    /**
     * 绑定 Presenter
     * @return Presenter
     */
    protected abstract P bindPresenter();

    /**
     * 视图初始化
     */
    protected abstract void onViewInit();
}
