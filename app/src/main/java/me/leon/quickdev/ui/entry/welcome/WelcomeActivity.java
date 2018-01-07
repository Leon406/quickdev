package me.leon.quickdev.ui.entry.welcome;

import me.leon.libs.base.BaseActivity;
import me.leon.libs.base.BasePresenter;
import me.leon.quickdev.R;

/**
 * Created by Leon on 2018/1/6 0006.
 */

public class WelcomeActivity extends BaseActivity {
    @Override
    protected int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onViewInit() {

    }
}
