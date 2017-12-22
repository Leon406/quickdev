package me.leon.quickdev;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import me.leon.libs.base.BaseActivity;
import me.leon.libs.utils.AnimateToast;
import me.leon.libs.utils.RxUtils;
import me.leon.libs.utils.T;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //延时
        Flowable.timer(1000, TimeUnit.MILLISECONDS)
                .compose(RxUtils.rxSwitch())
                .subscribe(l -> T.getInstance().show("emmit success",T.OK));
        Flowable.just(1,2,3,4,5,6,7)
                .delay(3000,TimeUnit.MILLISECONDS)
                .filter(integer -> integer>2)
                .compose(RxUtils.rxSwitch())
                .subscribe(System.out::println);


    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
//        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
//        T.getInstance().show("emmit success",T.ERR);
            AnimateToast.show("hello",this,R.style.Lite_Animation_Toast);
            Flowable.timer(2,TimeUnit.SECONDS).compose(RxUtils.rxSwitch()).subscribe(l->AnimateToast.hide());
        new PopTop.Builder(this).setView(tv).show();
        startActivity(new Intent(this,Main2Activity.class));
    }
}
