package me.leon.quickdev.ui.activity.main2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import me.leon.libs.base.BaseActivity;
import me.leon.libs.base.BasePresenter;
import me.leon.quickdev.R;
import me.leon.quickdev.bean.Meizi;
import me.leon.quickdev.ui.adapter.MeiZiAdapter;

/**
 * Created by PC on 2017/12/22.
 */

public class Main2Activity extends BaseActivity<Main2Contract.View,Main2Presenter> implements Main2Contract.View {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    private int page = 1;
    private MeiZiAdapter adapter;

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

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeiZiAdapter(R.layout.item_meizi);
        rv.setAdapter(adapter);
        adapter.setOnLoadMoreListener(()->{
            getPresenter().doFetch(++page);

        },
                rv);

        srl.setOnRefreshListener(()->{
            srl.setRefreshing(true);
            getPresenter().doFetch(page =1);

        });
        optimizeRecyclerViewScrollLoadImage(rv);


        getPresenter().doFetch(page);


    }


    @Override
    public void onError(Throwable e) {
        srl.setRefreshing(false);
    }

    @Override
    public void onFail(Throwable e) {
        srl.setRefreshing(false);
    }

    @Override
    public void onFetchSuccess(List<Meizi.Results> results) {
        srl.setRefreshing(false);
        if (page ==1) {
            adapter.setNewData(results);
        }else {
            adapter.addData(results);
        }

        if ( results.size() ==0) {

            adapter.loadMoreEnd();

        }else {
            adapter.loadMoreComplete();
        }
    }
}
