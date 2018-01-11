package ll.leon.com.widget_animation_effect.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import ll.leon.com.widget_animation_effect.R;


/**
 * ImmerseToolBar 沉浸式自定义toolbar 容器
 */

public final class ImmerseToolBar extends LinearLayout {

    private View layRoot;
    private View vStatusBar;
    private FrameLayout toolbar;

    public ImmerseToolBar(Context context) {
        this(context, null);
    }

    public ImmerseToolBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ImmerseToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        View contentView = inflate(getContext(), R.layout.view_immerse_toolbar, this);
        layRoot = contentView.findViewById(R.id.lay_transroot);
        vStatusBar = contentView.findViewById(R.id.v_statusbar);
        toolbar = contentView.findViewById(R.id.fl_toolbar);

    }

    /**
     * 设置状态栏高度
     *
     * @param statusBarHeight
     */
    public ImmerseToolBar setStatusBarHeight(int statusBarHeight) {
        ViewGroup.LayoutParams params = vStatusBar.getLayoutParams();
        params.height = statusBarHeight;
        vStatusBar.setLayoutParams(params);
        return this;
    }

    /**
     * 设置是否需要渐变
     */
    public ImmerseToolBar setNeedTranslucent() {
        setNeedTranslucent(true);
        return this;
    }

    public ImmerseToolBar setToolbar(@LayoutRes int redId) {
        LayoutInflater.from(getContext()).inflate(redId,toolbar,true);
//        toolbar.addView(bar);
        return this;
    }
    public ImmerseToolBar setToolbar(View view) {
        toolbar.addView(view);
        return this;
    }


    /**
     *
     * @param translucent
     */
    private void setNeedTranslucent(boolean translucent) {
            layRoot.setBackgroundDrawable(null);
    }

    public ImmerseToolBar setActionBarBackgroud(@DrawableRes int resID) {
        layRoot.setBackgroundResource(resID);
        return this;
    }
}
