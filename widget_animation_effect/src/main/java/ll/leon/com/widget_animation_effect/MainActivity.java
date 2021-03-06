package ll.leon.com.widget_animation_effect;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ll.leon.com.widget_animation_effect.animation.ViewMoveHelper;
import ll.leon.com.widget_animation_effect.effect.bubble.HeartLayout;
import ll.leon.com.widget_animation_effect.effect.explosion.ExplosionField;
import ll.leon.com.widget_animation_effect.effect.loading.ErrorCallback;
import ll.leon.com.widget_animation_effect.helper.CodeUtils;
import ll.leon.com.widget_animation_effect.helper.recyclerview.AbsAdapter;
import ll.leon.com.widget_animation_effect.helper.recyclerview.BaseHolderHelper;
import ll.leon.com.widget_animation_effect.widget.RippleView;

public class MainActivity extends AppCompatActivity {

    private RippleView rippleView;
    private Random mRandom = new Random();
    private Timer mTimer = new Timer();
    private HeartLayout mHeartLayout;
    private LoadService loadService;
    private View container;
    private ViewGroup.LayoutParams params;
    private float titleHeight;
    private Rect rectangle;
    private int statusBarHeight1;
    private RecyclerView rv;
    private AbsAdapter adapter;
    private CodeUtils instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View content = findViewById(android.R.id.content);
        ImageView image = findViewById(R.id.image);
        refreshCode(image);
        image.setOnClickListener(v->refreshCode(image));
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            data.add("test Data :"+i);
        }
       // adapter = new TestAdapter( data, android.R.layout.simple_list_item_1);
        adapter =new AbsAdapter<String>(data, android.R.layout.simple_list_item_1){
            @Override
            protected void convert(BaseHolderHelper holder, String data, int position) {
                holder.setText(android.R.id.text1,data);
            }
        };

        List<String> data2 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            data2.add("test Data2 :"+i);
        }
        rv.setAdapter(adapter);
        adapter.addData(data2);

        statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("WangJ", "状态栏-方法1:" + statusBarHeight1);

        mHeartLayout = (HeartLayout) findViewById(R.id.heart_layout);
        rippleView = ((RippleView) findViewById(R.id.ripple));
        container = findViewById(R.id.container);
        loadService = LoadSir.getDefault()
                .register(this, v -> loadService.showSuccess());

        loadService.setCallBack(ErrorCallback.class, (context, view) ->
                ((TextView) view.findViewById(R.id.tv_err)).setText("Err!!!"));

        new Thread(() -> {
            SystemClock.sleep(1000);
            loadService.showCallback(ErrorCallback.class);
        }).start();

        ViewMoveHelper.moveView(rippleView,statusBarHeight1);
    }

    private void refreshCode(ImageView image) {
        instance = CodeUtils.getInstance();
        image.setImageBitmap(instance.createBitmap());
        Toast.makeText(this, instance.getCode(), Toast.LENGTH_SHORT).show();
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
    public void onWindowFocusChanged(boolean hasFocus) {
        rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        //高度为rectangle.top-0仍为rectangle.top
        Log.e("WangJ", "状态栏-方法3:" + rectangle.top);
        super.onWindowFocusChanged(hasFocus);
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
