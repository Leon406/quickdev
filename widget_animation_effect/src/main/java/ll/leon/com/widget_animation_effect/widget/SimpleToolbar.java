package ll.leon.com.widget_animation_effect.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import ll.leon.com.widget_animation_effect.R;


public class SimpleToolbar extends Toolbar {

    /**
     * 左侧Title
     */
    private TextView mTxtLeftTitle;
    /**
     * 中间Title
     */
    private TextView mTxtMiddleTitle;
    /**
     * 右侧Title
     */
    private TextView mTxtRightTitle;

    public SimpleToolbar(Context context) {
        this(context,null);
    }

    public SimpleToolbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTxtLeftTitle = (TextView) findViewById(R.id.txt_left_title);
        mTxtMiddleTitle = (TextView) findViewById(R.id.txt_main_title);
        mTxtRightTitle = (TextView) findViewById(R.id.txt_right_title);
    }

    //设置中间title的内容
    public SimpleToolbar setMainTitle(String text) {
        this.setTitle(" ");
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setText(text);
        return this;
    }

    //设置中间title的内容文字的颜色
    public SimpleToolbar setMainTitleColor(int color) {
        mTxtMiddleTitle.setTextColor(color);
        return this;
    }

    //设置title左边文字
    public SimpleToolbar setLeftTitleText(String text) {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
        mTxtLeftTitle.setText(text);
        return this;
    }

    //设置title左边文字颜色
    public SimpleToolbar setLeftTitleColor(int color) {
        mTxtLeftTitle.setTextColor(color);
        return this;
    }

    //设置title左边图标
    public SimpleToolbar setLeftTitleDrawable(int res) {
        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
        mTxtLeftTitle.setCompoundDrawables(dwLeft, null, null, null);
        return this;
    }
    //设置title左边点击事件
    public SimpleToolbar setLeftTitleClickListener(OnClickListener onClickListener){
        mTxtLeftTitle.setOnClickListener(onClickListener);
        return this;
    }

    //设置title右边文字
    public SimpleToolbar setRightTitleText(String text) {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        mTxtRightTitle.setText(text);
        return this;
    }

    //设置title右边文字颜色
    public SimpleToolbar setRightTitleColor(int color) {
        mTxtRightTitle.setTextColor(color);
        return this;
    }

    //设置title右边图标
    public SimpleToolbar setRightTitleDrawable(int res) {
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
        mTxtRightTitle.setCompoundDrawables(null, null, dwRight, null);
        return this;
    }

    //设置title右边点击事件
    public SimpleToolbar setRightTitleClickListener(View.OnClickListener onClickListener){
        mTxtRightTitle.setOnClickListener(onClickListener);
        return this;
    }

}

