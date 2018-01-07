package me.leon.quickdev.utils;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by Leon on 2018/1/3 0003.
 * @desc    定位工具类
 */

public class LocationUtils {
    private static AMapLocationClient mlocationClient;
    public static AMapLocationClientOption mLocationOption = null;
    public static AMapLocation sLocation = null;

    public static void init(Context context) {
        // 声明mLocationOption对象
        mlocationClient = new AMapLocationClient(context);
        // 初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        // 设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
    }

    public static void getCurrentLocation(final MyLocationListener listener) {
        if (mlocationClient == null) {
            return;
        }
        // 设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {

            @Override
            public void onLocationChanged(AMapLocation location) {
                if (location != null && location.getErrorCode() == 0) {
                    //定位成功，取消定位
                    mlocationClient.stopLocation();
                    sLocation = location;
                    listener.result(location);
                } else {
                    //获取定位数据失败
                }
            }
        });
        // 启动定位
        mlocationClient.startLocation();
    }

    public static void destroy() {
        mlocationClient.onDestroy();
    }

    public static void getLocation(MyLocationListener listener) {
        if (sLocation == null) {
            getCurrentLocation(listener);
        } else {
            listener.result(sLocation);
        }
    }

    public interface MyLocationListener {
         void result(AMapLocation location);
    }
}
