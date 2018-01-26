package ll.leon.com.widget_animation_effect.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import ll.leon.com.widget_animation_effect.helper.recyclerview.BannerLayoutManager;


public class BannerRecyclerView extends RecyclerView {

    private int interval = 3000;
    private boolean isAutoPlay = false;

    public BannerRecyclerView(Context context) {
        this(context, null);

    }

    public BannerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
         LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        BannerLayoutManager layoutManager = new BannerLayoutManager(context, LinearLayoutManager.HORIZONTAL);


        setLayoutManager(layoutManager);
        new PagerSnapHelper().attachToRecyclerView(this);
        startAutoPlay(true);
    }

    public void startAutoPlay(boolean autoPlay) {
        isAutoPlay = autoPlay;

        if (getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isAutoPlay) {
                        int position = layoutManager.findFirstCompletelyVisibleItemPosition();
                        if (position != -1) {
                            smoothScrollToPosition(++position);

                        }
                    }
                    postDelayed(this, interval);
                }

            }, interval);
        }else if (getLayoutManager() instanceof BannerLayoutManager) {

            BannerLayoutManager layoutManager = (BannerLayoutManager) getLayoutManager();
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isAutoPlay) {
                        int position = layoutManager.getCurrentPosition();
                        if (position != -1) {
                            smoothScrollToPosition(++position);

                        }
                    }
                    postDelayed(this, interval);
                }

            }, interval);
        }
    }



    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                isAutoPlay = false;
                break;
            case MotionEvent.ACTION_MOVE:
                isAutoPlay = false;

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

                isAutoPlay = true;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAutoPlay = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isAutoPlay = false;
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        isAutoPlay = visibility == VISIBLE;
    }

}
