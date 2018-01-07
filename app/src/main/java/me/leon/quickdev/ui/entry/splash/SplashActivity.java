package me.leon.quickdev.ui.entry.splash;

import android.widget.Button;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import me.leon.devsuit.android.SPUtils;
import me.leon.libs.base.BaseActivity;
import me.leon.libs.base.BasePresenter;
import me.leon.libs.utils.RxUtils;
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

        btn.setText(getString(R.string.hint_time,3));

        btn.setOnClickListener(v->enter());
        Flowable.interval(1,1, TimeUnit.SECONDS)
                .take(3)
                .compose(bindToLifecycle())
                .compose(RxUtils.rxSwitch())
                .subscribe(this::enter);

    }

    private void enter() {
        if (SPUtils.getInstance("setting").getBoolean("isShowWelcome",false)) {
            MainActivity.start(this);
        }else {
            WelcomeActivity.start(this);
        }


        finish();
    }private void enter(long aLong) {
       btn.setText(getString(R.string.hint_time,3 - aLong));
        if (aLong == 2) {
            enter();
        }
    }
}
