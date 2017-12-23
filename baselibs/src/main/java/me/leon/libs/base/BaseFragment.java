package me.leon.libs.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.trello.rxlifecycle2.components.support.RxFragment;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import me.leon.libs.engine.image.GlideApp;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Author:  Leon
 * Time:    2017/4/5 下午12:56
 */

public abstract class BaseFragment<V extends IView, P extends BasePresenter<V>> extends RxFragment {

    private P mPresenter;
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化 Presenter
        mPresenter = bindPresenter();
        // Presenter 绑定 View
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载布局
        View view = inflater.inflate(bindLayout(), container, false);
        // 绑定布局
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 视图初始化
        onViewInit();
    }

    /**
     * 优化滚动加载数据  滑动时不加载图片
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
