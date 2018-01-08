package me.leon.libs.engine.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
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
     * 找回密码
     * <p>
     * mobile       手机号
     * checkcode    验证码
     * password     密码
     */
    public static final String RETRIEVE = "index/forget";

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
     * 完善资料
     * <p>
     * uid          用户 ID
     * token        用户令牌
     * username     昵称
     * sex          性别
     * provinceid   省份 ID
     * cityid       城市 ID
     * quyuid       区域 ID
     * birthday     生日
     * income       收入
     * myinfo       自我介绍
     * version      版本名称
     * appversign   包标识（0）
     */
    public static final String EDITUSERINFO = "index/edituserinfo";

    /**
     * 身份认证提交
     * <p>
     * uid          用户 ID
     * token        用户令牌
     * type         证件类型
     * realname     真实姓名
     * numbers      证件号码
     * zimg         证件正面照
     * simg         手持证件照
     */
    public static final String AUTHUSER = "index/authuser";

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
     * 上报用户
     * <p>
     * uid      用户 ID
     * token    用户令牌
     */
    public static final String UPDATEUSER = "index/updateuser";

    /**
     * 首页用户列表
     * <p>
     * uid                  用户 ID
     * token                用户令牌
     * type                 类型（1游客|2注册用户）
     * city                 当前城市
     * page                 当前页
     * <p>
     * suid                 搜索用户 ID
     * feel_experience      感情经历
     * expect_time          期望结婚时间
     * smoke_status         吸烟习惯
     * wine_status          饮酒习惯
     * user_history         婚史
     * user_constellation   星座
     * user_income          年收入
     * user_iscar           资产车
     * user_ishouse         资产房
     * user_edu             学历
     * user_city            地区（省-市-区）
     * user_age             年龄
     * user_height          身高
     */
    public static final String HOMEINDEX = "index/homeindex";


    /**
     * 获取动态列表
     * <p>
     * uid      用户 ID
     * token    用户令牌
     * page     页数
     */
    public static final String DYNAMICLIST = "dynamic/dynamiclist";


    /**
     * 编辑用户资料
     * <p>
     * uid                  用户 ID
     * token                用户令牌
     * photo                形象照
     * username             昵称
     * myinfo               自我介绍
     * birthday             出生日期
     * income               年收入
     * provinceid           常住地省份 ID
     * cityid               常住地城市 ID
     * quyuid               常住地区域 ID
     * height               身高
     * weight               体重
     * expect_time          期望结婚时间
     * household_province   户籍省份 ID
     * household_city       户籍城市 ID
     * household_quyu       户籍区域 ID
     * nation               民族
     * faith                信仰
     * wine                 饮酒
     * smoke                吸烟
     * occupation           职业
     * career_planning      职业规划
     * feel_experience      情感经历
     * marriage             婚姻状况
     * family               家庭情况
     * children             有无子女
     * home_rand            家中排行
     * version              当前版本
     * appversign           包标识（0）
     * teltype              平台（1苹果|2安卓）
     */
    public static final String EDITUSER = "user/edituser";

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
     * 获取会员购买参数
     * <p>
     * uid      用户 ID
     * token    用户令牌
     * sex      性别
     */
    public static final String VIPMONEY = "index/vipmoney";

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
     * 上报推送 ID
     * <p>
     * uid      用户 ID
     * token    用户令牌
     * pushid   推送 ID
     */
    public static final String PUSH = "index/push";

    /**
     * 根据云信账号获取用户 ID
     * <p>
     * yxuser   云信账号
     */
    public static final String GETUSERID = "user/getuserid";


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
        Observable.fromIterable(params.urlParamsMap.keySet()).sorted().map(s -> params.urlParamsMap.get(s).get(0)).toList().subscribe(s -> buildParams(params, s));
        // 返回请求对象
        return OkGo.post(Config.API_HOST + path).params(params);
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
