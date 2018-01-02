package ll.leon.com.widget_animation_effect;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ll.leon.com.widget_animation_effect.effect.bubble.HeartLayout;
import ll.leon.com.widget_animation_effect.effect.explosion.ExplosionField;
import ll.leon.com.widget_animation_effect.effect.loading.ErrorCallback;
import ll.leon.com.widget_animation_effect.widget.RippleView;

public class MainActivity extends AppCompatActivity {

    private RippleView rippleView;
    private Random mRandom = new Random();
    private Timer mTimer = new Timer();
    private HeartLayout mHeartLayout;
    private LoadService loadService;
    private View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeartLayout = (HeartLayout) findViewById(R.id.heart_layout);
        rippleView = ((RippleView) findViewById(R.id.ripple));
        container = findViewById(R.id.container);
        loadService = LoadSir.getDefault().register(container, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showSuccess();

            }
        });

        loadService.setCallBack(ErrorCallback.class, (context, view) -> {
                    ((TextView) view.findViewById(R.id.tv_err)).setText("Err!!!");

                }

        );

        new Thread() {

            @Override
            public void run() {
                SystemClock.sleep(1000);
                loadService.showCallback(ErrorCallback.class);
//                runOnUiThread(() -> loadService.showCallback(ErrorCallback.class));


            }
        }.start();


    }

    private void show() {

        rippleView.stratRipple();


        ExplosionField explosionField = new ExplosionField(this);

        explosionField.addListener(rippleView);

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHeartLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mHeartLayout.addHeart(randomColor());
                    }
                });
            }
        }, 500, 200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }
}
