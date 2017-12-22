package me.leon.quickdev;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import me.leon.libs.base.BaseActivity;

/**
 * Created by PC on 2017/12/22.
 */

public class Main2Activity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main2);
    }
}
