package me.leon.quickdev.http.retrofit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import me.leon.libs.engine.http.LoggerInterceptor;
import me.leon.libs.utils.RxUtils;
import me.leon.quickdev.bean.MyJoke;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    public static final int CONNECT_TIMEOUT = 30;
    public static final int WRITE_TIMEOUT = 30;
    public static final String BASE_URL = "http://api.laifudao.com/";
    private Retrofit mRetrofit;
    private ApiService mApiService;

    private RetrofitManager() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mApiService = mRetrofit.create(ApiService.class);

    }

    public static RetrofitManager getInstance() {
        return Holder.INSTANCE;
    }

    static class Holder {
        private static RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**
     *  获取api对象  发起call 请求
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public ApiService getApiService() {
        return mApiService;
    }

    public void getJoke(Observer<List<MyJoke>> observer){

        mApiService.getData()
                .compose(RxUtils.observeThreadSwitch())
                .subscribe(observer);
    }

    public static MultipartBody files2MultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    private static MediaType getMediaType(String name) {
        if (name.endsWith(".png")) {
            return MediaType.parse("image/png");
        }else if (name.endsWith(".jpg")) {

            return MediaType.parse("image/jpg");
        }else{
            return MediaType.parse("application/otcet-stream");
        }


    }
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }


    public static RequestBody createRequestBody(Object obj) {
        if (obj instanceof String) {
            return RequestBody.create(MediaType.parse("multipart/form-data"), ((String) obj));
        }else if (obj instanceof File) {
            return RequestBody.create(MediaType.parse("multipart/form-data"), ((File) obj));
        }
       return null;
    }


}
