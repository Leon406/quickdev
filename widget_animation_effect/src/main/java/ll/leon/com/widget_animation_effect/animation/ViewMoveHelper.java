package ll.leon.com.widget_animation_effect.animation;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import ll.leon.com.widget_animation_effect.BuildConfig;

/**
 * Created by Leon on 2018/1/6 0006.
 */

public class ViewMoveHelper {

    private static int statusBarHeight = -1;

    private ViewMoveHelper() {

    }

    public static void moveView(View v, int dy) {
        if (dy > 0) {
            statusBarHeight = dy;
        }
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float curX = event.getRawX();
                        float curY = event.getRawY();
                        moveViewByLayout(v, curX, curY);
                        break;
                }
                return true;
            }
        });
    }

    private static int getStatusBarHeight(View v) {
        int resourceId = v.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId < 0) {
            statusBarHeight = v.getResources().getDimensionPixelSize(resourceId);
        }
        if (BuildConfig.DEBUG) Log.d("ViewMoveHelper", "statusBarHeight:" + statusBarHeight);
        return statusBarHeight;
    }

    public static void moveViewByLayout(View view, float rawX, float rawY) {
        int left = (int) (rawX - view.getWidth() / 2);
        int top = (int) (rawY - getStatusBarHeight(view) - view.getHeight() / 2);

        int width = left + view.getWidth();
        int height = top + view.getHeight();
        view.layout(left, top, width, height);
    }
}
