package me.leon.quickdev.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibili.socialize.share.core.BiliShare;
import com.bilibili.socialize.share.core.shareparam.BaseShareParam;
import com.bilibili.socialize.share.core.shareparam.ShareImage;
import com.bilibili.socialize.share.core.shareparam.ShareParamWebPage;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import me.leon.libs.ShareDialog;
import me.leon.libs.base.BaseActivity;
import me.leon.libs.base.BasePresenter;
import me.leon.quickdev.PopTop;
import me.leon.quickdev.R;
//import me.leon.libs.utils.AnimateToast;
//import me.leon.libs.utils.RxUtils;
//import me.leon.libs.utils.T;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;


    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onViewInit() {
        //延时
        Flowable.timer(1000, TimeUnit.MILLISECONDS)
//                .compose(RxUtils.rxSwitch())
                .subscribe(l ->share());
        Flowable.just(1,2,3,4,5,6,7)
                .delay(3000,TimeUnit.MILLISECONDS)
                .filter(integer -> integer>2)
//                .compose(RxUtils.rxSwitch())
                .subscribe(System.out::println);
    }

    private void share() {

        ShareParamWebPage param = new ShareParamWebPage();
        param.setTitle("titel");
        param.setContent("this content");
        param.setTargetUrl("http://api.01yuelao.com/two/web/userview/id/281763.html");
        param.setThumb(new ShareImage(R.drawable.share_wechat));

        ShareDialog.getNewInstance(param).show(getSupportFragmentManager(),null);
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
//        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
//        T.getInstance().show("emmit success",T.ERR);
//            Flowable.timer(2,TimeUnit.SECONDS).compose(RxUtils.rxSwitch()).subscribe(l->AnimateToast.hide());
        new PopTop.Builder(this).setView(tv).show();
//        startActivity(new Intent(this,Main2Activity.class));
        share();
    }
}
