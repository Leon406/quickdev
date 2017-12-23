package me.leon.quickdev.ui.activity.main2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Flowable;
import me.leon.libs.base.BasePresenter;
import me.leon.libs.utils.RxUtils;
import me.leon.quickdev.bean.Meizi;

/**
 * Created by Leon on 2017/12/23 0023.
 */

public class Main2Presenter extends BasePresenter<Main2Contract.View> implements Main2Contract.Presenter {

    public Main2Presenter(LifecycleProvider mProvider) {
        super(mProvider);
    }

    @Override
    public void doFetch(int page) {
        try {
            GetRequest request = OkGo.get("http://gank.io/api/data/福利/10/" + page);

            Flowable.just(request)
                    .map(req->request.execute())
                    .compose(RxUtils.rxSwitch())
                    .subscribe(res->{
                        JSONObject parseObject = JSONObject.parseObject(res.body().string());
                        List<Meizi.Results> results
                                = JSONArray.parseArray(parseObject.getString("results"), Meizi.Results.class);

                            getView().onFetchSuccess(results);

                    });
        } catch (Exception e) {
            getView().onError(e);
            e.printStackTrace();
        }
    }
}
