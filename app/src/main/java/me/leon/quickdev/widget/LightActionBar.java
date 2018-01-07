package me.leon.quickdev.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.leon.quickdev.R;


/**
 * Author:  Leon
 * Time:    2017/4/6 上午9:22
 * Desc:    自定义标题栏
 */

@SuppressWarnings("unused")
public class LightActionBar extends FrameLayout {

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.icon_0)
    ImageView mLeftIcon;
    @BindView(R.id.text_0)
    TextView mLeftText;

    @BindView(R.id.icon_1)
    ImageView mRightIcon;
    @BindView(R.id.text_1)
    TextView mRightText;

    @BindView(R.id.root)
    RelativeLayout mRoot;
    @BindView(R.id.divider)
    TextView mDivider;

    @BindView(R.id.tv_dot_0)
    TextView mLeftDot;
    @BindView(R.id.tv_dot_1)
    TextView mRightDot;
    @BindView(R.id.fl_icon_0)
    FrameLayout mLeftLayout;
    @BindView(R.id.fl_icon_1)
    FrameLayout mRightLayout;


    @BindView(R.id.tv_dot_2)
    TextView mRightDot2;
    @BindView(R.id.icon_2)
    public ImageView mRightIcon2;
    @BindView(R.id.fl_icon_2)
    FrameLayout mRightLayout2;

    @BindView(R.id.fl_back)
    FrameLayout fl_back;
    @BindView(R.id.actionbar_toast_text)
    TextView actionbarToastText;
    @BindView(R.id.actionbar_toast)
    LinearLayout actionbarToast;

    public LightActionBar(@NonNull Context context) {
        this(context, null);
    }

    public LightActionBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LightActionBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_action_bar_light, null);
        ButterKnife.bind(this, view);

        addView(view);
    }

    public LightActionBar reset() {

        mTitle.setText(null);
        mTitle.setVisibility(GONE);

        mLeftIcon.setImageDrawable(null);
        mLeftLayout.setOnClickListener(null);
        mLeftLayout.setVisibility(GONE);
        mLeftDot.setVisibility(GONE);

        mLeftText.setText(null);
        mLeftText.setOnClickListener(null);
        mLeftText.setVisibility(GONE);

        mRightIcon.setImageDrawable(null);
        mRightLayout.setOnClickListener(null);
        mRightLayout.setVisibility(GONE);
        mRightDot.setVisibility(GONE);

        mRightIcon2.setImageDrawable(null);
        mRightLayout2.setOnClickListener(null);
        mRightLayout2.setVisibility(GONE);
        mRightDot2.setVisibility(GONE);

        mRightText.setText(null);
        mRightText.setOnClickListener(null);
        mRightText.setVisibility(GONE);

        return this;
    }

    public LightActionBar setTitle(String title) {
        mTitle.setText(title);
        mTitle.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setTitle(@StringRes int resId) {
        mTitle.setText(resId);
        mTitle.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setLeftIcon(@DrawableRes int resId) {
        mLeftIcon.setImageResource(resId);
        mLeftLayout.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setLeftDotVisible(boolean visible) {
        mLeftDot.setVisibility(visible ? VISIBLE : GONE);
        return this;
    }

    public LightActionBar setLeftText(String text) {
        mLeftText.setText(text);
        mLeftText.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setLeftText(@StringRes int resId) {
        mLeftText.setText(resId);
        mLeftText.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setRightIcon(@DrawableRes int resId) {
        mRightIcon.setImageResource(resId);
        mRightLayout.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setRightIcon2(@DrawableRes int resId) {
        mRightIcon2.setImageResource(resId);
        mRightLayout2.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setRightDotVisible(boolean visible) {
        mRightDot.setVisibility(visible ? VISIBLE : GONE);
        return this;
    }

    public LightActionBar setRightDot2Visible(boolean visible) {
        mRightDot2.setVisibility(visible ? VISIBLE : GONE);
        return this;
    }

    public LightActionBar setRightText(String text) {
        mRightText.setText(text);
        mRightText.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setRightText(@StringRes int resId) {
        mRightText.setText(resId);
        mRightText.setVisibility(VISIBLE);
        return this;
    }

    public LightActionBar setTransparent(boolean transparent) {
        mDivider.setVisibility(transparent ? GONE : VISIBLE);
        mRoot.setBackgroundColor(transparent ? Color.TRANSPARENT : Color.WHITE);
        return this;
    }

    public LightActionBar setDividerVisible(boolean visible) {
        mDivider.setVisibility(visible ? VISIBLE : GONE);
        return this;
    }

    public LightActionBar addLeftIconAction(OnClickListener listener) {
        mLeftLayout.setOnClickListener(listener);
        return this;
    }

    public LightActionBar addLeftTextAction(OnClickListener listener) {
        mLeftText.setOnClickListener(listener);
        return this;
    }

    public LightActionBar addRightIconAction(OnClickListener listener) {
        mRightLayout.setOnClickListener(listener);
        return this;
    }

    public LightActionBar addRightIcon2Action(OnClickListener listener) {
        mRightLayout2.setOnClickListener(listener);
        return this;
    }

    public LightActionBar addTitleAction(OnClickListener listener) {
        mTitle.setOnClickListener(listener);
        return this;
    }

    public LightActionBar addRightTextAction(OnClickListener listener) {
        mRightText.setOnClickListener(listener);
        return this;
    }


    public TextView getTitleView() {
        return mTitle;
    }

    public ImageView getLeftIconView() {
        return mLeftIcon;
    }

    public TextView getLeftTextView() {
        return mLeftText;
    }

    public ImageView getRightIconView() {
        return mRightIcon;
    }

    public TextView getRightTextView() {
        return mRightText;
    }

    public TextView getDividerView() {
        return mDivider;
    }

    public FrameLayout getBackgroundView() {
        return fl_back;
    }
}
