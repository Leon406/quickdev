package me.leon.quickdev.ui.entry.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import me.leon.devsuit.android.IntentUtils;
import me.leon.libs.base.RightSwipeBaseActivity;
import me.leon.libs.utils.RxUtils;
import me.leon.libs.utils.T;
import me.leon.quickdev.BuildConfig;
import me.leon.quickdev.R;
import me.leon.quickdev.bean.SimpleUser;
import me.leon.quickdev.ui.entry.main2.Main2Activity;
import me.leon.quickdev.utils.LocationUtils;
import me.leon.quickdev.utils.Picker;
//import me.leon.libs.utils.AnimateToast;
//import me.leon.libs.utils.RxUtils;
//import me.leon.libs.utils.T;

public class MainActivity extends RightSwipeBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.down)
    TextView down;
    @BindView(R.id.upload)
    TextView upload;
    private RxPermissions rxPermissions;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

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
                .blockingSubscribe(l -> share());
        Flowable.just(1, 2, 3, 4, 5, 6, 7)
                .delay(3000, TimeUnit.MILLISECONDS)
                .filter(integer -> integer > 2)
//                .compose(RxUtils.rxSwitch())
                .subscribe(l -> showLocation());

        rxPermissions = new RxPermissions(this);

        getPresenter().doFetch();
        getPresenter().doFetchList();


        RxView.clicks(down)
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        onViewClicked();
                        // I can control the camera now
//                    } else if (permission.shouldShowRequestPermissionRationale) {
//                        T.getInstance().show("请先授权!!!", T.ERR);
                    } else {
                        Intent intent = IntentUtils.getAppDetailsSettingsIntent(getPackageName());
                        startActivity(intent);
                        T.getInstance().show("请先手动授权!!!", T.ERR);
                    }
                });

        RxView.clicks(down)
//                .subscribe(v -> getPresenter().doDownload(""));
                .subscribe(v -> getPresenter().doLogin("13957839096","a406123"));

        RxView.clicks(upload)
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .subscribe(granted -> {
                    if (granted) {
                        Picker.openDynamicGallery(this, null)
                                .toFlowable()
                                .flatMap(Flowable::fromIterable)
                                .map(localMedia -> new File(localMedia.getCompressPath()))
                                .toList()
                                .subscribe(lists -> getPresenter().doUpload(lists));
                    } else {

                    }

                });

    }

        private void showLocation () {
            LocationUtils.getLocation(location -> {

                if (BuildConfig.DEBUG) Log.d("MainActivity", location.getAddress());
            });
        }

        private void share () {
//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(option);

//        FragDialog.newInstance().show(getSupportFragmentManager(),null);


//        ShareParamWebPage param = new ShareParamWebPage();
//        param.setTitle("titel");
//        param.setContent("this content");
//        param.setTargetUrl("http://api.01yuelao.com/two/web/userview/id/281763.html");
//        param.setThumb(new ShareImage(R.drawable.share_wechat));
//
//        ShareDialog.getNewInstance(param).show(getSupportFragmentManager(),null);
        }

        // @OnClick(R.id.tv)
        public void onViewClicked () {
//        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
//        T.getInstance().show("emmit success",T.ERR);
//            Flowable.timer(2,TimeUnit.SECONDS).compose(RxUtils.rxSwitch()).subscribe(l->AnimateToast.hide());
            Main2Activity.start(this);

        }

        @Override
        public void onError (Throwable e){
            T.getInstance().show(e.getMessage(), T.ERR);
        }


        @Override
        public void onFetchSuccess (List < SimpleUser > user) {

            Flowable.fromIterable(user)
                    .compose(RxUtils.rxSwitch())
                    .subscribe(usr -> Log.d("MainActivity", usr.getNickName()));

        }
    }
