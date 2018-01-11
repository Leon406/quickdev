package ll.leon.com.widget_animation_effect.immerse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ll.leon.com.widget_animation_effect.R;
import ll.leon.com.widget_animation_effect.StatusBarHelper;


public class FullScrrenNoTextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_no_text);
        StatusBarHelper.setFullScreen(this);
        //方式一
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //方式二
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //方式三 style.xml中配置
        //<style name="fullScreen" parent="Theme.AppCompat.DayNight.NoActionBar">
        //        <item name="android:windowFullscreen">true</item>
        //</style>
    }
}
