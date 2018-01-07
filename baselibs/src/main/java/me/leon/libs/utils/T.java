package me.leon.libs.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import me.leon.baselibs.R;


/**
 * Author:  Parorisim
 * Time:    2017/4/5 下午1:20
 * Desc:    吐司工具类
 */

public class T {

    @SuppressLint("StaticFieldLeak")
    private static volatile T INSTANCE;

    private Toast mToast;
    private Context mContext;
    private TextView msg;

    private T() {
    }

    public static T getInstance() {
        if (INSTANCE == null) {
            synchronized (T.class) {
                if (INSTANCE == null) {
                    INSTANCE = new T();
                }
            }
        }
        return INSTANCE;
    }

    @SuppressLint("ShowToast")
    public void init(Context context) {
        mContext = context;
        mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        mToast.setView(LayoutInflater.from(mContext).inflate(R.layout.view_toast, null));

        mToast.getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mToast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);

        msg = (TextView) mToast.getView().findViewById(R.id.msg);
        msg.setTextColor(Color.WHITE);
    }

    public void destroy() {
        mContext = null;
        mToast = null;
    }

    /**
     * 显示 Toast
     *
     * @param resId 字符串资源
     */
    public void show(@StringRes int resId, @Lv int level) {
        show(mContext.getString(resId), level);
    }

    /**
     * 显示 Toast
     *
     * @param text 字符串
     */
    public void show(String text, @Lv int level) {

        msg.setText(text);
        setLevel(level);
        mToast.show();
    }

    private void setLevel(@Lv int level) {
        if (level == ERR) {
            mToast.getView().setBackgroundResource(R.color.error);
        }
        if (level == OK) {
            mToast.getView().setBackgroundResource(R.color.ok);
        }
    }

    public static final int ERR = 0;
    public static final int OK = 1;

    @IntDef({ERR, OK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Lv {
    }
}
