package ll.leon.com.widget_animation_effect;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        // 进入 退出动画
//        getWindow().setEnterTransition(new Explode().setDuration(2000));
//        getWindow().setExitTransition(new Explode().setDuration(2000));
    }
}
