package me.leon.quickdev.ui.entry.splash;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import me.leon.libs.base.BaseActivity;
import me.leon.libs.base.BasePresenter;
import me.leon.libs.utils.RxUtils;
import me.leon.quickdev.R;
import me.leon.quickdev.ui.entry.main.MainActivity;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.btn)
    Button btn;

    @Override
    protected int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onViewInit() {

//        Flowable.timer(3, TimeUnit.SECONDS)
//                .compose(bindToLifecycle())
//                .blockingSubscribe(t -> {
//                    MainActivity.start(this);
//                    finish();
//                });

        btn.setText(getString(R.string.hint_time,3));
        btn.setOnClickListener(v->enter());
        Flowable.interval(1,1, TimeUnit.SECONDS)
                .take(3)
                .compose(bindToLifecycle())
                .compose(RxUtils.rxSwitch())
                .subscribe(aLong -> {
                    Log.d("SplashActivity", "aLong:" + (3 -aLong));
                    btn.setText(getString(R.string.hint_time,3 - aLong));
                    if (aLong == 2) {
                        enter();
                    }
                });

    }

    private void enter() {
        MainActivity.start(this);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
