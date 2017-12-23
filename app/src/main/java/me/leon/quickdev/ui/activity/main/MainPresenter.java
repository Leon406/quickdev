package me.leon.quickdev.ui.activity.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.trello.rxlifecycle2.LifecycleProvider;

import me.leon.libs.base.BasePresenter;
import me.leon.libs.engine.http.API;
import me.leon.libs.utils.M;

/**
 * Created by PC on 2017/12/23.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    public MainPresenter(LifecycleProvider mProvider) {
        super(mProvider);
    }

    @Override
    public void doFetch() {
        API.buildRequest(new HttpParams(),API.PUBLICINFO).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                getView().onFetchSuccess();
            }


        });
    }

    public  void doFetchList(){
        HttpParams params = new HttpParams();
        params.put("uid", M.generateUUID());
        params.put("sex", 1);
        params.put("city", "杭州市");

        API.buildRequest(params, API.LOOKLIST).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
//                getView().onFetchSuccess(response.body());
            }
        });
    }
}
