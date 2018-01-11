package ll.leon.com.widget_animation_effect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ll.leon.com.widget_animation_effect.immerse.IMActivity;
import ll.leon.com.widget_animation_effect.widget.ImmerseToolBar;
import ll.leon.com.widget_animation_effect.widget.SimpleToolbar;

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
    private Class<?> clazz;
    private ImmerseToolBar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        toolBar = ((ImmerseToolBar) findViewById(R.id.toolbar));

       SimpleToolbar simpleToolbar = ((SimpleToolbar) LayoutInflater.from(this).inflate(R.layout.layout_toolbar, null));
        simpleToolbar.setLeftTitleClickListener(v -> Toast.makeText(this, "Left", Toast.LENGTH_SHORT).show())
                .setRightTitleClickListener(v -> Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show())
                .setMainTitle("ToolBar")
                .setMainTitleColor(Color.RED)
        ;


       toolBar
              //  .setActionBarBackgroud(R.drawable.ic_launcher_background)
                .setToolbar(simpleToolbar);
//        StatusBarHelper.setImmerse(this);
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
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                clazz = MainActivity.class;
                break;
            case R.id.bt2:
                clazz = IMActivity.class;
                break;
            case R.id.bt3:
                break;
            case R.id.bt4:
                break;
            case R.id.bt5:
                break;
            case R.id.bt6:
                break;
            case R.id.bt7:
                break;
        }

        try {
            startActivity(new Intent(this, clazz));
        } catch (Exception e) {

        }

    }
}
