package ll.leon.com.widget_animation_effect.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import ll.leon.com.widget_animation_effect.BuildConfig;

/**
 * @author Leon
 * @data 2018-1-12
 * @desc 状态栏工具类
 */

public class StatusBarHelper {
    private static final int COLOR_DEFAULT = Color.parseColor("#40000000");

    private StatusBarHelper() {
    }

    /**
     * 全屏
     *
     * @param activity
     */
    @TargetApi(19)
    public static void setFullScreen(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//   activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);//下拉不消失
    }

    /**
     * 布局侵入状态栏
     *
     * @param activity
     */
    @TargetApi(19)
    public static void setImmerse(Activity activity) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }

    }

    /**
     * 设置状态栏颜色  21以下无法设置
     *
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity, @ColorRes int color) {
        Window window = activity.getWindow();
        Resources resources = activity.getResources();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(resources.getColor(color));
        } else {
            addFakeStatusBar(activity, color);
        }

    }

    /**
     * @param activity
     * @desc 6.0以上原生支持, 以下兼容, 改变背状态栏字体颜色
     */

    @TargetApi(19)
    public static void setDarkFontStatusBar(Activity activity) {

        //当前手机版本为6.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            //activity.getWindow().setStatusBarColor(activity.getResources().getColor(statusColor));
            return;
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) { //兼容5.x
            activity.getWindow().setStatusBarColor(COLOR_DEFAULT);
        }

        //手机版本为4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            addFakeStatusBarColorInt(activity, COLOR_DEFAULT);
        }
    }

    @TargetApi(23)
    public static void clearDarkFontFlag(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = activity.getWindow().getDecorView();
            int origin = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(origin & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 添加fakeStatusBar 兼容Api 19及以下
     *
     * @param activity
     * @param color
     */
    public static void addFakeStatusBar(Activity activity, @ColorRes int color) {
        Resources resources = activity.getResources();

        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup systemContent = activity.findViewById(android.R.id.content);

        View statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity));
        statusBarView.setBackgroundColor(resources.getColor(color));

        systemContent.getChildAt(0).setFitsSystemWindows(true);

        systemContent.addView(statusBarView, 0, lp);
    }

    public static void addFakeStatusBarColorInt(Activity activity, @ColorInt int color) {

        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup systemContent = activity.findViewById(android.R.id.content);

        View statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity));
        statusBarView.setBackgroundColor(color);

        systemContent.getChildAt(0).setFitsSystemWindows(true);

        systemContent.addView(statusBarView, 0, lp);
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int statusBarHeight = resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
        if (BuildConfig.DEBUG) Log.d("StatusBarHelper", "statusBarHeight:" + statusBarHeight);
        return statusBarHeight;
    }


}
