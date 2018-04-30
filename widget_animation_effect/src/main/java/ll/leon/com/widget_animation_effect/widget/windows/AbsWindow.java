package ll.leon.com.widget_animation_effect.widget.windows;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/17.
 */

public abstract class AbsWindow {
    private  int sW;
    private  int sH;
    protected WindowManager.LayoutParams params;
    protected Context app;
    protected WindowManager wm;
    protected View view;
    protected  LayoutInflater inflater;

    protected OnFloatWindowClickListener listener;
    private float x;
    private float y;
    private float mTouchStartX;
    private float mTouchStartY;
    private float mStartX;
    private float mStartY;
    private long mLastTime;
    private boolean isMove;
    private float mLastX;
    private float mLastY;
    private long mCurrentTime;
    private float statusHeight;

    public WindowManager getWm() {
        return wm;
    }

    public View getView() {
        return view;
    }


    public void setListener(OnFloatWindowClickListener listener) {
        this.listener = listener;
    }

    public void show(Context application) {
        app = application;
        statusHeight =getStatusHeight(application);
        if (inflater == null) {
            inflater = LayoutInflater.from(app);
        }
        params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        if (isOverlayApiDeprecated()) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {

            if (Build.VERSION.SDK_INT < 21) {
                params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
            } else if (Build.VERSION.SDK_INT < 24) {
                params.type = WindowManager.LayoutParams.TYPE_TOAST;
            } else {
                params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            }
        }
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.format = PixelFormat.TRANSLUCENT;

        customLayoutParameter();
        wm = WindowManager.class.cast(application.getSystemService(Context.WINDOW_SERVICE));

        sW = wm.getDefaultDisplay().getWidth();
        sH = wm.getDefaultDisplay().getHeight();

        view = inflater.inflate(bindLayout(), null);




        initView();

        initTouchEvent(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onViewClicked();
//            }
//        });

        wm.addView(view, params);


    }

    protected  void initTouchEvent(@NonNull View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取相对屏幕的坐标，即以屏幕左上角为原点
                x = event.getRawX();
                y = event.getRawY() - statusHeight;   //statusHeight是系统状态栏的高度
                Log.i("currP", "currX" + x + "====currY" + y);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:    //捕获手指触摸按下动作
                        //获取相对View的坐标，即以此View左上角为原点
                        mTouchStartX = event.getX();
                        mTouchStartY = event.getY();
                        mStartX = event.getRawX();

                        mStartY = event.getRawY();
                        mLastTime = System.currentTimeMillis();
                        Log.i("startP", "startX" + mTouchStartX + "====startY" + mTouchStartY);
                        isMove = false;
                        break;


                    case MotionEvent.ACTION_MOVE:   //捕获手指触摸移动动作
                        updateViewPosition();
                        isMove = true;
                        break;


                    case MotionEvent.ACTION_UP:    //捕获手指触摸离开动作
                        mLastX = event.getRawX();
                        mLastY = event.getRawY();


                        // 抬起手指时让floatView紧贴屏幕左右边缘
                        params.x = params.x <= (sW / 2) ? 0 : sW;
                        params.y = (int) (y - mTouchStartY);
                        wm.updateViewLayout(view, params);




                        mCurrentTime = System.currentTimeMillis();
                        if(mCurrentTime - mLastTime < 800){
                            if(Math.abs(mStartX - mLastX)< 10.0 && Math.abs(mStartY - mLastY) < 10.0){
                                //处理点击的事件
                                Toast.makeText(app,"可以处理点击事件",Toast.LENGTH_SHORT).show();
                            }
                        }


                        break;
                }
                return true;
            }

        });


    }
    private void updateViewPosition() {
        //更新浮动窗口位置参数
        params.x = (int) (x - mTouchStartX);
        params.y = (int) (y - mTouchStartY);
        wm.updateViewLayout(view, params);  //刷新显示


    }

    protected abstract void initView();

    public abstract int bindLayout();

    public static int getStatusHeight(Context context) {


        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


    public void customLayoutParameter() {
//        params.gravity = Gravity.BOTTOM | Gravity.LEFT;

    }


    public void removeView() {
        if (wm != null && view != null) {
            wm.removeViewImmediate(view);
            view = null;
            wm = null;
        }
    }

    protected boolean isOverlayApiDeprecated() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public interface OnFloatWindowClickListener {
        void onClick();

    }
}
