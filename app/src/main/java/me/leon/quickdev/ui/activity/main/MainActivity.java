package me.leon.quickdev.ui.activity.main;

import android.view.View;
import android.widget.TextView;

import com.bilibili.socialize.share.core.shareparam.ShareImage;
import com.bilibili.socialize.share.core.shareparam.ShareParamWebPage;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import me.leon.libs.ShareDialog;
import me.leon.libs.base.BaseActivity;
import me.leon.quickdev.R;
import me.leon.quickdev.ui.activity.main2.Main2Activity;
import me.leon.quickdev.widget.dialog.FragDialog;
//import me.leon.libs.utils.AnimateToast;
//import me.leon.libs.utils.RxUtils;
//import me.leon.libs.utils.T;

public class MainActivity extends BaseActivity<MainContract.View,MainPresenter>  implements MainContract.View {

    @BindView(R.id.tv)
    TextView tv;


    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onViewInit() {
        //延时
        Flowable.timer(1000, TimeUnit.MILLISECONDS)
//                .compose(RxUtils.rxSwitch())
                .blockingSubscribe(l ->share());
        Flowable.just(1,2,3,4,5,6,7)
                .delay(3000,TimeUnit.MILLISECONDS)
                .filter(integer -> integer>2)
//                .compose(RxUtils.rxSwitch())
                .subscribe(System.out::println);

        getPresenter().doFetch();
        getPresenter().doFetchList();
    }

    private void share() {
//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(option);

        FragDialog.newInstance().show(getSupportFragmentManager(),null);

//        ShareParamWebPage param = new ShareParamWebPage();
//        param.setTitle("titel");
//        param.setContent("this content");
//        param.setTargetUrl("http://api.01yuelao.com/two/web/userview/id/281763.html");
//        param.setThumb(new ShareImage(R.drawable.share_wechat));
//
//        ShareDialog.getNewInstance(param).show(getSupportFragmentManager(),null);
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
//        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
//        T.getInstance().show("emmit success",T.ERR);
//            Flowable.timer(2,TimeUnit.SECONDS).compose(RxUtils.rxSwitch()).subscribe(l->AnimateToast.hide());
        Main2Activity.start(this);

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onFail(Throwable e) {

    }

    @Override
    public void onFetchSuccess() {

    }
}
