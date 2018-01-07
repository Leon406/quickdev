package me.leon.libs;

/**
 * Created by PC on 2017/12/22.
 */

public interface Config {
    public static final String SERVER =  false ?   "http://120.27.217.136:8080" : "http://api.01yuelao.com";
    public static final String API_HOST =SERVER+ "/two/";
    public static final String APP_ID_WECHAT = "wx311952cca4d43c2d";
    public static final String APP_ID_QQ = "1106059675";
    public static final String API_KEY = "loveu123456";
    public static final String BUNDLE_POSITION ="position";
    public static final String BUNDLE_DATA ="bundle_data";
}
