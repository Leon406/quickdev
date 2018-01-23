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
package me.leon.quickdev.http.okrx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.convert.Converter;
import com.lzy.okgo.convert.StringConvert;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.leon.libs.engine.http.HTTPResponse;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class FastJsonConvert<T> implements Converter<HTTPResponse<T>> {

    private Type type;
    private Class<T> clazz;
    private LifecycleProvider mProvider;

    public FastJsonConvert() {
    }

    public FastJsonConvert(LifecycleProvider mProvider) {
        this.mProvider = mProvider;
    }

    public FastJsonConvert(Type type) {
        this.type = type;
    }

    public FastJsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }


    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象，生成onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public HTTPResponse<T> convertResponse(Response response) throws Throwable {

        // 获取字符串并转码
        String s = unicodeToString(new StringConvert().convertResponse(response));

        HTTPResponse<T> HTTPResponse = JSON.parseObject(s, new TypeReference<HTTPResponse<T>>() {
        });


        return HTTPResponse;
    }

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
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }


}
