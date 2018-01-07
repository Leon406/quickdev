package me.leon.libs.base;

import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import me.leon.baselibs.BuildConfig;
import me.leon.baselibs.R;
import me.leon.libs.utils.RxUtils;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

/**
 * Created by Leon on 2018/1/7 0007.
 * @desc 侧滑退出
 */

public abstract class RightSwipeBaseActivity<V extends IView, P
        extends BasePresenter<V>> extends BaseActivity<V , P> {

    //手指上下滑动时的最小速度
    protected static final int YSPEED_MIN = 1000;
    //手指向右滑动时的最小距离
    protected static final int XDISTANCE_MIN = Resources.getSystem().getDisplayMetrics().widthPixels / 3;
    protected static final int X_TOUCH_ARAE = XDISTANCE_MIN;
    //手指向上滑或下滑时的最小距离
    protected static final int YDISTANCE_MIN = 100;
    //记录手指按下时的横坐标。
    protected float xDown;
    //记录手指按下时的纵坐标。
    protected float yDown;
    //记录手指移动时的横坐标。
    protected float xMove;
    //记录手指移动时的纵坐标。
    protected float yMove;
    //用于计算手指滑动的速度。
    protected VelocityTracker mVelocityTracker;
    protected boolean disallowIntercept;
    protected boolean flag;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        createVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                yDown = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:

                if (xDown > X_TOUCH_ARAE) {
                    break;
                }
                xMove = event.getRawX();
                yMove = event.getRawY();
                //滑动的距离
                int distanceX = (int) (xMove - xDown);
                int distanceY = (int) (yMove - yDown);
                //获取顺时速度
                int ySpeed = getScrollVelocity();

                //关闭Activity需满足以下条件：
                //1.x轴滑动的距离>XDISTANCE_MIN
                //2.y轴滑动的距离在YDISTANCE_MIN范围内
                //3.y轴上（即上下滑动的速度）<XSPEED_MIN，如果大于，则认为用户意图是在上下滑动而非左滑结束Activity


                if (distanceX > XDISTANCE_MIN
                        && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN)
                        && ySpeed < YSPEED_MIN && !disallowIntercept) {
                    finish();
                    overridePendingTransition(0, R.anim.slide_out_right);
                }

                if (distanceX > distanceY
                        && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN
                        && ySpeed < YSPEED_MIN) && !disallowIntercept) {
                    flag = distanceX > 0;
                    getWindow().getDecorView().scrollTo(-distanceX, 0);
                    return false;
                }
                if (flag) {
                    return false;
                }

                break;
            case MotionEvent.ACTION_UP:

                if (xDown > X_TOUCH_ARAE) {
                    break;
                }
                recycleVelocityTracker();

                int finalDistanceX = (int) (xMove - xDown);
                int finalDistanceY = (int) (yMove - yDown);
                if (
                        flag
                                || finalDistanceX > finalDistanceY && finalDistanceX > 0 && !disallowIntercept) {
//                    getWindow().getDecorView().scrollTo(0, 0);

                    Flowable.interval(10, 10, TimeUnit.MILLISECONDS)
                            .take(10)
                            .compose(bindToLifecycle())
                            .compose(RxUtils.rxSwitch())
                            .subscribe(l -> {
                                getWindow().getDecorView().scrollBy(finalDistanceX / 10, 0);
                                if (BuildConfig.DEBUG) Log.d("BaseActivity ", l + "");
                                if (l == 9) {

                                    getWindow().getDecorView().scrollTo(0, 0);
                                }

                            });
                    flag = false;
//
                    return false;
                }
                if (flag) {
                    getWindow().getDecorView().scrollTo(0, 0);

                    flag = false;
                    return false;
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 创建VelocityTracker对象，并将触摸界面的滑动事件加入到VelocityTracker当中。
     *
     * @param event
     */
    protected  void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 回收VelocityTracker对象。
     */
    protected  void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    /**
     * @return 滑动速度，以每秒钟移动了多少像素值为单位。
     */
    protected int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getYVelocity();
        return Math.abs(velocity);
    }

    /**
     * 请求不拦截事件
     * @param flag
     */
    protected void requestDisallowIntercept(boolean flag) {
        disallowIntercept = flag;
    }

    /**
     * 处理水平滑动RecycleView 事件冲突
     * @param rv
     */

    protected void handleTouchEventConflict(RecyclerView rv) {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = rv.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager && ((LinearLayoutManager) layoutManager).getOrientation()==HORIZONTAL) {
                    requestDisallowIntercept(((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() != 0);
                }
            }
        });
    }
    /**
     * 处理水平滑动ViewPager 事件冲突
     * @param vp
     */
    protected void handleTouchEventConflict(ViewPager vp) {
        requestDisallowIntercept(vp.getCurrentItem() != 0);
        vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                requestDisallowIntercept(position != 0);
            }
        });
    }
}
