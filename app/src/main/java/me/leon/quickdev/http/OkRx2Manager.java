package me.leon.quickdev.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.convert.FileConvert;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableBody;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import me.leon.devsuit.android.SPUtils;
import me.leon.libs.engine.http.HTTPResponse;
import me.leon.libs.utils.M;
import me.leon.libs.utils.RxUtils;

/**
 * FastJson OkRx2
 */
public class OkRx2Manager {


    public static <T> Observable<T> getObject(String url, HttpParams params, Class<T> clazz, LifecycleProvider provider) {

        return OkGo.<HTTPResponse<JSONObject>>get(url)
                .params(params)
                .converter(new FastJsonConvert<JSONObject>())
                .adapt(new ObservableBody<HTTPResponse<JSONObject>>())
                .compose(RxUtils.observeLifecycle(provider))
                .map(resp -> JSON.parseObject(resp.getData().toJSONString(), clazz))
                .compose(RxUtils.observeThreadSwitch())
                ;

    }

    public static <T> Observable<List<T>> getArray(String url, HttpParams params, Class<T> clazz, LifecycleProvider provider) {

        return OkGo.<HTTPResponse<JSONArray>>get(url)
                .params(params)
                .converter(new FastJsonConvert<JSONArray>())
                .adapt(new ObservableBody<HTTPResponse<JSONArray>>())
                .compose(RxUtils.observeLifecycle(provider))
                .map(resp -> JSON.parseArray(resp.getData().toJSONString(), clazz))
                .compose(RxUtils.observeThreadSwitch())
                ;

    }

    public static <T> Observable<T> getNormal(String url, HttpParams params, Class<T> clazz) {
        return OkGo.<T>get(url)
                .params(params)
                .converter(new GsonConvert<T>(clazz))
                .adapt(new ObservableBody<T>())
                .compose(RxUtils.observeThreadSwitch())
                ;
    }

    public static <T> Observable<T> postObject(String url, HttpParams params, Class<T> clazz, LifecycleProvider provider) {
        // 添加时间戳
        buildHashParam(params);


        return OkGo.<HTTPResponse<JSONObject>>get(url)
                .params(params)
                .converter(new FastJsonConvert<JSONObject>() {
                })
                .adapt(new ObservableBody<HTTPResponse<JSONObject>>())
                .compose(RxUtils.observeLifecycle(provider))
                .map(resp -> JSON.parseObject(resp.getData().toJSONString(), clazz))
                .compose(RxUtils.observeThreadSwitch())
                ;

    }

    public static <T> Observable<T> postNorml(String url, HttpParams params, Class<T> clazz, LifecycleProvider provider) {
        // 添加时间戳
        buildHashParam(params);


        return OkGo.<HTTPResponse<T>>get(url)
                .params(params)
                .converter(new FastJsonConvert<T>() {
                })
                .adapt(new ObservableBody<HTTPResponse<T>>())
                .compose(RxUtils.observeLifecycle(provider))
                .map(resp->resp.getData())
                .compose(RxUtils.observeThreadSwitch())
                ;

    }


    public static <T> Observable<List<T>> postArray(String url, HttpParams params, Class<T> clazz, LifecycleProvider provider) {
        buildHashParam(params);


        return
                OkGo.<HTTPResponse<JSONArray>>get(url)
                        .params(params)
                        .converter(new FastJsonConvert<JSONArray>(provider) {
                        })
                        .adapt(new ObservableBody<HTTPResponse<JSONArray>>())
                        .compose(RxUtils.observeLifecycle(provider))
                        .map(resp -> JSON.parseArray(resp.getData().toJSONString(), clazz))
                        .compose(RxUtils.observeThreadSwitch())
                ;
    }

    private static void buildHashParam(HttpParams params) {
        // 添加时间戳
        params.put("time", System.currentTimeMillis() / 1000);
        // 参数排序处理
        Observable.fromIterable(params.urlParamsMap.keySet())
                .sorted()
                .map(s -> params.urlParamsMap.get(s).get(0))
                .toList()
                .subscribe(s -> buildHashParam(params, s));
    }

    /**
     * 上传文件
     *
     * @param url
     * @param params
     * @param files
     * @param clazz
     * @param <T>
     * @return
     */

    public static <T> Observable<T> uploadFiles(String url, HttpParams params, List<File> files, Class<T> clazz) {
        return OkGo.<HTTPResponse<JSONObject>>post(url)
                .params(params)
                .addFileParams("file", files)
                .converter(new FastJsonConvert<JSONObject>())
                .adapt(new ObservableBody<HTTPResponse<JSONObject>>())
                .map(resp -> JSON.parseObject(resp.getData().toJSONString(), clazz))
                .compose(RxUtils.observeThreadSwitch())
                ;
    }

    public static <T> Observable<T> uploadFile(String url, HttpParams params, File file, Class<T> clazz) {
        return OkGo.<HTTPResponse<JSONObject>>post(url)
                .params(params)
                .params("img", file)
                .converter(new FastJsonConvert<JSONObject>())
                .adapt(new ObservableBody<HTTPResponse<JSONObject>>())
                .map(resp -> JSON.parseObject(resp.getData().toJSONString(), clazz))
                .compose(RxUtils.observeThreadSwitch())
                ;
    }

    public static <T> Observable<T> uploadFileNormal(String url, HttpParams params, Class<T> clazz) {
        // 添加时间戳
        buildHashParam(params);

        return OkGo.<HTTPResponse<T>>post(url)
                .params(params)
                .converter(new FastJsonConvert<T>())
                .adapt(new ObservableBody<HTTPResponse<T>>())
                .map(resp -> resp.getData())
                .compose(RxUtils.observeThreadSwitch())
                ;
    }

    /**
     * 下载无回调
     *
     * @param url
     * @param params
     * @param path
     * @return
     */
    public static Observable<String> downloadFile(String url, HttpParams params, String path) {
        return OkGo.<File>get(url)
                .params(params)
                .converter(new FileConvert(path))
                .adapt(new ObservableBody<File>())
                .map(resp -> resp.getPath())
                .compose(RxUtils.observeThreadSwitch())
                ;

    }

    /**
     * 下载有回调
     * @param url
     * @param params
     * @return
     */

    public static Observable<Progress> downloadFile2(String url, HttpParams params) {
        return Observable.create(emitter ->
                OkGo.<File>get(url)//
                        .params(params)//
                        .execute(new FileCallback() {
                            @Override
                            public void onSuccess(Response<File> response) {
                                emitter.onComplete();
                            }
                            @Override
                            public void onError(Response<File> response) {
                                emitter.onError(response.getException());
                            }

                            @Override
                            public void downloadProgress(Progress progress) {
                                emitter.onNext(progress);
                            }
                        }));
    }


    /**
     * 创建请求参数  排序加盐后生成md5值
     *
     * @param params 原始参数
     * @param values 已排序值
     */
    private static void buildHashParam(HttpParams params, List<String> values) {
        StringBuilder temp = new StringBuilder();
        values.add("loveu123456");
        for (String value : values) {
            temp.append(value);
        }
        params.put("hash", M.generate(temp.toString()));
    }

    /**
     * 获取通用请求参数
     *
     * @return 请求参数
     */
    public static HttpParams getUserParams() {

        HttpParams params = new HttpParams();
        int uid = SPUtils.getInstance("user").getInt("uid");
        String token = SPUtils.getInstance("user").getString("token");

        params.put("uid", uid);
        params.put("token", token);

        return params;
    }

}
