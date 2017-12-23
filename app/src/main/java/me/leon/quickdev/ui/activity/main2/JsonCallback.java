/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.leon.quickdev.ui.activity.main2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.request.base.Request;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import me.leon.libs.engine.http.HTTPResponse;
import me.leon.libs.utils.RxUtils;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * 修订历史：
 * ================================================
 */
public abstract class JsonCallback<T> extends AbsCallback<HTTPResponse<T>> {

    private LifecycleProvider mProvider;

    public JsonCallback(LifecycleProvider mProvider) {
        this.mProvider = mProvider;
    }

    @Override
    public void onStart(Request<HTTPResponse<T>, ? extends Request> request) {
        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
//        request.headers("header1", "HeaderValue1")//
//                .params("params1", "ParamsValue1")//
//                .params("token", "3215sdf13ad1f65asd4f3ads1f");
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public HTTPResponse<T> convertResponse(Response response) throws Throwable {

        // 获取字符串并转码
        String s = unicodeToString(new StringConvert().convertResponse(response));

        HTTPResponse<T>  HTTPResponse= JSON.parseObject(s, new TypeReference<HTTPResponse<T>>() {
        });

        if (mProvider instanceof RxAppCompatActivity) {
            Flowable.just(HTTPResponse)
                    .compose(((RxAppCompatActivity)mProvider).bindToLifecycle())
                    .compose(RxUtils.rxSwitch())
                    .subscribe(r -> {
                                if (r.getCode() == 1) {
                                    onSuccess(r.getData(), r.getMessage());
                                }else if(r.getCode() == -1){
                                    onError(new Throwable(r.getMessage()));
                                }else{
                                    onFailure(new Throwable(r.getMessage()));
                                }
                            }
                    );
        }else if (mProvider instanceof RxFragment) {
            Flowable.just(HTTPResponse)
                    .compose(((RxFragment)mProvider).bindToLifecycle())
                    .compose(RxUtils.rxSwitch())
                    .subscribe(r -> {
                                if (r.getCode() == 1) {
                                    onSuccess(r.getData(), r.getMessage());
                                }else if(r.getCode() == -1){
                                    onError(new Throwable(r.getMessage()));
                                }else{
                                    onFailure(new Throwable(r.getMessage()));
                                }
                            }
                    );
        }else  if (mProvider instanceof RxAppCompatDialogFragment) {
            Flowable.just(HTTPResponse)
                    .compose(((RxAppCompatDialogFragment)mProvider).bindToLifecycle())
                    .compose(RxUtils.rxSwitch())
                    .subscribe(r -> {
                                if (r.getCode() == 1) {
                                    onSuccess(r.getData(), r.getMessage());
                                }else if(r.getCode() == -1){
                                    onError(new Throwable(r.getMessage()));
                                }else{
                                    onFailure(new Throwable(r.getMessage()));
                                }
                            }
                    );
        }else {
            
            
        }
       

        return HTTPResponse;
    }

    protected abstract void onSuccess(T data, String message);

    protected  void onError(Throwable throwable){

        me.leon.libs.utils.T.getInstance().show("Error", me.leon.libs.utils.T.ERR);

        
    };

    protected  void onFailure(Throwable throwable){
        me.leon.libs.utils.T.getInstance().show("Fail", me.leon.libs.utils.T.ERR);
    };

    /**
     * Unicode 转字符串
     *
     * @param str Unicode 字符串
     * @return 转换后字符串
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find() ) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
}
