package me.leon.quickdev.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import me.leon.libs.base.BaseActivity;
import me.leon.libs.base.BasePresenter;
import me.leon.quickdev.R;

/**
 * Created by PC on 2017/12/22.
 */

public class Main2Activity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onViewInit() {

    }
}
