package me.leon.quickdev.ui.entry.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.leon.baselibs.BuildConfig;
import me.leon.devsuit.android.SPUtils;
import me.leon.libs.Config;
import me.leon.libs.base.BasePresenter;
import me.leon.libs.engine.http.API;
import me.leon.libs.utils.M;
import me.leon.quickdev.bean.SimpleUser;
import me.leon.quickdev.bean.User;
import me.leon.quickdev.http.OkRx2Manager;

/**
 * Created by PC on 2017/12/23.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    public MainPresenter(LifecycleProvider mProvider) {
        super(mProvider);
    }

    @Override
    public void doFetch() {

        OkRx2Manager.postObject(Config.API_HOST + API.PUBLICINFO, new HttpParams(), String.class, getProvider())
                .subscribe(data -> Log.d("MainPresenter", data)
                        , getView()::onError);


//        API.buildRequest(new HttpParams(),API.PUBLICINFO).execute(new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//               // getView().onFetchSuccess();
//            }
//
//
//
//        });
    }

    @Override
    public void doDownload(String path) {
        OkRx2Manager.downloadFile2("http://down11.zol.com.cn/downsjbz/ttpod10.0.7@148_19985.exe", new HttpParams())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Progress>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Progress progress) {

                        if (BuildConfig.DEBUG)
                            Log.d("MainPresenter", "progress.fraction:" + progress.fraction);

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG)
                            Log.d("MainPresenter", "onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (BuildConfig.DEBUG)
                            Log.d("MainPresenter", "onComplete");
                    }
                });
    }

    @Override
    public void doUpload(List<File> files) {


        if (BuildConfig.DEBUG) Log.d("MainPresenter", "List<File> : " + files);

        StringBuilder sb = new StringBuilder();
        sb.append("");
//
        Observable.fromIterable(files)
                .flatMap(file -> OkRx2Manager.uploadFileNormal(Config.API_HOST + API.UPLOADIMG, getHttpFileParams(file), String.class))

                .subscribe(str -> {
                    sb.append(str).append(",");
                    Log.d("MainPresenter", "path" +  sb.toString());
                }, Throwable::printStackTrace);


//        OkRx2Manager.uploadFileNormal(Config.API_HOST + API.UPLOADIMG, params, files.get(0), String.class)
//                .subscribe(paths -> Log.d("MainPresenter", "upload : " +paths.toString()));

    }

    @NonNull
    private HttpParams getHttpFileParams(File file) {
        HttpParams params = API.getUserParams();
        params.put("type", 2);
        params.put("img", file.getName());
        params.put("img", file);
        return params;
    }

    public void doLogin(String mobile, String password) {
        HttpParams params = new HttpParams();
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("appversign", 0);

        OkRx2Manager.postNorml(Config.API_HOST + API.LOGIN, params, Integer.class, getProvider())
                .subscribe(integer -> saveUser(integer, mobile, M.generate(integer + Config.API_KEY)));


    }

    private void saveUser(int uid, String mobile, String token) {

        SPUtils.getInstance("user").put("uid", uid);
        SPUtils.getInstance("user").put("mobile", mobile);
        SPUtils.getInstance("user").put("token", token);
        User user = new User();
        user.setId(uid);
        user.setTel(mobile);
        user.setToken(token);

        if (BuildConfig.DEBUG) Log.d("MainPresenter", "Login Success : " + token);

//        App.realm.executeTransaction(realm -> realm.where(User.class).findAll().deleteAllFromRealm());
//        User user = new User();
//        user.setId(uid);
//        user.setTel(mobile);
//        user.setToken(token);
//        App.realm.executeTransaction(realm -> realm.insertOrUpdate(user));

    }

    public void doFetchList() {
        HttpParams params = new HttpParams();
        params.put("uid", M.generateUUID());
        params.put("sex", 1);
        params.put("city", "杭州市");


        OkRx2Manager.postArray(Config.API_HOST + API.LOOKLIST, params, SimpleUser.class, getProvider())
                .subscribe(data -> getView().onFetchSuccess(data),
                        throwable -> getView().onError(throwable));

//        API.buildRequest(params, API.LOOKLIST).execute(new JsonCallback<JSONArray>(getProvider()) {
//
//
//                @Override
//                protected void onSuccess(JSONArray data) {
//                    getView().onFetchSuccess(JSON.parseArray(data.toJSONString(), SimpleUser.class));
//            }
//
//            @Override
//            protected void onFailure(Throwable throwable) {
//                getView().onError(throwable);
//            }
//        });
    }
}
