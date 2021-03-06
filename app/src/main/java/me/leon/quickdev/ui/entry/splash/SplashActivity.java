package me.leon.quickdev.ui.entry.splash;

import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import me.leon.baselibs.BuildConfig;
import me.leon.devsuit.android.SPUtils;
import me.leon.libs.base.BaseActivity;
import me.leon.libs.base.BasePresenter;
import me.leon.quickdev.R;
import me.leon.quickdev.ui.entry.main.MainActivity;
import me.leon.quickdev.ui.entry.welcome.WelcomeActivity;

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

        btn.setText(getString(R.string.hint_time, 3));

        btn.setOnClickListener(v -> enter());

        enter();
//        timer( 1, 3,  this)
//                .subscribe(this::enter);

//        Flowable.interval(1,1, TimeUnit.SECONDS)
//                .take(6)
//                .compose(RxUtils.flowableLifecycle(this))
//                .compose(RxUtils.rxSwitch())
//                .subscribe(this::enter);

    }

    private void enter() {
        if (SPUtils.getInstance("setting").getBoolean("isShowWelcome", false)) {
            MainActivity.start(this);
        } else {
            WelcomeActivity.start(this);
        }


        finish();
    }

    private void enter(long aLong) {

        btn.setText(getString(R.string.hint_time, 3 - aLong));
        if (BuildConfig.DEBUG) Log.d("SplashActivity", "aLong:  " + aLong);
        if (aLong == 2) {
            enter();
        }
    }
}
