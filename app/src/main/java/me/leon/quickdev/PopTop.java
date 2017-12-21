package me.leon.quickdev;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

 

public class PopTop extends PopupWindow implements View.OnClickListener {

    public static View mainView;
    protected final Builder builder;


    public PopTop(Builder builder) {
        //窗口布局
        super(builder.context);
        this.builder = builder;
        Create();
    }

    public void Create() {
        //窗口布局
        setContentView(mainView = LayoutInflater.from(builder.context).inflate(R.layout.pop_top, null));
        //设置宽度
        setWidth(dip2px(builder.context, 100));
        //设置高度
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setTouchable(true);
        setFocusable(true);
        //设置显示隐藏动画
//        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable());
        /*          //监听窗口的焦点事件，点击窗口外面则取消显示*/
        getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    dismiss();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        dismiss();

    }



    public static class Builder {
        protected final Context context;
        protected View view;
        PopTopOnClick popTopOnClick;

        public Builder setPopTopOnClick(PopTopOnClick popTopOnClick) {
            this.popTopOnClick = popTopOnClick;
            return this;
        }


        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        @UiThread
        public PopupWindow build() {
            return new PopTop(this);
        }

        @UiThread
        public PopupWindow show() {
            PopupWindow popTop = build();
            int windowPos[] = calculatePopWindowPos(view);
            windowPos[0] -= 15; //x 轴向左偏移15像素
            windowPos[1] -= 10; //y 轴向上偏移10像素
            popTop.showAtLocation(view, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
            return popTop;
        }
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView 呼出window的view
     * @return window显示的左上角的xOff, yOff坐标
     */
    public static int[] calculatePopWindowPos(final View anchorView) {

        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = getScreenHeight(anchorView.getContext());
        final int screenWidth = getScreenWidth(anchorView.getContext());
        mainView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = mainView.getMeasuredHeight();
        final int windowWidth = mainView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public interface PopTopOnClick{
        void EditOnclick();
        void DelOnclick();
        void SysOnclick();
        void CreateOnclick();
    }

}