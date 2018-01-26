package ll.leon.com.widget_animation_effect.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import ll.leon.com.widget_animation_effect.R;
import ll.leon.com.widget_animation_effect.helper.recyclerview.BannerLayoutManager;


public class IndicatorView extends LinearLayout {

    private int mDotCount;
    private int mCurrentPosition;
    private int mOldPosition;
    private Drawable mSelectedDrawable;
    private Drawable mUnselectedDrawable;
    private int mGap;
    private int mWidth;
    private int mHeight;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        mSelectedDrawable = a.getDrawable(R.styleable.IndicatorView_indicatorSelectedSrc);
        mUnselectedDrawable = a.getDrawable(R.styleable.IndicatorView_indicatorUnselectedSrc);
        mGap = (int) (a.getDimension(R.styleable.IndicatorView_indicatorPadding, dp2px(10)) + 0.5);
        mWidth = (int) (a.getDimension(R.styleable.IndicatorView_indicatorWidth, dp2px(10)) + 0.5);
        mHeight = (int) (a.getDimension(R.styleable.IndicatorView_indicatorHeight, dp2px(10)) + 0.5);

        if (mSelectedDrawable == null) {
            //绘制默认选中状态图形
            GradientDrawable selectedGradientDrawable = new GradientDrawable();
            selectedGradientDrawable.setShape(GradientDrawable.OVAL);
            selectedGradientDrawable.setColor(getColor(R.color.colorAccent));
            selectedGradientDrawable.setSize(dp2px(5), dp2px(5));
            selectedGradientDrawable.setCornerRadius(dp2px(5) / 2);
            mSelectedDrawable = new LayerDrawable(new Drawable[]{selectedGradientDrawable});
        }
        if (mUnselectedDrawable == null) {
            //绘制默认未选中状态图形
            GradientDrawable unSelectedGradientDrawable = new GradientDrawable();
            unSelectedGradientDrawable.setShape(GradientDrawable.OVAL);
            unSelectedGradientDrawable.setColor(getColor(R.color.colorPrimaryDark));
            unSelectedGradientDrawable.setSize(dp2px(5), dp2px(5));
            unSelectedGradientDrawable.setCornerRadius(dp2px(5) / 2);
            mUnselectedDrawable = new LayerDrawable(new Drawable[]{unSelectedGradientDrawable});
        }

    }

    public void setupWithViewpager(ViewPager pager) {
        setupWithViewpager(pager, 1);

    }

    public void setupWithViewpager(ViewPager pager, int size) {
//        mDotCount = pager.getAdapter().getCount()% size;
        mDotCount = size;

        initDot(mDotCount);

        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                handleWithDot(position % mDotCount);
            }
        });
    }

    public void setupWithRecyclerView(RecyclerView pager) {
        setupWithRecyclerView(pager, 1);

    }

    public void setupWithRecyclerView(RecyclerView recyclerView, int size) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof LinearLayoutManager) {
//            mDotCount = recyclerView.getAdapter().getItemCount();
            mDotCount = size;
            initDot(mDotCount);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    handleWithDot(((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() % mDotCount);
                }
            });

        }else if (layoutManager instanceof BannerLayoutManager) {
            mDotCount = size;
            initDot(mDotCount);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    handleWithDot(((BannerLayoutManager) layoutManager).getCurrentPosition() % mDotCount);
                }
            });
        }
    }

    private void initDot(int count) {
        //消除容器里面的布局
        removeAllViews();
        for (int i = 0; i < count; i++) {
            View view = new View(getContext());
            view.setBackground(mUnselectedDrawable);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, mHeight);
            //右边距
            params.rightMargin = mGap;
            //添加布局
            addView(view, params);
        }

        getChildAt(0).setBackground(mSelectedDrawable);
    }

    private void handleWithDot(int position) {
        if (position == -1) {
            return;
        }
        mCurrentPosition = position;
        if (mCurrentPosition != mOldPosition) {

            getChildAt(mOldPosition).setBackground(mUnselectedDrawable);
            getChildAt(mCurrentPosition).setBackground(mSelectedDrawable);

            mOldPosition = mCurrentPosition;
        }

    }


    protected int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 获取颜色
     */
    protected int getColor(@ColorRes int color) {
        return ContextCompat.getColor(getContext(), color);
    }
}
