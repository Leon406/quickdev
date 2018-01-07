package ll.leon.com.widget_animation_effect.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

/**自动获取焦点,不会在失去焦点后停止滚动
 * Created by Leon on 2017/6/2 0002.
 */

public class RollTextView extends android.support.v7.widget.AppCompatTextView {
    public RollTextView(Context context) {
        super(context);
        init();
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
private void init() {
    /*
    android:ellipsize="marquee"
    android:focusableInTouchMode="true"
    android:focusable="true"
    android:marqueeRepeatLimit="marquee_forever"
    android:singleLine="true"*/
    setFocusable(true);
    setFocusableInTouchMode(true);
    setSingleLine();
    setMarqueeRepeatLimit(-1);
    setEllipsize(TextUtils.TruncateAt.MARQUEE);


}
    /**
     * 是否获取焦点
     * @return true 始终获取焦点
     */
    @Override
    public boolean isFocused() {
        return true;
    }

    /**
     *
     * @param focused   true ,始终获取焦点
     * @param direction
     * @param previouslyFocusedRect
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(true, direction, previouslyFocusedRect);
    }
}
