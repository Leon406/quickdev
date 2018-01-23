package me.leon.quickdev.ui.entry.main2;

import com.lzy.okgo.model.HttpParams;
import com.trello.rxlifecycle2.LifecycleProvider;

import me.leon.libs.base.BasePresenter;
import me.leon.quickdev.bean.Meizi;
import me.leon.quickdev.http.okrx.OkRx2Manager;

/**
 * Created by Leon on 2017/12/23 0023.
 */

public class Main2Presenter extends BasePresenter<Main2Contract.View> implements Main2Contract.Presenter {

    public Main2Presenter(LifecycleProvider mProvider) {
        super(mProvider);
    }

    @Override
    public void doFetch(int page) {

        OkRx2Manager.getNormal("http://gank.io/api/data/福利/10/" + page, new HttpParams(), Meizi.class)
                .subscribe(meizi -> getView().onFetchSuccess(meizi.results),
                        throwable -> getView().onError(throwable));
//        try {
//            GetRequest request = OkGo.get("http://gank.io/api/data/福利/10/" + page);
//
//            Flowable.fromCallable(() -> request.execute())
//                    .compose(((RxAppCompatActivity) getProvider()).bindToLifecycle())
//                    .compose(RxUtils.rxSwitch())
//                    .subscribe(res -> {
//                        JSONObject parseObject = JSONObject.parseObject(res.body().string());
//                        List<Meizi.Results> results
//                                = JSONArray.parseArray(parseObject.getString("results"), Meizi.Results.class);
//
//                        getView().onFetchSuccess(results);
//
//                    },throwable -> getView().onError(throwable) );
//        } catch (Exception e) {
//            getView().onError(e);
//            e.printStackTrace();
//        }
    }
}
