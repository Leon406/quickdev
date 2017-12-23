package me.leon.libs.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by PC on 2017/12/21.
 */

public class AnimateToast {

    /**
     * 窗体管理者
     */
    private static WindowManager wm;
    private static WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
    private static View mView;
    private static TextView tv;

    /**
     * 显示自定义吐司
     *
     * @param message
     * @param context
     */
    public static void show(String message, Context context, int animate) {
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        tv = new TextView(context);
        tv.setText(message);
        tv.setTextSize(20);
        // 原来TN所做的工作
        WindowManager.LayoutParams params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.TOP;
        params.format = PixelFormat.TRANSLUCENT;


        params.type =  WindowManager.LayoutParams.TYPE_TOAST;

//                Build.VERSION.SDK_INT <= 23 ? WindowManager.LayoutParams.TYPE_TOAST :
//                Build.VERSION.SDK_INT <= 25 ? WindowManager.LayoutParams.TYPE_SYSTEM_ALERT : WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        params.windowAnimations = animate;
        params.setTitle("Toast");
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        wm.addView(tv, params);

    }

    /**
     * 自定义文本吐司的显示方法，这个方法提供了更多自定义的内容，比如textview可以应用一个传入的style的id
     *
     * @param message
     * @param context
     * @param textViewResid
     * @param params
     */
    public static void show(String message, Context context, int textViewResid,
                            WindowManager.LayoutParams params) {
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        tv = new TextView(context);
        // 多加了一个风格
        tv.setTextAppearance(context, textViewResid);
        // 原来TN所做的工作
        // WindowManager.LayoutParams params = mParams;
        // params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        // params.format = PixelFormat.TRANSLUCENT;
        // params.type = WindowManager.LayoutParams.TYPE_TOAST;
        // params.setTitle("Toast");
        // params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        // | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        wm.addView(tv, params);
    }

    /**
     * 更多自定义的形式，可以直接传入任一个自己定义好的view，自己设置wm的参数
     *
     * @param view
     * @param context
     * @param params  WindowManager.LayoutParams类型的参数， WindowManager.LayoutParams
     *                mParams = new WindowManager.LayoutParams(); params.height =
     *                WindowManager.LayoutParams.WRAP_CONTENT; params.width =
     *                WindowManager.LayoutParams.WRAP_CONTENT; params.format =
     *                PixelFormat.TRANSLUCENT; params.type =
     *                WindowManager.LayoutParams.TYPE_TOAST;
     *                params.setTitle("Toast"); params.flags =
     *                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
     *                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
     *                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
     */
    public static void show(View view, Context context,
                            WindowManager.LayoutParams params) {
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mView = view;
        wm.addView(mView, params);

    }

    /**
     * 隐藏自定义吐司 这里一定要记得判空一下，因为平时没有打电话时，这两个量应该都是空的
     */
    public static void hide() {
        if (wm != null) {
            if (tv != null) {
                wm.removeView(tv);
                tv = null;
            }
            if (mView != null) {
                wm.removeView(mView);
                mView = null;
            }
            if (tv == null && mView == null) {
                wm = null;
            }
        }
    }
}