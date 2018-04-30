package ll.leon.com.widget_animation_effect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ll.leon.com.widget_animation_effect.helper.StatusBarHelper;
import ll.leon.com.widget_animation_effect.immerse.IMActivity;
import ll.leon.com.widget_animation_effect.widget.SimpleToolbar;
import ll.leon.com.widget_animation_effect.widget.windows.HomeWindow;
import ll.leon.com.widget_animation_effect.zxing.ZxingTestActivity;

public class IndexActivity extends AppCompatActivity {

    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.bt4)
    Button bt4;
    @BindView(R.id.bt5)
    Button bt5;
    @BindView(R.id.bt6)
    Button bt6;
    @BindView(R.id.bt7)
    Button bt7;
    @BindView(R.id.bt8)
    Button bt8;
    private Class<?> clazz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        SimpleToolbar  simpleToolbar = ((SimpleToolbar) findViewById(R.id.simple_toolbar));

        simpleToolbar.setLeftTitleClickListener(v -> Toast.makeText(this, "Left", Toast.LENGTH_SHORT).show())
                .setRightTitleClickListener(v -> FrameworkActivity.start(this))
                .setMainTitle("ToolBar")
                .setMainTitleColor(Color.RED)
        ;


        StatusBarHelper.setImmerse(this);
//        StatusBarHelper.setPaddingSmart(this,simpleToolbar);
//        StatusBarHelper.setDarkFontStatusBar(this);
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StatusBarHelper.clearDarkFontFlag(IndexActivity.this);
                    }
                });
            }
        }.start();
        ButterKnife.bind(this);

        new HomeWindow().show(getApplication());
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7,R.id.bt8,R.id.bt9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                clazz = MainActivity.class;
                break;
            case R.id.bt2:
                clazz = IMActivity.class;
                break;
            case R.id.bt3:
                clazz = DialogTestActivity.class;
                break;
            case R.id.bt4:
                clazz = WebActivity.class;
                break;
            case R.id.bt5:
                clazz = PictureActivity.class;
                break;
            case R.id.bt6:
                clazz = AnimationActivity.class;
                break;
            case R.id.bt7:
                clazz = UIWidgetActivity.class;
                break;
            case R.id.bt8:
                break;
            case R.id.bt9:
                clazz = ZxingTestActivity.class;
                break;
        }

        try {
            startActivity(new Intent(this, clazz));
        } catch (Exception e) {

        }

    }
}
