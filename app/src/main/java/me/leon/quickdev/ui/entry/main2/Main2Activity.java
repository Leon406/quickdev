package me.leon.quickdev.ui.entry.main2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import me.leon.libs.Config;
import me.leon.libs.base.RightSwipeBaseActivity;
import me.leon.quickdev.R;
import me.leon.quickdev.bean.Meizi;
import me.leon.quickdev.ui.entry.gallery.GalleryActivity;

/**
 * Created by PC on 2017/12/22.
 */

public class Main2Activity extends RightSwipeBaseActivity<Main2Contract.View, Main2Presenter> implements Main2Contract.View {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    private int page = 1;
    private MeiZiAdapter adapter;
    private LinearLayoutManager layoutManager;

    public static void start(Context context) {
        Intent starter = new Intent(context, Main2Activity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected Main2Presenter bindPresenter() {
        return new Main2Presenter(this);
    }

    @Override
    protected void onViewInit() {

        layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(layoutManager);
        adapter = new MeiZiAdapter(R.layout.item_meizi);
        rv.setAdapter(adapter);
        adapter.setOnLoadMoreListener(() -> getPresenter().doFetch(++page), rv);

        adapter.setOnItemChildClickListener((adapter, v, pos) ->
                Flowable.fromIterable(((MeiZiAdapter) adapter).getData())
                        .map(o -> o.url)
                        .toList()
                        .subscribe(urls -> {
                            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "share");

                            Intent intent = new Intent(this, GalleryActivity.class);

                            intent.putExtra(Config.BUNDLE_POSITION, pos);
                            intent.putExtra(Config.BUNDLE_DATA, ((ArrayList<String>) urls));
                            startActivity(intent,transitionActivityOptions.toBundle());
                            overridePendingTransition(0,0);
                           // GalleryActivity.start(this, pos, ((ArrayList<String>) urls));
                        })
        );

        handleTouchEventConflict(rv);

        srl.setOnRefreshListener(() -> {
            srl.setRefreshing(true);
            getPresenter().doFetch(page = 1);

        });
        optimizeRecyclerViewScrollLoadImage(rv);

        getPresenter().doFetch(page);

    }


    @Override
    public void onError(Throwable e) {
        srl.setRefreshing(false);
        adapter.loadMoreFail();
    }


    @Override
    public void onFetchSuccess(List<Meizi.Results> results) {
        srl.setRefreshing(false);
        if (page == 1) {
            adapter.setNewData(results);
        } else {
            adapter.addData(results);
        }

        if (results.size() == 0) {

            adapter.loadMoreEnd();

        } else {
            adapter.loadMoreComplete();
        }
    }

}
