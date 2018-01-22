package me.leon.libs.engine.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.util.List;

import io.reactivex.Observable;
import me.leon.devsuit.android.SPUtils;
import me.leon.libs.Config;
import me.leon.libs.utils.M;


/**
 * Author:  Leon
 * Time:    2017/4/6 下午1:47
 * Desc:    API 接口
 */

public class API {

    /**
     * 注册
     * <p>
     * mobile       手机号
     * checkcode    验证码
     * password     密码
     * channel      渠道
     * appversign   包标识（0）
     */
    public static final String REGISTER = "index/register";

    /**
     * 登录
     * <p>
     * mobile       手机号
     * password     密码
     * appversign   包标识（0）
     */
    public static final String LOGIN = "index/login";


    /**
     * 发送验证码
     * <p>
     * mobile       手机号
     * appversign   包标识（0）
     */
    public static final String SENDCODE = "index/sendcode";


    /**
     * 上传图片
     * <p>
     * uid      用户 ID
     * token    用户令牌
     * type     图片类型(1认证|2其它)
     * img      图片流
     */
    public static final String UPLOADIMG = "index/uploadimg";

    /**
     * 获取用户个人资料
     * <p>
     * uid          用户 ID
     * token        用户令牌
     * photonum     简单动态数量
     */
    public static final String USERINFO = "user/userinfo";

    /**
     * 获取支付参数
     * <p>
     * uid      用户 ID
     * token    用户令牌
     * apptype  应用类型（1官方版）
     * teltype  手机品牌
     * mobile   操作系统（1安卓|2苹果）
     * paytype  支付类型（1支付宝|2微信）
     * cvid     商品 ID（付费认证时传空）
     * point    会员购买触发点（选填）
     * rpoint   付费认证触发点（选填）
     */
    public static final String PAYTYPE = "index/paytype";


    /**
     * 上传个人形象照
     * <p>
     * uid      用户 ID
     * token    用户令牌
     * img      图片地址
     */
    public static final String USERPHOTO = "index/userphoto";


    /**
     * 逛一逛列表
     * <p>
     * uid      游客唯一标识
     * sex      性别
     * city     城市
     */
    public static final String LOOKLIST = "index/looklist";



    /**
     * 获取用户详情
     * <p>
     * uid      用户 ID
     * token    用户令牌
     * vuid     被查看用户 ID
     */
    public static final String USERVIEW = "index/userview";

    /**
     * 获取公共数据
     */
    public static final String PUBLICINFO = "index/publicinfo";






    /**
     * 创建请求实体
     *
     * @param params 请求参数
     * @param path   请求路径
     * @return 请求实体
     */
    public static PostRequest buildRequest(HttpParams params, String path) {
        // 添加时间戳
        params.put("time", System.currentTimeMillis() / 1000);
        // 参数排序处理
        Observable.fromIterable(params.urlParamsMap.keySet())
                .sorted()
                .map(s -> params.urlParamsMap.get(s).get(0))
                .toList()
                .subscribe(s -> buildParams(params, s));
        // 返回请求对象
        return OkGo.post(Config.API_HOST + path).params(params);
    }
    /**
     * 创建请求实体
     *
     * @param params 请求参数
     * @param path   请求路径
     * @return 请求实体
     */
    public static GetRequest buildGetRequest(HttpParams params, String path) {
        // 添加时间戳
        params.put("time", System.currentTimeMillis() / 1000);
        // 参数排序处理
        Observable.fromIterable(params.urlParamsMap.keySet())
                .sorted()
                .map(s -> params.urlParamsMap.get(s).get(0))
                .toList()
                .subscribe(s -> buildParams(params, s));
        // 返回请求对象
        return OkGo.get(Config.API_HOST + path).params(params);
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

    /**
     * 创建请求参数  排序加盐后生成md5值
     *
     * @param params 原始参数
     * @param values 已排序值
     */
    private static void buildParams(HttpParams params, List<String> values) {
        StringBuilder temp = new StringBuilder();
        values.add("loveu123456");
        for (String value : values) {
            temp.append(value);
        }
        params.put("hash", M.generate(temp.toString()));
    }
}
