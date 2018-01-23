package me.leon.quickdev.http.retrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.leon.quickdev.bean.Meizi;
import me.leon.quickdev.bean.MyJoke;
import me.leon.quickdev.bean.UserBean;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("open/xiaohua.json")
    Observable<List<MyJoke>> getData();

    @GET("http://gank.io/api/data/福利/10/{page}")
    Observable<Meizi> Meizi(@Path("page") int page);

    @GET("https://api.github.com/users/whatever")
    Observable<ResponseBody> getUser(@Query("client_id") String id, @Query("client_secret") String secret);

    @GET("https://api.github.com/users/whatever")
    Observable<ResponseBody> getUser(@QueryMap Map<String, String> map);//泛型 必须加

    @POST("https://api.github.com/login")
    Observable<ResponseBody> login(@Body UserBean user);//上传json


    @POST("https://api.github.com/login")
    @FormUrlEncoded
    Observable<ResponseBody> login(@FieldMap Map<String, String> map);//上传json

//    /**
//     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
//     *
//     * @param parts 每个part代表一个
//     * @return 状态信息
//     */
//    @Multipart
//    @POST("users/image")
//    Observable<ResponseBody> uploadFilesWithParts(@Part() List<MultipartBody.Part> parts,
//                                                  @Part("api_key") RequestBody request_api_key,
//                                                  @Part("api_secret") RequestBody request_api_secret,
//                                                  @Part("password") RequestBody password);
//
//    @Multipart
//    @POST("users/image")
//    Observable<ResponseBody> uploadFilesWithParts(@Part() List<MultipartBody.Part> parts,
//                                                  @PartMap Map<String, RequestBody> map);
//
//    /**
//     * 通过 MultipartBody和@body作为参数来上传
//     *
//     * @param multipartBody MultipartBody包含多个Part
//     * @return 状态信息
//     */
//    @POST("users/image")
//    Observable<ResponseBody> uploadFileWithRequestBody(@Body MultipartBody multipartBody);

}



